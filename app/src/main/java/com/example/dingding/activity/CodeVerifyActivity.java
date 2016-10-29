package com.example.dingding.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Array;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeVerifyActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_codeverify)HMActionBar mHMActionBar;
    @Bind(R.id.ed_number1)EditText mEtNumber1;
    @Bind(R.id.ed_number2)EditText mEtNumber2;
    @Bind(R.id.ed_number3)EditText mEtNumber3;
    @Bind(R.id.ed_number4)EditText mEtNumber4;

//    public EditText[] editText = { mEtNumber1 , mEtNumber2 , mEtNumber3 , mEtNumber4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verify);

        mActivityUtils = new ActivityUtils(this);

        //为四个验证码输入框设置自动向下跳转
        mEtNumber1.addTextChangedListener(watcher1);
        mEtNumber2.addTextChangedListener(watcher2);
        mEtNumber3.addTextChangedListener(watcher3);
        mEtNumber4.addTextChangedListener(watcher4);

        Timer timer = new Timer();
        //开启定时器
        timer.schedule(task, 0, 1500); // 1s后执行task,经过1s再次执行
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setActionBar();

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

    private TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable != null && editable.length() == 1){
            mEtNumber1.clearFocus();
            mEtNumber2.requestFocus();
        }
        }
    };

    private TextWatcher watcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable != null && editable.length() == 1){
                mEtNumber2.clearFocus();
                mEtNumber3.requestFocus();
            }
        }
    };

    private TextWatcher watcher3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable != null && editable.length() == 1){
                mEtNumber3.clearFocus();
                mEtNumber4.requestFocus();
            }
        }
    };

    private TextWatcher watcher4 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    /**
     * 设置验证码输入框的动画效果
     */
    Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if(mEtNumber1.isFocused()){
                    mEtNumber1.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.aa));
                }
                else  if(mEtNumber2.isFocused()){
                    mEtNumber2.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.aa));
                }else  if(mEtNumber3.isFocused()){
                    mEtNumber3.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.aa));
                }else  if(mEtNumber4.isFocused()){
                    mEtNumber4.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.aa));
                }

            }
            super.handleMessage(msg);
        };
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // 需要做的事:发送消息
            System.out.println("需要做的事:发送消息");
            Message message = new Message();
            message.what = 1;
            handler1.sendMessage(message);
        }
    };

}
