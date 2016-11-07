package com.example.dingding.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
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

public class WorkFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集

    private PopupMenu mPopupMenu;


    @Bind(R.id.iv_more)ImageView mIvMore;
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

        setActionBar();

    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
//        mHMActionBar.setIvIcon();
//        mHMActionBar.setTvTitle();
//        mHMActionBar.setBackIcon(R.mipmap.icon_org_invite_qrcode_logo);
        mHMActionBar.setIvSearch();
        mHMActionBar.setIvMore();

    }

    /**
     * 搜索图标的点击事件，跳转至搜索界面
     */
    @OnClick(R.id.iv_search)
    public void search(){
        activityUtils.startActivity(SearchActivity.class);
    }
}
