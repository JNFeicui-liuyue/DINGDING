package com.example.dingding.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集

    PopupMenu popup=null;

    @Bind(R.id.iv_more)ImageView mIvMore;
    @Bind(R.id.actionbar_message)HMActionBar mHMActionBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUtils = new ActivityUtils(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_message_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        setActionBar();

    }

    /**
     * 最右侧 + 按钮的点击事件，使用的是弹出PopupMenu
     * @param view
     */
    @OnClick(R.id.iv_more)
    public void addMore(View view){

        // 创建PopupMenu对象
        popup = new PopupMenu(getActivity(), view);
        // 将R.menu.popup_menu菜单资源加载到popup菜单中
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        // 为popup菜单的菜单项单击事件绑定事件监听器
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // 使用Toast显示用户单击的菜单项
                Toast.makeText(getActivity(),
                        "您单击了【" + item.getTitle() + "】菜单项"
                        , Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
//        mHMActionBar.setIvIcon();
//        mHMActionBar.setTvTitle();
        mHMActionBar.setBackIcon(R.mipmap.icon_org_invite_qrcode_logo);
        mHMActionBar.setIvSearch();
        mHMActionBar.setIvMore();

    }



}
