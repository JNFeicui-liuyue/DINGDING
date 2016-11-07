package com.example.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BlacklistActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_blacklist)HMActionBar mHMActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);
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
        mHMActionBar.setLeftText("黑名单");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(PrivacyActivity.class);
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
                mActivityUtils.startActivity(PrivacyActivity.class);
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
