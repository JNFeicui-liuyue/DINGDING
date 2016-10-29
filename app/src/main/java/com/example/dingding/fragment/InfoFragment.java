package com.example.dingding.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集

    PopupMenu popup=null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUtils = new ActivityUtils(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_info_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

    }

//    @OnClick(R.id.iv_more)
//    public void addMore(){
//
//    }

//    public void onPopupButtonClick(View iv)
//    {
//        //创建PopupMenu对象
//        popup=new PopupMenu(this,iv);
//        //将R.menu.popup_menu菜单资源加载到popup菜单中
//        getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
//        //为popup菜单的菜单项单击事件绑定事件监听器
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
//
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch(item.getItemId())
//                {
//                    case R.id.exit:
//                        //隐藏该对话框
//                        popup.dismiss();
//                        break;
//                    default:
//                        //使用Toast显示用户单击的菜单项
//                        Toast.makeText(PopupMenuTest.this, "您单击了【"+item.getTitle()+"】菜单项", Toast.LENGTH_SHORT).show();
//
//                }
//                // TODO Auto-generated method stub
//                return false;
//            }
//
//        });
//        popup.show();
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.popup_menu_test, menu);
//        return true;
//    }


}
