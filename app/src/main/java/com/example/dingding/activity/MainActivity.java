package com.example.dingding.activity;

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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主界面,下方导航栏
 */
public class MainActivity extends AppCompatActivity {

    private ActivityUtils activityUtils; // Activity常用工具集

    @Bind(R.id.fth_tabhost)FragmentTabHost fth_tabhost;
    @Bind(R.id.realtabcontent)FrameLayout mFrameLayout;

    /**
     * Fragment数组界面
     */
    private Class mFragmentArray [] = {InfoFragment.class, DINGFragment.class, WorkFragment.class ,
            LinkmanFragment.class, MineFragment.class};

    /**
     * 布局填充器
     */
    private LayoutInflater mLayoutInflater;

    /**
     *存放图片数组
     */
    private int mImageArray [] = {R.drawable.tab_message, R.drawable.tab_ding, R.drawable.tab_work,
            R.drawable.tab_linkman, R.drawable.tab_mine};

    /**
     * 选项卡文字
     */
    private String mTextArray[] = {"消息", "DING", "工作", "联系人", "我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        initDate();
    }

    private void initDate() {

        mLayoutInflater = LayoutInflater.from(this);
        fth_tabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        final int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = fth_tabhost.newTabSpec(mTextArray[i]).setIndicator(
                    getTabItemView(i));
            fth_tabhost.addTab(tabSpec,mFragmentArray[i],null);
            fth_tabhost.getTabWidget().setDividerDrawable(R.color.white);
        }
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = mLayoutInflater.inflate(R.layout.tab_item,null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(mImageArray[index]);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTextArray[index]);
        return view;

    }
}
