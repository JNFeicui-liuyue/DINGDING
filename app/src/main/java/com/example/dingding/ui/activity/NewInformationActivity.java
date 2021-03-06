package com.example.dingding.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewInformationActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_new_message)HMActionBar mHMActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_information);
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
        mHMActionBar.setLeftText("新消息通知");
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
     * 接收新消息通知
     */
    @OnClick(R.id.iv_receive_new_message)
    public void receiveNewMessage(){

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
