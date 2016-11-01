package com.example.library.imageloader;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


public class ImageLoaderInit {
	public static void initImageLoader(Context context) {
		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.threadPoolSize(6)
				// 线程池内加载的数量
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(5 * 1024 * 1024)) // 建议内存设在5-10M,可以有比较好的表现
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileCount(100) // 缓存的文件数量
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		// 全局化配置
		ImageLoader.getInstance().init(config);
	}
}
