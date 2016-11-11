package com.example.dingding.ui.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.dingding.R;
import com.example.dingding.ui.activity.SearchActivity;
import com.example.dingding.adapter.WorkGridViewAdapter;
import com.example.dingding.adapter.WorkViewPagerAdapter;
import com.example.dingding.ben.bean.WorkModel;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WorkFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集
    private PopupMenu popup=null;
    private String [] titles = {"出勤人数","待我审批","签到人数","未读日志","日报","周报"};
    private ViewPager mPager;
    private List<View> mPagerList;
    private List<WorkModel> mDatas;
    private LinearLayout mLlDot;
    private LayoutInflater mInflater;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 4;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    @Bind(R.id.actionbar_work_fragment)HMActionBar mHMActionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUtils = new ActivityUtils(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_work_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        mPager = (ViewPager) view.findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) view.findViewById(R.id.ll_dot);

        //初始化数据源
        initDatas();
        mInflater = LayoutInflater.from(getContext());
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 /pageSize);
        mPagerList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) mInflater.inflate(R.layout.gridview_work_item,mPager,false);
            gridView.setAdapter(new WorkGridViewAdapter(getContext(),mDatas,i,pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int pos = i + curIndex * pageSize;
                    activityUtils.showToast(mDatas.get(pos).getName());
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new WorkViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();

        setActionBar();

    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
        mHMActionBar.setIvSearch(R.drawable.cspace_files_search);
        mHMActionBar.setIvMore(R.drawable.dentry_icon_more_update);

        //搜索图标的点击事件，跳转至搜索界面
        mHMActionBar.setIvSearchOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityUtils.startActivity(SearchActivity.class);
            }
        });

        //最右侧 + 按钮的点击事件，使用的是弹出PopupMenu
        mHMActionBar.setIvMoreOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 创建PopupMenu对象
                popup = new PopupMenu(getActivity(), view);
                // 将R.menu.popup_menu菜单资源加载到popup菜单中
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //若去掉此处，列表项前的小图标将不会显示！！！
                //http://www.apkbus.com/android-181878-1-1.html
                try {
                    Field mpopup=popup.getClass().getDeclaredField("mPopup");
                    mpopup.setAccessible(true);
                    MenuPopupHelper mPopup = (MenuPopupHelper) mpopup.get(popup);
                    mPopup.setForceShowIcon(true);
                } catch (Exception e) {

                }
                popup.show();

                // 为popup菜单的菜单项单击事件绑定事件监听器
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        // 使用Toast显示用户单击的菜单项
                        activityUtils.showToast( "您单击了【" + item.getTitle() + "】菜单项");
                        return true;
                    }
                });
            }
        });
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getContext().getPackageName());
            mDatas.add(new WorkModel(titles[i],imageId));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout(){
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(mInflater.inflate(R.layout.dot,null));
        }
        //默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                //圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                curIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
