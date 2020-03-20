package cn.sxh.technology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @package-name: cn.sxh.technology
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/3/20 0020 : 17 :23
 * @project-name: songFox
 */
public class LineView extends View {

    private int count = 6;  //数据个数
    private Paint linePaint; //雷达区画笔
    private int centerX;                  //中心X
    private int centerY;                  //中心Y



    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Path path = new Path();
        path.moveTo(centerX , centerY);
        path.lineTo(centerX + 100, centerY + 100);
        path.lineTo(centerX + 100, centerY );
        linePaint.setColor(Color.RED);
        canvas.drawLine(0,centerY,centerX*2,centerY,linePaint);
        canvas.drawLine(centerX,0,centerX,centerY*2,linePaint);
        linePaint.setColor(Color.GRAY);
        path.close();
        canvas.drawPath(path,linePaint);
    }

}
