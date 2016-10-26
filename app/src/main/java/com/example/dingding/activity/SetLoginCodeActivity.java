package com.example.dingding.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SetLoginCodeActivity extends AppCompatActivity {

    private ActivityUtils mActivityUtils;
    @Bind(R.id.actionbar_set_logincode)HMActionBar mHMActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_login_code);
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
     * @author wangzan
     * @date 2016.06.27.
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("设置登录密码");
        mHMActionBar.setRightViewBtn("跳过");
        mHMActionBar.setRightViewBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityUtils.startActivity(MainActivity.class);
            }
        });

    }
}
