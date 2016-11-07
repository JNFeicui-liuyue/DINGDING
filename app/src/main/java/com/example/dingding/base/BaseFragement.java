package com.example.dingding.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 类名：com.house.base.BaseFragement.class
 * 描述：应用程序基类
 * Created by 刘帅 on 2016/8/9.
 */
public abstract class BaseFragement extends Fragment implements View.OnKeyListener{
    private View mContextView = null;

    /**
     * 注意：这里的boolean，每个fragement都继承BaseFragement，当fragement初始化时，BaseFragement便完成一次初始化
     * ，这样就可以实现每个fragement都可以单独的进行显示状态的设置
     * **/

    /**
     * 是否允许快速点击
     **/
    public boolean allowQuick = false;

    /**
     * 快速点击周期
     **/
    private long lastClick = 0;

    /**
     * 获取当前包名的TAG
     **/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        $Log("-----------> Fragment:onAttach <--------------");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        $Log("-----------> Fragment:onCreate <--------------");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        $Log("-----------> Fragment:onCreateView <--------------");
        mContextView = inflater.inflate(initLayout(), container, false);
        ButterKnife.bind(this, mContextView);
        doBusiness(getActivity());
        return mContextView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        $Log("-----------> Fragment:onActivityCreated <--------------");
    }

    @Override
    public void onStart() {
        super.onStart();
        $Log("-----------> Fragment:onStart <--------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        $Log("-----------> Fragment:onResume <--------------");
    }

    @Override
    public void onPause() {
        super.onPause();
        $Log("-----------> Fragment:onResume <--------------");
    }

    @Override
    public void onStop() {
        super.onStop();
        $Log("-----------> Fragment:onStop <--------------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        $Log("-----------> Fragment:onDestroyView <--------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        $Log("-----------> Fragment:onDestroy <--------------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        $Log("-----------> Fragment:onDetach <--------------");
    }

    /********************************************************************************
     *
     * [1.是否允许快速点击转 2.日志输出 contact_profile_header_blacklist3.防止快速点击
     * 4.物理键监听 ]
     *
     ********************************************************************************/


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
     * <p/>
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
     * [物理键监听]
     *
     * @return
     */
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return $onKey(view,i,keyEvent);
    }

/********************************************************************************
     *
     * [1.绑定布局 2.物理件监听 contact_profile_header_blacklist3.业务操作]
     *
     ********************************************************************************/


    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [物理件监听]
     * @param view
     * @param i
     * @param keyEvent
     * @return
     */
    public abstract boolean $onKey(View view, int i, KeyEvent keyEvent);

}
