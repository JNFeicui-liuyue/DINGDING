package com.example.dingding.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.example.dingding.ui.fragment.MineFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountAndSecurityActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_account_security)HMActionBar mHMActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_and_security);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setActionBar();
    }

    /**
     * 账号保护界面
     */
    @OnClick({R.id.rl_account_security,R.id.iv_account_security,R.id.tv_protected,
                R.id.tv_word1,R.id.tv_word2})
    public void accountProtect(){
        mActivityUtils.startActivity(AccountProtectActivity.class);
        finish();
    }

    /**
     * 更换手机号界面
     */
    @OnClick({R.id.rl_update_new_number,R.id.tv_update_new_number,R.id.iv_phone_number})
    public void updatePhoneNumber(){
        mActivityUtils.startActivity(InputNewNumberActivity.class);
        finish();
    }

    /**
     * 安全密码锁定界面
     */
    @OnClick({R.id.rl_password_lock,R.id.tv_password_lock,R.id.tv_unstart,R.id.iv_password_lock})
    public void securityPwdLock(){
        mActivityUtils.startActivity(SecurityPwdLockActivity.class);
        finish();
    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("账号与安全");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(SettingActivity.class);
                finish();
            }
        });
    }

    /**
     * 物理回退
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                mActivityUtils.startActivity(SettingActivity.class);
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
