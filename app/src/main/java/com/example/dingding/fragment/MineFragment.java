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
import com.example.dingding.activity.CantReceiveActivity;
import com.example.dingding.activity.InvitationActivity;
import com.example.dingding.activity.MyCollectionActivity;
import com.example.dingding.activity.MyInfomationActivity;
import com.example.dingding.activity.MyRedPacketActivity;
import com.example.dingding.activity.RegisterActivity;
import com.example.dingding.activity.SettingActivity;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends Fragment {

    private ActivityUtils activityUtils; // Activity常用工具集

    @Bind(R.id.actionbar_mine)HMActionBar mHMActionBar;

    PopupMenu popup=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUtils = new ActivityUtils(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mine_fragment,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        setActionBar();

    }

    /**
     * 顶部标题栏
     * @author wangzan
     * @date 2016.06.27.
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("我的");
        mHMActionBar.setIvSearch();
        mHMActionBar.setIvMore();

    }

    /**
     * 我的资料界面
     */
    @OnClick(R.id.iv_mine_more)
    public void myInfomation(){
        activityUtils.startActivity(MyInfomationActivity.class);
    }


    /**
     * 我的红包界面
     */
    @OnClick(R.id.iv_mine_myhongbao)
    public void myRedPacket(){
        activityUtils.startActivity(MyRedPacketActivity.class);
    }

    /**
     * 我的收藏界面
     */
    @OnClick(R.id.iv_mine_myshoucang)
    public void myCollection(){
        activityUtils.startActivity(MyCollectionActivity.class);
    }

    /**
     * 邀请界面
     */
    @OnClick(R.id.iv_mine_invitation)
    public void invitation(){
        activityUtils.startActivity(InvitationActivity.class);
    }

    /**
     * 设置界面
     */
    @OnClick(R.id.iv_mine_setting)
    public void setting(){
        activityUtils.startActivity(SettingActivity.class);
    }
}
