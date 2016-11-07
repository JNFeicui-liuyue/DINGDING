package com.example.dingding.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dingding.R;
import com.example.dingding.activity.SearchActivity;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DINGFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集

    private PopupMenu mPopupMenu;


//    @Bind(R.id.iv_more)ImageView mIvMore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUtils = new ActivityUtils(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dingfragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);


    }

    /**
     * 全部按钮的点击事件
     */
    @OnClick(R.id.tv_all)
    public void all(View view){

        // 创建PopupMenu对象
        mPopupMenu = new PopupMenu(getActivity(),view);
        // 将R.menu.popup_menu菜单资源加载到popup菜单中
        mPopupMenu.getMenuInflater().inflate(R.menu.popup_menu_all,mPopupMenu.getMenu());
        mPopupMenu.show();
        // 为popup菜单的菜单项单击事件绑定事件监听器
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                activityUtils.showToast( "您单击了【" + item.getTitle() + "】菜单项");
                return true;
            }
        });
    }

    /**
     * 搜索图标的点击事件，跳转至搜索界面
     */
    @OnClick(R.id.iv_search)
    public void search(){
        activityUtils.startActivity(SearchActivity.class);
    }

}
