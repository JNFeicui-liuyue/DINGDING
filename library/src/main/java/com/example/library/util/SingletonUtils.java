package com.example.library.util;

/**
 * 单例工具类
 * Singleton helper class for lazily initialization.
 * 描述：继承此类以创建单例类
 * 时间：2016年9月14日
 * @author <a href="http://www.trinea.cn/" target="_blank">Trinea</a>
 * 
 * @param <T>
 */
public abstract class SingletonUtils<T> {

    private T instance;

    protected abstract T newInstance();

    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
