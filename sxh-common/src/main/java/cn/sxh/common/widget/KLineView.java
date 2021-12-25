package cn.sxh.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

public class KLineView extends View {

    private Paint mLinePaint;//k线画笔
    private Paint mRectPaint;//矩形框线画笔

    private int mWidth;
    private int mHeight;


    public KLineView(Context context) {
        this(context, null);
    }

    public KLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(5);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int startY = (mHeight - mWidth) / 2;
        int stopY = (mHeight - mWidth) / 2 + mWidth;
        canvas.drawLine(10, startY, 10, stopY, mLinePaint);
        canvas.drawLine(10 + mWidth / 4, startY, mWidth / 4, stopY, mLinePaint);
        canvas.drawLine(10 + mWidth / 2, startY, 10 + mWidth / 2, stopY, mLinePaint);
        canvas.drawLine(10 + mWidth / 4 * 3, startY, 10 + mWidth / 4 * 3, stopY, mLinePaint);
        canvas.drawLine(mWidth - 10, startY, mWidth - 10, stopY, mLinePaint);


        int startX = 10;
        int stopX = mWidth - 10;

        canvas.drawLine(startX, startY , stopX, startY, mLinePaint);
        canvas.drawLine(startX, (mHeight - mWidth) / 2 + mWidth / 4, stopX, (mHeight - mWidth) / 2 + mWidth / 4, mLinePaint);
        canvas.drawLine(startX, (mHeight - mWidth) / 2 + mWidth / 2, stopX, (mHeight - mWidth) / 2 + mWidth / 2, mLinePaint);
        canvas.drawLine(startX, (mHeight - mWidth) / 2 + mWidth / 4 * 3, stopX, (mHeight - mWidth) / 2 + mWidth / 4 * 3, mLinePaint);
        canvas.drawLine(startX, (mHeight - mWidth) / 2 + mWidth, stopX, (mHeight - mWidth) / 2 + mWidth, mLinePaint);
    }
}
