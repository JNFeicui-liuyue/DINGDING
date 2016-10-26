package com.example.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.example.dingding.PrivacyPolicyClickableSpan;
import com.example.dingding.R;
import com.example.dingding.ServiceAgreementClickableSpan;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_register)HMActionBar mHMActionBar;
    @Bind(R.id.tv_tiaokuan)TextView mPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setActionBar();

        initPolicy();

    }

    /**
     * 下一步,跳到验证码输入界面
     */
    @OnClick(R.id.register_btn)
    public void codeVerify(){
        mActivityUtils.startActivity(CodeVerifyActivity.class);
        finish();
    }

    /**
     * 条款
     */
    private void initPolicy() {

        String service = "服务协议";
        String privacy = "隐私政策";
        SpannableString spanservice = new SpannableString(service);
        SpannableString spanprivacy = new SpannableString(privacy);
        ClickableSpan clickservice = new ServiceAgreementClickableSpan(service,this);
        ClickableSpan clickpricy = new PrivacyPolicyClickableSpan(privacy,this);
        spanservice.setSpan(clickservice,0,service.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanprivacy.setSpan(clickpricy,0,privacy.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mPolicy.setText("即表示您同意《");
        mPolicy.append(spanservice);
        mPolicy.append("》和《");
        mPolicy.append(spanprivacy);
        mPolicy.append("》");
        mPolicy.setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * 顶部标题栏
     * @author wangzan
     * @date 2016.06.27.
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("新用户注册");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });

    }
}
