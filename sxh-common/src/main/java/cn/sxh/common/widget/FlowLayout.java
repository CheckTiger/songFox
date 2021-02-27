package cn.sxh.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {


    private List<List<View>> allLines;
    private List<Integer> lineHeights = new ArrayList<>();

    private int spaceIng1 = 16;
    private int spaceIng2 = 16;


    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void initMeasureParams() {
        allLines = new ArrayList();
        lineHeights = new ArrayList<>();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int lineCount = allLines.size();


        int curLeft = getPaddingLeft();
        int curTop = getPaddingTop();
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = allLines.get(i);
            int height = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curLeft;
                int top = curTop;

                int right = left + view.getMeasuredWidth();//自己测量布局之后设置才能获取到的
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curLeft = right + spaceIng1;
            }

            curTop = curTop + height;
            curLeft = getPaddingLeft();
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        initMeasureParams();

        int childCount = getChildCount();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int linesWidth = 0;
        int linesHeight = 0;

        int parentNeedWidth = 0;
        int parentNeedHeight = 0;

        List<View> lineViews = new ArrayList<>();


        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLp = childView.getLayoutParams();

            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    paddingLeft + paddingRight, childLp.width);

            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    paddingBottom + paddingTop, childLp.height);

            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);


            int childMeasureWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();

            Log.e("sxq", "linesWidth------->" + linesWidth);
            Log.e("sxq", "childMeasureWidth------->" + childMeasureWidth);
            Log.e("sxq", "selfWidth------->" + selfWidth);
            if (linesWidth + childMeasureWidth > selfWidth) {

                allLines.add(lineViews);
                lineHeights.add(linesHeight);

                parentNeedWidth = Math.max(parentNeedWidth, linesWidth);
                parentNeedHeight = parentNeedHeight + linesHeight + spaceIng2;

                lineViews = new ArrayList<>();
                linesWidth = 0;
                linesHeight = 0;
            }

            lineViews.add(childView);


            linesWidth = linesWidth + childMeasureWidth + spaceIng1;
            linesHeight = Math.max(linesHeight + spaceIng2, childMeasureHeight);
        }

        int WidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int HeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (WidthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        int realHeight = (HeightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;


        setMeasuredDimension(realWidth, realHeight);

    }
}
