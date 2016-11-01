package com.example.dingding.ben.utils;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dingding.R;


/**
 * Custom Common ActionBar
 */
public class HMActionBar extends RelativeLayout {

    private Context mContext;

    private View mRootView;

    private TextView mLeftTextView;
    private TextView mTvTitle;
    private ImageView mLeftImageView;
    private ImageView mRightViewImage;
    private ImageView mRightViewImage2;
    private ImageView mImageViewBack;
    private ImageView mIvIcon;
    private ImageView mIvSearch;
    private ImageView mIvMore;
    private Button mRightViewBtn;
    private View mView;

    private TextView mTitleView;

    private TextView mRightView;

    public HMActionBar(Context context) {
        super(context);
        init(context);
    }

    public HMActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HMActionBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.layout_actionbar, this, true);
        findViews();
    }

    private void findViews() {
        mLeftTextView = (TextView) mRootView.findViewById(R.id.actionbar_left_text);
        mTvTitle = (TextView) mRootView.findViewById(R.id.title);
        mLeftImageView = (ImageView) mRootView.findViewById(R.id.actionbar_left_image);
        mTitleView = (TextView) mRootView.findViewById(R.id.actionbar_title);
        mRightView = (TextView) mRootView.findViewById(R.id.actionbar_right);
        mRightViewImage = (ImageView) mRootView.findViewById(R.id.actionbar_right_image);
        mRightViewImage2 = (ImageView) mRootView.findViewById(R.id.actionbar_right_image2);
        mImageViewBack = (ImageView) mRootView.findViewById(R.id.iv_back);
        mIvIcon = (ImageView) mRootView.findViewById(R.id.imageView);
        mIvSearch = (ImageView) mRootView.findViewById(R.id.iv_search);
        mIvMore = (ImageView) mRootView.findViewById(R.id.iv_more);
        mRightViewBtn = (Button) mRootView.findViewById(R.id.actionbar_right_btn);
        mView = mRootView.findViewById(R.id.actionbar_line);
        setListeners();
    }

    public void setListeners(){
        mLeftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)mContext).finish();
            }
        });
    }

    public void setBackIcon(int resId){
        if (mImageViewBack != null){
            mImageViewBack.setImageResource(resId);
            mImageViewBack.setVisibility(VISIBLE);
        }

    }

    public void setViewOccur(){
        if (mView != null){
            mView.setVisibility(VISIBLE);
        }
    }

    public void setIvIcon(){
        if (mIvIcon != null){
//            mIvIcon.setImageResource(resId);
            mIvIcon.setVisibility(VISIBLE);
        }

    }

    public void setIvSearch(){
        if (mIvSearch != null){
//            mIvSearch.setImageResource(resId);
            mIvSearch.setVisibility(VISIBLE);
        }

    }

    public void setIvMore(){
        if (mIvMore != null){
//            mIvMore.setImageResource(resId);
            mIvMore.setVisibility(VISIBLE);
        }

    }

    public void setBackIconOnClickListener(OnClickListener listener) {
        if (mImageViewBack != null) {
            this.mImageViewBack.setOnClickListener(listener);
        }
    }

    public void setIvSearchOnClickListener(OnClickListener listener) {
        if (mImageViewBack != null) {
            this.mImageViewBack.setOnClickListener(listener);
        }
    }

    public void setIvMoreOnClickListener(OnClickListener listener) {
        if (mImageViewBack != null) {
            this.mImageViewBack.setOnClickListener(listener);
        }
    }

    public void setLeftText(String text) {
        if (mLeftTextView != null) {
            mLeftTextView.setText(text);
            mLeftTextView.setVisibility(View.VISIBLE);
        }
    }

    public void setTvTitle() {
        if (mTvTitle != null) {
//            mTvTitle.setText(text);
            mTvTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setLeftImageResource(int resId) {
        if (mLeftImageView != null) {
            mLeftImageView.setImageResource(resId);
            mLeftImageView.setVisibility(View.VISIBLE);
        }
    }

    public void setRightImageResource(int resId) {
        if (mRightViewImage != null) {
            mRightViewImage.setImageResource(resId);
            mRightViewImage.setVisibility(View.VISIBLE);
        }
    }

    public void setRightImageResource2(int resId) {
        if (mRightViewImage2 != null) {
            mRightViewImage2.setImageResource(resId);
            mRightViewImage2.setVisibility(View.VISIBLE);
        }
    }

    public void setRightViewBtn(String text){
        if (mRightViewBtn != null){
            mRightViewBtn.setText(text);
            mRightViewBtn.setVisibility(VISIBLE);
        }
    }

    public void setTitle(int resId) {
        if (mTitleView != null) {
            mTitleView.setText(resId);
            mTitleView.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(String title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
            mTitleView.setVisibility(View.VISIBLE);
        }
    }

    public void setRightText(int resId) {
        if (mRightView != null) {
            mRightView.setText(resId);
            mRightView.setVisibility(View.VISIBLE);
        }
    }

    public void setRightText(String text) {
        if (mRightView != null) {
            mRightView.setText(text);
            mRightView.setVisibility(View.VISIBLE);
        }
    }

    public void setLeftTextOnClickListener(OnClickListener listener) {
        if (mLeftTextView != null) {
            this.mLeftTextView.setOnClickListener(listener);
        }
    }

    public void setLeftImageOnClickListener(OnClickListener listener) {
        if (mLeftImageView != null) {
            this.mLeftImageView.setOnClickListener(listener);
        }
    }

    public void setRightViewOnClickListener(OnClickListener listener) {
        if (mRightView != null) {
            this.mRightView.setOnClickListener(listener);
        }
    }

    public void setRightViewBtnOnClickListener(OnClickListener listener){
        if (mRightViewBtn != null){
            this.mRightViewBtn.setOnClickListener(listener);
        }
    }

    public void setRightViewImageOnClickListener(OnClickListener listener) {
        if (mRightViewImage != null) {
            this.mRightViewImage.setOnClickListener(listener);
        }
    }

    public void setRightViewImageOnClickListener2(OnClickListener listener) {
        if (mRightViewImage2 != null) {
            this.mRightViewImage2.setOnClickListener(listener);
        }
    }

    public void setRightTextOnClickListener(OnClickListener listener) {
        if (mRightView != null) {
            this.mRightView.setOnClickListener(listener);
        }
    }

}
