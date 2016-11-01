package com.example.library.util;

/**
 * SystemUtils
 * 描述：线程池工具类
 * 时间：2016年9月14日
 */
public class SystemUtils {

    /** recommend default thread pool size according to system available processors, {@link #getDefaultThreadPoolSize()} **/
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

    private SystemUtils() {
        throw new AssertionError();
    }

    /**
     * 获取默认线程池的大小
     * get recommend default thread pool size
     * 
     * @return 如果 2*可用线程+1 <8  返回线程，否则返回8  if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * 获取默认线程池的大小
     * get recommend default thread pool size
     * 
     * @param max 最大线程池的数量
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
}
