package cn.sxh.animation;

import android.animation.TypeEvaluator;

public class ViewEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int start = startValue;
        int end = endValue;
        int cur = (int) (start + fraction * (end - start));
        return cur;
    }
}
