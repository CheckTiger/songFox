package cn.sxh.animation.one;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.sxh.animation.R;
import cn.sxh.common.widget.AvatarView;

public class AlphaScaleActivity extends AppCompatActivity implements View.OnClickListener {

    private AvatarView avatarView;
    private Button bt1,bt2,bt3, bt4;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_alpha_scale);
        initView();
    }

    private void initView() {
        avatarView = findViewById(R.id.animation_content);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt1) {
            animation = AnimationUtils.loadAnimation(AlphaScaleActivity.this, R.anim.alphaanim);
        } else if (id == R.id.bt2) {
            animation = AnimationUtils.loadAnimation(AlphaScaleActivity.this, R.anim.scaleanim);
        } else if (id == R.id.bt3) {
            animation = AnimationUtils.loadAnimation(AlphaScaleActivity.this, R.anim.rotateanim);
            LinearInterpolator interpolator = new LinearInterpolator();
            animation.setInterpolator(interpolator);
        } else if (id == R.id.bt4) {
            animation = AnimationUtils.loadAnimation(AlphaScaleActivity.this, R.anim.alphaanim);
        }
        avatarView.startAnimation(animation);
    }
}