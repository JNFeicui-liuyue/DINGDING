package com.example.dingding.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类名：com.house.base.BaseFragement.class
 * 描述：应用程序基类
 * Created by 刘帅 on 2016/8/9.
 */
public abstract class BaseFragement_StopUse extends Fragment implements View.OnClickListener {
    private View mContextView = null;

    /**
     * 注意：这里的boolean，每个fragement都继承BaseFragement，当fragement初始化时，BaseFragement便完成一次初始化
     * ，这样就可以实现每个fragement都可以单独的进行显示状态的设置
     * **/

    /** 是否允许快速点击 **/
    public boolean allowQuick = false;

    /** 快速点击周期 **/
    private long lastClick = 0;

    /** 获取当前包名的TAG **/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContextView = inflater.inflate(initLayout(), container, false);
        initView(mContextView);
        doBusiness(getActivity());
        return mContextView;
    }



    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetOnClick(v);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(View view, int resId) {
        return (T) view.findViewById(resId);
    }


    /**
     * [是否允许快速点击转]
     *
     * @param allowQuick
     */
    public void setAllowQuick(boolean allowQuick) {
        this.allowQuick = allowQuick;
    }

    /**
     * [日志输出]
     *
     * msg
     */
    protected void $Log(String msg) {
        if (BaseApplication.getInstance().isDebug) {
            Log.i(BaseApplication.APP_NAME, msg);
        }
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        if (!allowQuick) {// 不允许快速点击
            if (System.currentTimeMillis() - lastClick <= 1000) {
                return false;
            }
            lastClick = System.currentTimeMillis();
            return true;
        }
        return true;
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * [初始化控件]
     *
     *  @param view
     */
    public abstract void initView(final View view);

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [View点击事件]
     *
     * @param view
     */
    public abstract void widgetOnClick(View view);
}
