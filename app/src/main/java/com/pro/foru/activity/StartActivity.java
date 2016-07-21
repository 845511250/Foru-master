package com.pro.foru.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pro.foru.foru.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends Activity {
    Context context;
    @BindView(R.id.splash_image)
    ImageView splash_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.activity_start);
        context = this;
        ButterKnife.bind(this);

        startAppAnim();
    }

    public void startAppAnim(){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.start_splash);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash_image.startAnimation(animation);
    }
}
