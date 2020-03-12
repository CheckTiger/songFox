package cn.sxh.songfox.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import cn.sxh.songfox.util.DpUtils;

/**
 * @package-name: cn.sxh.songfox.widget
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/7 0007 : 14 :01
 * @project-name: songFox
 */
public class Dashboard extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int ANGLE = 120;
    private static final float RADIUS = DpUtils.dp2px(150);
    private static final float LENGTH = DpUtils.dp2px(100);
    private Path dash = new Path();
    private Path arc = new Path();
    private PathDashPathEffect effect;
    public Dashboard(Context context) {
        super(context);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DpUtils.dp2px(2));
        dash.addRect(0, 0, DpUtils.dp2px(2), DpUtils.dp2px(10), Path.Direction.CW);
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc,false);
        effect = new PathDashPathEffect(dash, (pathMeasure.getLength() - DpUtils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(effect);
        //画刻度
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        //画指针
        int currenAngle = 90;
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH + getHeight() /2,
                paint);
    }

    private int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }
}
