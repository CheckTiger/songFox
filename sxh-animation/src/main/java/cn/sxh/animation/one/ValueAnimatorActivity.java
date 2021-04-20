package cn.sxh.animation.one;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.sxh.animation.CharEvaluator;
import cn.sxh.animation.R;
import cn.sxh.animation.ViewEvaluator;
import cn.sxh.common.widget.AvatarView;

public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private AvatarView mAvatarView;
    private TextView start,show, cancel;
    private Button mMenuButton;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;
    private boolean mIsMenuOpen = false;
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

        mMenuButton = (Button) findViewById(R.id.menu);
        mMenuButton.setOnClickListener(this);

        mItemButton1 = (Button) findViewById(R.id.item1);
        mItemButton1.setOnClickListener(this);

        mItemButton2 = (Button) findViewById(R.id.item2);
        mItemButton2.setOnClickListener(this);

        mItemButton3 = (Button) findViewById(R.id.item3);
        mItemButton3.setOnClickListener(this);

        mItemButton4 = (Button) findViewById(R.id.item4);
        mItemButton4.setOnClickListener(this);

        mItemButton5 = (Button) findViewById(R.id.item5);
        mItemButton5.setOnClickListener(this);

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
        } else if (v == mMenuButton) {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true;
                doAnimateOpen(mItemButton1, 0, 5, 300);
                doAnimateOpen(mItemButton2, 1, 5, 300);
                doAnimateOpen(mItemButton3, 2, 5, 300);
                doAnimateOpen(mItemButton4, 3, 5, 300);
                doAnimateOpen(mItemButton5, 4, 5, 300);
            } else {
                mIsMenuOpen = false;
                doAnimateClose(mItemButton1, 0, 5, 300);
                doAnimateClose(mItemButton2, 1, 5, 300);
                doAnimateClose(mItemButton3, 2, 5, 300);
                doAnimateClose(mItemButton4, 3, 5, 300);
                doAnimateClose(mItemButton5, 4, 5, 300);
            }
        } else {
            Toast.makeText(this, "你点击了" + v, Toast.LENGTH_SHORT).show();
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

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90)/(total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        //动画周期为500ms
        set.setDuration(1 * 500).start();
    }


    private void doAnimateClose(final View view, int index, int total,
                                int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 1),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 1),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0.1f));

        set.setDuration(500).start();
    }




}