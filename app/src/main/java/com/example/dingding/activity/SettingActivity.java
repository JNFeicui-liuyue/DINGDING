package com.example.dingding.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.example.dingding.fragment.MineFragment;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AutoLayoutActivity {

    private ActivityUtils activityUtils;
    private NormalDialog mDialog;

    @Bind(R.id.actionbar_setting)HMActionBar mHMActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        activityUtils = new ActivityUtils(this);
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
        mHMActionBar.setLeftText("设置");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtils.startFragment(MineFragment.class);
                finish();
            }
        });

    }

    /**
     * 账号与安全界面
     */
    @OnClick({R.id.rl_setting,R.id.iv_setting_more})
    public void AccountAndSecurity(){
        activityUtils.startActivity(AccountAndSecurityActivity.class);
        finish();
    }


    /**
     * 新消息通知界面
     */
    @OnClick({R.id.rl_setting_new_info,R.id.iv_setting_newinfo})
    public void NewInformation(){
        activityUtils.startActivity(NewInformationActivity.class);
        finish();
    }

    /**
     * 勿扰模式界面
     */
    @OnClick({R.id.rl_setting_nodistrub,R.id.iv_setting_nodietrub})
    public void NoDistrub(){
        activityUtils.startActivity(NoDistrubActivity.class);
        finish();
    }

    /**
     * 隐私界面
     */
    @OnClick({R.id.rl_setting_sercet,R.id.iv_setting_sercet})
    public void Privacy(){
        activityUtils.startActivity(PrivacyActivity.class);
        finish();
    }

    /**
     * 通用界面
     */
    @OnClick({R.id.rl_setting_universally,R.id.iv_setting_universally})
    public void Universal(){
        activityUtils.startActivity(UniversalActivity.class);
        finish();
    }

    /**
     * 关于钉钉界面
     */
    @OnClick({R.id.rl_setting_dingding,R.id.iv_setting_dingding})
    public void AboutDingding(){
        activityUtils.startActivity(AboutDingdingActivity.class);
        finish();
    }

    /**
     * dialog弹窗确认退出登录
     */
    @Override
    public void onBackPressed() {
        mDialog.show();
    }
    @OnClick({R.id.rl_setting_exit,R.id.tv_exit})
    public void ExitDialog(){

        mDialog = new NormalDialog(this);
        mDialog.setOnBtnClickL(new OnBtnClickL() {
            /**左*/
            @Override
            public void onBtnClick() {
                mDialog.dismiss();
            }
        }, new OnBtnClickL() {
            /**右*/
            @Override
            public void onBtnClick() {
                mDialog.dismiss();
                activityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });
        mDialog.content("您确定要退出登录嘛？");
        mDialog.show();

//        final AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setMessage("您确定要退出登录嘛？")
//                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                        activityUtils.startActivity(LoginActivity.class);
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        dialogInterface.dismiss();
//                    }
//                });
//        builder.create().show();


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
                activityUtils.startFragment(MineFragment.class);
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
