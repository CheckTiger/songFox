package cn.sxh.animation.one;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.sxh.animation.CharEvaluator;
import cn.sxh.animation.R;
import cn.sxh.animation.ViewEvaluator;
import cn.sxh.common.widget.AvatarView;

public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private AvatarView mAvatarView;
    private TextView start,show, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        init();
    }

    private void init() {
        mAvatarView = findViewById(R.id.av);
        start = findViewById(R.id.start);
        show = findViewById(R.id.show);
        cancel = findViewById(R.id.cancel);
        initListener();
    }

    private void initListener() {
        start.setOnClickListener(this);
        show.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start) {
            initAnimation();
        } else if (id == R.id.show) {
            initValueAnimation();
        } else if (id == R.id.cancel) {
            playWithValueAnimation();
        }
    }

    private void initAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ViewEvaluator(),0, 200);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentProcess = (int) animation.getAnimatedValue();
                Log.e("sxh", "-------------->" + currentProcess);
                show.setText(String.valueOf(currentProcess));
            }
        });
        valueAnimator.start();
    }

    private void initValueAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(),'A', 'Z');
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char currentProcess = (char) animation.getAnimatedValue();
                Log.e("sxh", "-------------->" + currentProcess);
                show.setText(String.valueOf(currentProcess));
            }
        });
        valueAnimator.start();
    }

    private void playWithValueAnimation() {
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(show, "translationY", 0, 400,0);
        tv1TranslateY.setInterpolator(new BounceInterpolator());

        ObjectAnimator av = ObjectAnimator.ofFloat(mAvatarView, "translationX", 0, 400, -400,
                300,-300,200,-200,100,-100,50,-50,0);
        tv1TranslateY.setInterpolator(new BounceInterpolator());


        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(cancel, "translationY", 0, 400, 0);
        tv2TranslateY.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(tv2TranslateY).with(tv1TranslateY).before(av);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }


}