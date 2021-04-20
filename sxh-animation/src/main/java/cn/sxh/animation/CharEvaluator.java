package cn.sxh.animation;

import android.animation.TypeEvaluator;

public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int start = startValue;
        int end = endValue;
        int current = (int) (start + fraction * (end - start));

        return (char) current;
    }
}
