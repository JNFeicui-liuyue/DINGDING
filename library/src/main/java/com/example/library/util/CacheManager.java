package com.example.library.util;

import android.app.Activity;
import android.content.Context;

import com.example.library.common.service.HttpCache;
import com.example.library.common.service.impl.ImageCache;
import com.example.library.common.service.impl.ImageSDCardCache;


/**
 * CacheManager
 * 描述：缓存管理工具类
 * 时间：2016年9月13日
 */
public class CacheManager {

    private static HttpCache httpCache = null;

    private CacheManager() {
        throw new AssertionError();
    }

    /**
     * 获得一个Http缓存的实例
     * 
     * @param context {@link Activity#getApplicationContext()}
     * @return
     */
    public static HttpCache getHttpCache(Context context) {
        if (httpCache == null) {
            synchronized (CacheManager.class) {
                if (httpCache == null) {
                    httpCache = new HttpCache(context);
                }
            }
        }
        return httpCache;
    }

    /**
     *获得一个图片缓存的单例
     * 
     * @return
     * @see ImageCacheManager#getImageCache()
     */
    public static ImageCache getImageCache() {
        return ImageCacheManager.getImageCache();
    }

    /**
     * 获得一个SD卡 图片缓存的单例
     * 
     * @return
     * @see ImageCacheManager#getImageSDCardCache()
     */
    public static ImageSDCardCache getImageSDCardCache() {
        return ImageCacheManager.getImageSDCardCache();
    }
}
