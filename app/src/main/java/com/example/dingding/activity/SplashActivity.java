package com.example.dingding.activity;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dingding.R;
import com.example.dingding.adapter.SplashAdapter;
import com.example.dingding.ben.commons.ActivityUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 引导界面
 */
public class SplashActivity extends AutoLayoutActivity {

    @Bind(R.id.viewpager)ViewPager mViewPager;

    private ActivityUtils mActivityUtils;
    private ImageView[] points = new ImageView[3];
    private SplashAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mActivityUtils = new ActivityUtils(this);

        //xml文件存储  偏好－－音乐 音效 皮肤
        SharedPreferences preferences=getSharedPreferences("runconfig", MODE_PRIVATE);
        //从文件中获取存储的数据，默认为true
        boolean isFirst = preferences.getBoolean("isFirstRun", true);
        //如果不是第一次打开，则直接跳转到Logo界面
        if(!isFirst){
            mActivityUtils.startActivity(LogoActivity.class);
            finish();
            return;
        }
        points[0]=(ImageView) findViewById(R.id.iv_p1);
        points[1]=(ImageView) findViewById(R.id.iv_p2);
        points[2]=(ImageView) findViewById(R.id.iv_p3);
        setPoint(0);
        //初始化控件
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //设置每一个具体界面的样式
        List<View> viewList=new ArrayList<View>();
        viewList.add(getLayoutInflater().inflate(R.layout.lead_1, null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_2, null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead_3, null));
        //初始化适配器
        mAdapter=new SplashAdapter(viewList);
        //设置适配器
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(listener);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

    }

    private void setPoint(int index) {
        for (int i = 0; i < points.length; i++) {
            if(i==index){
                points[i].setAlpha(255);
            }else{
                points[i].setAlpha(100);
            }
        }
    }

    private ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {

        /**
         * 当界面切换后调用
         */
        @Override
        public void onPageSelected(int arg0) {
            setPoint(arg0);
            if(arg0>=2){
                mActivityUtils.startActivity(LogoActivity.class);
                finish();
                SharedPreferences preferences=getSharedPreferences("runconfig", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("isFirstRun", false);
                editor.commit();
            }
        }

        /**
         * 界面切换时调用
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {		}

        /**
         * 滑动状态变化时调用
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {  }
    };
}
