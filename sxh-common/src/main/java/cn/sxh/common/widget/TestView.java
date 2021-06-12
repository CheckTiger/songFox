package cn.sxh.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.reset();
        path.addRect(getWidth()/2-150,getHeight()/2-300,
                getWidth()/2+150,
                getHeight()/2,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,150,Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(100,100,200,200,paint);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, DpUtils.dp2px(150), paint);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(path,paint);
    }
}
