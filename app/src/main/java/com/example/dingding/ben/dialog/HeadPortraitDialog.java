package com.example.dingding.ben.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dingding.R;

public class HeadPortraitDialog extends Dialog {

    private static final String TAG = "";
    private TextView mTvTitle;
    private TextView mTvModifyHeadProtrait;
    private TextView mTvRegainHeadProtrait;

    private HeadPortraitDialog.onModifyOnclickListener ModifyOnclickListener;//取消按钮被点击了的监听器
    private HeadPortraitDialog.onRegainOnclickListener RegainOnclickListener;//确定按钮被点击了的监听器

    private String tv;

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param onModifyOnclickListener
     */
    public void setModifyOnclickListener(HeadPortraitDialog.onModifyOnclickListener onModifyOnclickListener) {
//        if (str != null) {
//            noStr = str;
//        }
        this.ModifyOnclickListener = onModifyOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param onRegainOnclickListener
     */
    public void setRegainOnclickListener(HeadPortraitDialog.onRegainOnclickListener onRegainOnclickListener) {
//        if (str != null) {
//            yesStr = str;
//        }
        this.RegainOnclickListener = onRegainOnclickListener;
    }

    public HeadPortraitDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_portrait_dialog);

        //按空白处取消动画
        setCanceledOnTouchOutside(true);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvModifyHeadProtrait = (TextView) findViewById(R.id.tv_modify_head_portrait);
        mTvRegainHeadProtrait = (TextView) findViewById(R.id.tv_regain_head_portrait);

        tv = mTvModifyHeadProtrait.getText().toString().trim();
    }

    /**
     * 初始化界面控件
     */
    public void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvModifyHeadProtrait = (TextView) findViewById(R.id.tv_modify_head_portrait);
        mTvRegainHeadProtrait = (TextView) findViewById(R.id.tv_regain_head_portrait);

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        mTvModifyHeadProtrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ModifyOnclickListener != null) {
                    ModifyOnclickListener.onModifyClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        mTvRegainHeadProtrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RegainOnclickListener != null) {
                    RegainOnclickListener.onRegainClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

//        String text = mTvTitle.getText().toString().trim();
//        System.out.println("mTvTitle=============================="+text);
//        if (!TextUtils.isEmpty(text)){
//            mTvTitle.setVisibility(View.VISIBLE);
//        }

//        modify = mTvModifyHeadProtrait.getText().toString().trim();
//        regain = mTvRegainHeadProtrait.getText().toString().trim();
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onRegainOnclickListener {
        public void onRegainClick();
    }

    public interface onModifyOnclickListener {
        public void onModifyClick();
    }
}
