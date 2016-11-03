package com.example.dingding.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.View;

import com.example.dingding.R;
import com.example.dingding.ben.dialog.HeadPortraitDialog;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.example.dingding.fragment.MineFragment;
import com.example.library.dialog.AlertDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kale.ui.view.BaseEasyDialog;

public class MyInfomationActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;
    private HeadPortraitDialog mHeadPortraitDialog;
    private AlertDialog mAlertDialog;
    private BaseEasyDialog mBaseEasyDialog;

    @Bind(R.id.actionbar_my_info)HMActionBar mHMActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        setActionBar();
    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("我的资料");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startFragment(MineFragment.class);
                finish();
            }
        });
    }

    /**
     * 头像点击的dialog
     */
    @OnClick({R.id.rl_mine_more,R.id.iv_head_protrait,R.id.iv_mine_more})
    public void headProtrait(){

        mHeadPortraitDialog = new HeadPortraitDialog(this);
        mHeadPortraitDialog.setModifyOnclickListener(new HeadPortraitDialog.onModifyOnclickListener() {
            @Override
            public void onModifyClick() {
                mActivityUtils.startActivity(SelectPhotoActivity.class);
            }
        });
        mHeadPortraitDialog.show();

    }

    /**
     * 修改昵称的Dialog
     */
    @OnClick({R.id.rl_mine_nick,R.id.iv_mine_nick})
    public void modifyNick(){

        mAlertDialog = new AlertDialog(this);
//        mAlertDialog.
        mBaseEasyDialog = new BaseEasyDialog() {
            @Override
            protected <T extends View> T getView(@IdRes int id) {
                return super.getView(id);
            }
        };
    }

    /**
     * 物理回退
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                mActivityUtils.startFragment(MineFragment.class);
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
