package com.example.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivacyActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_privacy)HMActionBar mHMActionBar;
    @Bind(R.id.iv_check_info)ImageView mIvCheckInfo;
    @Bind(R.id.iv_uncheck_info)ImageView mIvUncheckInfo;
    @Bind(R.id.iv_match_addlist)ImageView mIvMatchAddlist;
    @Bind(R.id.iv_unmatch_addlist)ImageView mIvUnmatchAddlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setActionBar();
    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("隐私");
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
     * 打开允许非好友查看我的认证企业信息
     */
    @OnClick(R.id.iv_check_info)
    public void checkInfo(View view){
        view.setVisibility(View.GONE);
        mIvUncheckInfo.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭允许非好友查看我的认证企业信息
     */
    @OnClick(R.id.iv_uncheck_info)
    public void uncheckInfo(View view){
        view.setVisibility(View.GONE);
        mIvCheckInfo.setVisibility(View.VISIBLE);
    }

    /**
     * 打开手机通讯录匹配
     */
    @OnClick(R.id.iv_match_addlist)
    public void matchAddlist(View view){
        view.setVisibility(View.GONE);
        mIvUnmatchAddlist.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭手机通讯录匹配
     */
    @OnClick(R.id.iv_unmatch_addlist)
    public void unmatchAddlist(View view){
        view.setVisibility(View.GONE);
        mIvMatchAddlist.setVisibility(View.VISIBLE);
    }

    /**
     * 黑名单界面
     */
    @OnClick({R.id.tv_privacy_blacklist,R.id.rl_privacy_blacklist,R.id.iv_blacklist})
    public void AccountAndSecurity(){
        mActivityUtils.startActivity(BlacklistActivity.class);
        finish();
    }


    /**
     * 共享手机号码界面
     */
    @OnClick({R.id.rl_privacy_share_phonenumber,R.id.tv_privacy_share_phonenumber,R.id.iv_share_phonenumber})
    public void NewInformation(){
        mActivityUtils.startActivity(SharePhonenumberActivity.class);
        finish();
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
