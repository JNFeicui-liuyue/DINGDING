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

public class AccountProtectActivity extends AutoLayoutActivity {


    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_account_protect)HMActionBar mHMActionBar;
    @Bind(R.id.iv_account_protect)ImageView mIvProtect;
    @Bind(R.id.iv_account_unprotect)ImageView mIvUnprotect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_protect);
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
        mHMActionBar.setLeftText("账号保护");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(AccountAndSecurityActivity.class);
                finish();
            }
        });
    }

    @OnClick(R.id.iv_account_protect)
    public void accountProtect(View view){
        view.setVisibility(View.GONE);
        mIvUnprotect.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_account_unprotect)
    public void accountUnprotect(View view){
        view.setVisibility(View.GONE);
        mIvProtect.setVisibility(View.VISIBLE);
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
                mActivityUtils.startActivity(AccountAndSecurityActivity.class);
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
