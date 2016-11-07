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

public class NoDistrubActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_no_distrub)HMActionBar mHMActionBar;
    @Bind(R.id.iv_start_distrub)ImageView mIvStartDistrub;
    @Bind(R.id.iv_close_distrub)ImageView mIvCloseDistrub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_distrub);
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
        mHMActionBar.setLeftText("勿扰模式");
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
     * 打开勿扰模式
     */
    @OnClick(R.id.iv_start_distrub)
    public void startDistrub(View view){
        view.setVisibility(View.GONE);
        mIvCloseDistrub.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭勿扰模式
     */
    @OnClick(R.id.iv_close_distrub)
    public void closeDistrub(View view){
        view.setVisibility(View.GONE);
        mIvStartDistrub.setVisibility(View.VISIBLE);
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
