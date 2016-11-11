package com.example.dingding.ui.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dingding.R;
import com.example.dingding.ui.activity.SearchActivity;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集
    private PopupMenu popup=null;

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
     * 顶部标题栏
     */
    private  void setActionBar(){

        mHMActionBar.setBackIcon(R.mipmap.icon_org_invite_qrcode_logo);
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
}
