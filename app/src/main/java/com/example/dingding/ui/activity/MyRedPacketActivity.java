package com.example.dingding.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.example.dingding.R;
import com.example.dingding.adapter.MyReadFragmentAdapter;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ui.fragment.MyReceiveFragment;
import com.example.dingding.ui.fragment.MySendFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRedPacketActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;
    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    @Bind(R.id.tabLayout) TabLayout mTabLayout;
    @Bind(R.id.viewPager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_red_packet);

        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        ButterKnife.bind(this);

        initView();

        MyReadFragmentAdapter adapter = new MyReadFragmentAdapter(getSupportFragmentManager(),
                mTitle,mFragment);
        mViewPager.setAdapter(adapter);
        //为TabLayout设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        //使用ViewPager适配器
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    //初始化视图
    private void initView() {
        mTitle.add("我收到的");
        mTitle.add("我发出的");

        mFragment.add(new MyReceiveFragment());
        mFragment.add(new MySendFragment());
    }

    /**
     * 回退图标的监听事件
     */
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }

    /**
     * 使用帮助图标的点击事件
     */
    @OnClick(R.id.iv_help)
    public void help(){
        mActivityUtils.startActivity(HelpActivity.class);
        finish();
    }
}
