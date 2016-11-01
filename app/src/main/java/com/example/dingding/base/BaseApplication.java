package com.example.dingding.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.example.library.crash.CrashHandler;
import com.example.library.imageloader.ImageLoaderInit;
import com.example.library.update.UpdateManager;
import com.example.library.util.AppUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 类名：com.house.base.BaseApplication.class
 * 描述：继承Application类来实现应用程序级的全局变量，这种全局变量方法相对静态类更有保障，直到应用的所有Activity全部被destory掉之后才会被释放掉。
 * Created by 刘帅 on 2016/8/9.
 */
public class BaseApplication extends Application {
    /**
     * 注意：这里的boolean，属于全局配置，一次配置，全局使用
     * **/

    /**
     * 该类的实例
     **/
    private static BaseApplication application;

    /**
     * Activity之间的传值可用此方法
     **/
    public Map<String, Object> mCache = new HashMap<String, Object>();

    /**
     * app名称
     **/
    public static String APP_NAME;

    /**
     * 是否输出日志信息
     **/
    public static final boolean isDebug = true;

    /**
     * 是否开启异常捕获
     **/
    public static final boolean allowCrash = false;

    /**
     * 是否允许检测更新
     **/
    public static final boolean allowCheckUpdate = false;

    /**
     * 是否允许静默安装apk，不允许则需要手动更新
     **/
    public static final boolean allowAutoInstall = true;

    // 构造方法
    public synchronized static BaseApplication getInstance() {
        if (null == application) {
            application = new BaseApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        APP_NAME = AppUtils.getAppName(this);
        MustInit();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (isDebug)
            Log.e("BaseApplication", "onLowMemory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (isDebug)
            Log.e("BaseApplication", "onTerminate");
    }

    /**
     * 统一注册
     */
    private void MustInit() {
        /**
         * 非常Nice的图片异步加载框架
         */
        // 注册图片异步加载框架
        ImageLoaderInit.initImageLoader(getApplicationContext());
    }
    /**
     * 可选注册
     */
    public void ChooseInit(Context mContext) {
        // 注册异常处理
        if (allowCrash) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(mContext);
        }


        // 注册APP自动更新
        if (allowCheckUpdate) {
            String url = "http://192.168.4.117:80/JJLRService/Update.xml";// 自动更新地址
            UpdateManager manager = UpdateManager.getInstance();
            manager.init(mContext, allowAutoInstall);
            manager.isUpdate(url);
        }
    }

    // Activity之间的传值可用此方法
    public Object getObject(String value) {
        if (mCache.containsKey(value)) {
            Object reference = mCache.get(value);
            if (reference == null) {
                mCache.remove(value);
            } else {
                return reference;
            }
        }
        return null;
    }

}
