package com.example.dingding.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AlertDialog;

import com.example.dingding.R;
import com.example.dingding.ben.dialog.HeadPortraitDialog;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.dialog.ModifyNickDialog;
import com.example.dingding.ben.utils.HMActionBar;
import com.example.dingding.fragment.MineFragment;
import com.zhy.autolayout.AutoLayoutActivity;
import cn.qqtheme.framework.picker.DatePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.Calendar;

public class MyInfomationActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;
    private HeadPortraitDialog mHeadPortraitDialog;
    private ModifyNickDialog mModifyNickDialog;
    private Calendar calendar = Calendar.getInstance();
    private static final String TAG = "MyInfomationActivity";

    public static String nick;

    @Bind(R.id.actionbar_my_info)HMActionBar mHMActionBar;
    @Bind(R.id.tv_myinfo_nick)TextView mTvNick;

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

        mModifyNickDialog = new ModifyNickDialog(MyInfomationActivity.this);
        mModifyNickDialog.setYesOnclickListener("确定", new ModifyNickDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Toast.makeText(MyInfomationActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                mModifyNickDialog.dismiss();
                //设置昵称的值
                mTvNick.setText(nick);

            }
        });
        mModifyNickDialog.setNoOnclickListener("取消", new ModifyNickDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                Toast.makeText(MyInfomationActivity.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                mModifyNickDialog.dismiss();
            }
        });
        mModifyNickDialog.show();

    }

    /**
     * 性别选择的Dialog
     */
    @OnClick({R.id.rl_mine_sex,R.id.tv_sex,R.id.iv_mine_sex})
    public void choseSex(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(MyInfomationActivity.this);
        builder.setTitle("性别");
        final String [] sex = {"男","女"};
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合
         * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */
        builder.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MyInfomationActivity.this, "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 生日选择
     */
    @OnClick({R.id.rl_mine_birthday,R.id.tv_birthday,R.id.iv_mine_birthday})
    public void choseBrithday(View view){

        DatePicker picker = new DatePicker(this);
        picker.setRange(1900,2100);
        picker.setSelectedItem(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        picker.setGravity(17);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year,String month, String day) {
                mActivityUtils.showToast(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 地区选择
     */
    @OnClick({R.id.rl_mine_region,R.id.tv_region,R.id.iv_mine_region})
    public void choseRegion(View view){
        mActivityUtils.startActivity(RegionActivity.class);
        finish();
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
