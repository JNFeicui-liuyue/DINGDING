package com.example.dingding.ui.activity;

import android.os.Bundle;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchDINGActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ding);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    /**
     * 回退图标的监听事件
     */
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
