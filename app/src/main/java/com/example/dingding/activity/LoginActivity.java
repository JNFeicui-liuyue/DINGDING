package com.example.dingding.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.dingding.R;
import com.example.dingding.ben.bean.LoginBean;
import com.example.dingding.ben.bean.LoginUpBean;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.connection.LoginConnection;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AutoLayoutActivity {

    private ActivityUtils activityUtils; // Activity常用工具集
    private PopupWindow mPopupWindow;
    private ProgressDialog mProgressDialog;
    private LoginConnection mLoginConnection;
    private LoginUpBean mLoginUpBean;
    private Handler mHandler;
    private static final String TAG = "LoginActivity";

    @Bind(R.id.tv_word)TextView mTvCountry;
    @Bind(R.id.login_edt_username)EditText telephone_login;
    @Bind(R.id.login_edt_pwd)EditText password_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);

        mHandler = new Handler(){
            public void handleMessage(Message msg){
                Bundle bundle = msg.getData();
                String info = bundle.getString("msg");
                switch (msg.what) {
                    case 0x1://异常信息
                        Log.d(TAG, "handleMessage: " + info);
//                        KL.e(info);
                        break;
                    case 0x2://请求成功
                        Log.d(TAG, "handleMessage: " + info);
//                        KL.d(info);
                        break;
                    case 0x3://请求失败
                        Log.d(TAG, "handleMessage: " + info);
//                        KL.d(info);
                        break;
                }
            }
        };
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    /**
     * 选择国家地区代码
     */
    @OnClick({R.id.tv_word,R.id.iv_login_country})
    public void selectCountryNumber(){
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, CountryActivity.class);
        startActivityForResult(intent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 12:
                if (resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String countryNumber = bundle.getString("countryNumber");
                    mTvCountry.setText(countryNumber);

                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 登录
     */
    @OnClick(R.id.login_btn)
    public void loginJump(){
        String telephone = telephone_login.getText().toString().trim();
        String password = password_login.getText().toString().trim();
        if (TextUtils.isEmpty(telephone) || TextUtils.isEmpty(password)){
            activityUtils.showToast("请输入手机号或密码");
        } else {
            mProgressDialog = ProgressDialog.show(this, "", "登陆中,请稍后...");

            mLoginUpBean = new LoginUpBean();
            mLoginUpBean.setAccount(telephone);
            mLoginUpBean.setUpwd(password);
            String gson = new Gson().toJson(mLoginUpBean);

            mLoginConnection = new LoginConnection(gson,mHandler);

            activityUtils.startActivity(MainActivity.class);
            finish();
        }
    }


    /**
     * 新用户注册
     */
    @OnClick(R.id.tv_newuser_register)
    public void setNewuser_register(){
        activityUtils.startActivity(RegisterActivity.class);
        finish();
    }

    /**
     * 更多
     */
    @OnClick(R.id.tv_more)
    public void showMore(){
        View contentview = LayoutInflater.from(LoginActivity.this).inflate(R.layout.popuplayout,null);
        mPopupWindow = new PopupWindow(contentview,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setContentView(contentview);
        View rootview = LayoutInflater.from(LoginActivity.this).inflate(R.layout.activity_login,null);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(rootview, Gravity.CENTER,0,0);

    }

}
