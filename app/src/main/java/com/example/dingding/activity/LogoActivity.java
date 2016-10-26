package com.example.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Logo界面
 */
public class LogoActivity extends AppCompatActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.iv_logo)ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setAnimation();
    }

    /**
     * 设置图片的动画效果
     */
    private void setAnimation() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            //动画启动时调用
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //动画重复时调用
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            //动画结束时调用
            @Override
            public void onAnimationEnd(Animation animation) {
                mActivityUtils.startActivity(LoginActivity.class);
                LogoActivity.this.finish();
            }
        });
        logo.setAnimation(animation);
    }
}
