package com.example.dingding.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

/**
 * 类名：com.house.base.BaseActivity.class
 * 描述：应用程序基类
 * Created by 刘帅 on 2016/8/9.
 */
public abstract class BaseActivity_StopUse extends AppCompatActivity implements
        View.OnClickListener{
    /**
     * 注意：这里的boolean，每个activity都继承BaseActivity，当activity初始化时，
     * BaseActivity便完成一次初始化 ，这样就可以实现每个activity都可以单独的进行显示状态的设置
     * **/

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;

    /**
     * 是否允许全屏
     **/
    private boolean allowFullScreen = false;

    /**
     * 是否禁止旋转屏幕
     **/
    private boolean allowScreenRoate = true;

    /**
     * 是否允许快速点击
     **/
    private boolean allowQuick = false;

    /**
     * 当前activity渲染的视图View
     **/
    private View mContextView = null;

    /**
     * 获取当前包名的TAG
     **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 定义全局Handler
     **/
    public UIHandler handler = new UIHandler(Looper.getMainLooper());

    /**
     * 定义全局Contex
     **/
    public Activity cont = this;

    /**
     * 快速点击周期
     **/
    private long lastClick = 0;

    /***************************************************************************
     *
     * 打印Activity生命周期
     *
     ***************************************************************************/

    /**
     * onCreate 创建
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        $Log(TAG + "---------onCreat ");

        Bundle bundle = getIntent().getExtras();
        mContextView = LayoutInflater.from(this).inflate(initLayout(), null);
        Logger.i("当前所在类-----> " + TAG + " <-----");

        initParms(bundle);

        if (allowFullScreen) {// 是否允许全屏
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (isSetStatusBar) // 是否沉浸状态栏
            steepStatusBar();

        if (!allowScreenRoate) // 是否允许横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(mContextView);
        initView(mContextView);
        setHandler();
        initQtData();
        doBusiness(this);

        // 注册APP自动更新
//        if (BaseApplication.getInstance().allowCheckUpdate) {
//            String url = "http://192.168.4.117:80/JJLRService/Update.xml";// 自动更新地址
//            UpdateManager manager = UpdateManager.getInstance();
//            manager.init(BaseActivity.this,BaseApplication.getInstance().allowAutoInstall);
//            manager.isUpdate(url);
//        }
    }

    /**
     * onStop 停止
     */
    @Override
    protected void onStop() {
        super.onStop();
        $Log(TAG + "---------onStop ");
    }

    /**
     * onStart 启动
     */
    @Override
    protected void onStart() {
        super.onStart();
        $Log(TAG + "---------onStart ");
    }

    /**
     * onPause 休眠
     */
    @Override
    protected void onPause() {
        super.onPause();
        $Log(TAG + "---------onPause ");
    }

    /**
     * onRestart 重启
     */

    @Override
    protected void onRestart() {
        super.onRestart();
        $Log(TAG + "---------onRestart ");
    }

    /**
     * onResume 恢复
     */
    @Override
    protected void onResume() {
        super.onResume();
        $Log(TAG + "--->onResume()");
    }

    /**
     * onDestroy 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        $Log(TAG + "--->onDestroy()");
    }

    /***************************************************************************
     *
     * [重写：1.是否沉浸状态栏 2.页面跳转 contact_profile_header_blacklist3.携带数据的页面跳转 4.加载主视图 5.加载布局文件
     * 6.含有Bundle通过Class打开编辑界面 7.是否允许全屏 6.是否设置沉浸状态栏
     * 9.是否允许屏幕旋转 10.日志输出  11.防止快速点击 12.重写内部类]
     *
     ***************************************************************************/

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [页面跳转]
     * <p/>
     * clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     * <p/>
     * clz bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * [加载布局文件]
     *
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T $findViewById(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     * <p/>
     * cls bundle requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * [是否允许全屏]
     * <p/>
     * allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.allowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     * <p/>
     * allowFullScreen
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     * <p/>
     * isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.allowScreenRoate = isAllowScreenRoate;
    }

    /**
     * [是否允许快速点击转]
     * <p/>
     * isAllowScreenRoate
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
        if (BaseApplication.getInstance().isDebug)
            Log.i(BaseApplication.APP_NAME, msg);
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        if (!allowQuick) {// 不允许快速点击
            if (System.currentTimeMillis() - lastClick <= 1000)
                return false;
            lastClick = System.currentTimeMillis();
            return true;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetOnClick(v);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return widgetOnKey(event);
    }

    /**
     * [内部类]
     * <p/>
     * 重写Handler
     */
    public class UIHandler extends Handler {

        private IHandler handler;// 回调接口，消息传递给注册者

        public UIHandler(Looper looper) {
            super(looper);
        }

        public UIHandler(Looper looper, IHandler handler) {
            super(looper);
            this.handler = handler;
        }

        public void setHandler(IHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (handler != null)
                handler.handleMessage(msg);// 有消息，就传递
        }
    }

    public interface IHandler {
         void handleMessage(Message msg);
    }

    private void setHandler() {
        handler.setHandler(new IHandler() {
            public void handleMessage(Message msg) {
                widgetHandle(msg);// 有消息就提交给子类实现的方法
            }
        });
    }

    /********************************************************************************
     *
     * [1.绑定布局 2.初始化控件 contact_profile_header_blacklist3.View点击事件4.初始化Handler 5.初始化Bundle参数
     * 6.初始化其他数据 7.业务操作 8.物理键监听]
     *
     ********************************************************************************/

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    /**
     * [View点击事件]
     *
     * @param view
     */
    public abstract void widgetOnClick(View view);

    /**
     * [初始化Handler]
     */
    public abstract void widgetHandle(Message msg);

    /**
     * [初始化Bundle参数]
     *
     * @param bundle
     */
    public abstract void initParms(Bundle bundle);

    /**
     * [初始化其他数据]
     */
    public abstract void initQtData();

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [物理键监听]
     *
     * @param keyEvent
     */
    public abstract boolean widgetOnKey(KeyEvent keyEvent);

}
