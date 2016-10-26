package com.example.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeVerifyActivity extends AppCompatActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_codeverify)HMActionBar mHMActionBar;
    @Bind(R.id.ed_number1)EditText mEtNumber1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verify);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setActionBar();

        setEditTextAnimation();
    }

    /**
     * 顶部标题栏
     * @author wangzan
     * @date 2016.06.27.
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("填写验证码");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setRightImageResource(R.drawable.help);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(RegisterActivity.class);
                finish();
            }
        });
        mHMActionBar.setRightViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityUtils.startActivity(CantReceiveActivity.class);
                finish();
            }
        });

    }

    /**
     * 下一步,跳到设置登录密码界面
     */
    @OnClick(R.id.register_btn)
    public void codeVerify(){
        mActivityUtils.startActivity(SetLoginCodeActivity.class);
        finish();
    }

    /**
     * 设置验证码输入框的动画效果
     */
    public void setEditTextAnimation(){

        final AlphaAnimation alphaAnimation1 = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation1.setDuration(500);
        alphaAnimation1.setRepeatCount(Animation.INFINITE);
        alphaAnimation1.setRepeatMode(Animation.REVERSE);
        mEtNumber1.setAnimation(alphaAnimation1);
        alphaAnimation1.start();

        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation2.setDuration(500);
        alphaAnimation2.setRepeatCount(Animation.INFINITE);
        alphaAnimation2.setRepeatMode(Animation.REVERSE);
        mEtNumber1.setAnimation(alphaAnimation2);

        alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                alphaAnimation2.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        alphaAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                alphaAnimation1.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
