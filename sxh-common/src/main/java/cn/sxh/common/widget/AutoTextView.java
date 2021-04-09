package cn.sxh.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;

import cn.sxh.common.R;

/**
 * 自动适配字体大小的文本伸缩控件
 */
public class AutoTextView extends androidx.appcompat.widget.AppCompatTextView {

    protected static float MIN_SIZE = 0.7F;
    protected Paint textPaint;
    protected float defaultTextSize;
    protected float minTestSize;

    protected float largeSize;
    protected float smallSize;

    public AutoTextView(Context context) {
        this(context,null);
    }

    public AutoTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.textPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoTextView);
        this.minTestSize = a.getDimension(R.styleable.AutoTextView_minTextSize,
                this.getTextSize() * MIN_SIZE);
        this.defaultTextSize = a.getDimension(R.styleable.AutoTextView_defaultTextSize,
                this.getTextSize());
        a.recycle();

        this.largeSize = defaultTextSize;
        this.smallSize = minTestSize;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        this.fitText(text.toString(),this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            this.fitText(getText().toString(), w);
        }
    }

    private void fitText(String text, int textWidth) {
        if (textWidth <= 0 || text == null || text.length() == 0) {
            return;
        }

        int target = textWidth - this.getPaddingLeft() - this.getPaddingRight();
        final float threshold = 0.01f;
        this.largeSize = defaultTextSize == 0f ? this.getTextSize() : defaultTextSize;
        this.smallSize = minTestSize;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, this.largeSize);
        this.textPaint.set(this.getPaint());
        if (this.textPaint.measureText(text) <= target) {
            return;
        }

        while ((this.largeSize - this.smallSize) > threshold) {
            float size = (this.largeSize + this.smallSize) / 2;
            this.textPaint.setTextSize(size);
            if (this.textPaint.measureText(text) >= target) {
                this.largeSize = size;
            } else {
                this.smallSize = size;
            }
        }
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.smallSize);
    }
}
