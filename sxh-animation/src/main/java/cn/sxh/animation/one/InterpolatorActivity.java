package cn.sxh.animation.one;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.sxh.animation.R;
import cn.sxh.common.widget.AvatarView;

public class InterpolatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button accelerateDecelerate,accelerateInterpolator, anticipateInterpolator;
    private Button anticipateOvershootInterpolator,bounceInterpolator, cycleInterpolator;
    private Button decelerateInterpolator,linearInterpolator, overshootInterpolator;
    private Button alpha,scale, rotate,translate;

    private Animation animation;
    private AvatarView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        init();
    }

    private void init() {
        animation = AnimationUtils.loadAnimation(InterpolatorActivity.this, R.anim.alphaanim);
        imageView = findViewById(R.id.avatarView);
        alpha = findViewById(R.id.bt5);
        scale = findViewById(R.id.bt6);
        rotate = findViewById(R.id.bt7);
        translate = findViewById(R.id.bt8);
        accelerateDecelerate = findViewById(R.id.bt11);
        accelerateInterpolator = findViewById(R.id.bt12);
        anticipateInterpolator = findViewById(R.id.bt13);
        anticipateOvershootInterpolator = findViewById(R.id.bt14);
        bounceInterpolator = findViewById(R.id.bt15);
        cycleInterpolator = findViewById(R.id.bt16);
        decelerateInterpolator = findViewById(R.id.bt17);
        linearInterpolator = findViewById(R.id.bt18);
        overshootInterpolator = findViewById(R.id.bt19);
        accelerateDecelerate.setOnClickListener(this);
        accelerateInterpolator.setOnClickListener(this);
        anticipateInterpolator.setOnClickListener(this);
        anticipateOvershootInterpolator.setOnClickListener(this);
        bounceInterpolator.setOnClickListener(this);
        cycleInterpolator.setOnClickListener(this);
        decelerateInterpolator.setOnClickListener(this);
        linearInterpolator.setOnClickListener(this);
        overshootInterpolator.setOnClickListener(this);

        alpha.setOnClickListener(this);
        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        translate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt5) {
            animation = AnimationUtils.loadAnimation(InterpolatorActivity.this, R.anim.alphaanim);
            imageView.startAnimation(animation);
        }else if (id == R.id.bt6) {
            animation = AnimationUtils.loadAnimation(InterpolatorActivity.this, R.anim.scaleanim);
            imageView.startAnimation(animation);
        }else if (id == R.id.bt7) {
            animation = AnimationUtils.loadAnimation(InterpolatorActivity.this, R.anim.rotateanim);
            imageView.startAnimation(animation);
        }else if (id == R.id.bt8) {
            animation = AnimationUtils.loadAnimation(InterpolatorActivity.this, R.anim.translate);
            imageView.startAnimation(animation);
        }else if (id == R.id.bt11) {
            AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
            animation.setInterpolator(accelerateDecelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt12) {
            AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt13) {
            AnticipateInterpolator accelerateInterpolator = new AnticipateInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt14) {
            AnticipateOvershootInterpolator accelerateInterpolator = new AnticipateOvershootInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt15) {
            BounceInterpolator accelerateInterpolator = new BounceInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt16) {
            CycleInterpolator accelerateInterpolator = new CycleInterpolator(0.0f);
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt17) {
            DecelerateInterpolator accelerateInterpolator = new DecelerateInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt18) {
            LinearInterpolator accelerateInterpolator = new LinearInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        } else if (id == R.id.bt19) {
            OvershootInterpolator accelerateInterpolator = new OvershootInterpolator();
            animation.setInterpolator(accelerateInterpolator);
            imageView.startAnimation(animation);
        }

    }
}