package com.example.dingding.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.view.fragmentTabHost.FragmentTabHost;
import com.example.dingding.fragment.DINGFragment;
import com.example.dingding.fragment.InfoFragment;
import com.example.dingding.fragment.LinkmanFragment;
import com.example.dingding.fragment.MineFragment;
import com.example.dingding.fragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面,下方导航栏
 */
public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragmentList;
    private Class mClass[] = {InfoFragment.class,
            DINGFragment.class, WorkFragment.class ,
            LinkmanFragment.class, MineFragment.class};

    /**
     * Fragment数组界面
     */
    private Fragment mFragment[] = {new InfoFragment(),new DINGFragment(),
            new WorkFragment(), new LinkmanFragment(),new MineFragment()};

    /**
     * 选项卡文字
     */
    private String mTitles[] = {"消息", "DING", "工作", "联系人", "我的"};

    /**
     *存放图片数组
     */
    private int mImageArray [] = {R.drawable.tab_message, R.drawable.tab_ding,
            R.drawable.tab_work, R.drawable.tab_linkman, R.drawable.tab_mine};

    private android.support.v4.app.FragmentTabHost mTabhost;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        initView();
        initEvent();

    }

    /**
     * 初始化视图控件
     */
    private void initView() {
        mTabhost = (android.support.v4.app.FragmentTabHost) findViewById(android.R.id.tabhost);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mFragmentList = new ArrayList<>();

        mTabhost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        mTabhost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragment.length; i++) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            mTabhost.addTab(tabSpec,mClass[i],null);
            mFragmentList.add(mFragment[i]);
            mTabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    /**
     * 为Tabhost跟ViewPager设置监听
     */
    private void initEvent(){

        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                mViewPager.setCurrentItem(mTabhost.getCurrentTab());
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mTabhost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabView(int index){
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(mImageArray[index]);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTitles[index]);

        return view;

    }
}
