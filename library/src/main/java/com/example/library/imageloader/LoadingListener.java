package com.example.library.imageloader;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @描述 自定义ImageLoading监听，关闭图片加载失败时打印的error Log
 * 
 * @类名 LoadingListener.class
 * 
 * @时间 2016年4月5日
 * 
 * @author Administrator
 * 
 * @最后修改时间 2016年4月5日 by liushuai
 */

public class LoadingListener implements ImageLoadingListener {
	// com.nostra13.universalimageloader.utils.L.disableLogging();关闭LOG
	// com.nostra13.universalimageloader.utils.L.enableLogging();打印LOG
	static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());// 获取图片路径list，判断是否是第一次加载

	public LoadingListener() {
	}


	@Override
	public void onLoadingStarted(String imageUri, View view) {
		// LogUtils.i("正在加载中");
		com.nostra13.universalimageloader.utils.L.disableLogging();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onLoadingFailed(String imageUri, View view,
			FailReason failReason) {
		switch (failReason.getType()) {
		case IO_ERROR:
			// LogUtils.i("IO错误，加载失败");
			com.nostra13.universalimageloader.utils.L.disableLogging();
			break;
		case DECODING_ERROR:
			// LogUtils.i("解码错误，加载失败");
			com.nostra13.universalimageloader.utils.L.disableLogging();
			break;

		case NETWORK_DENIED:
			// LogUtils.i("网络请求拒绝，加载失败");
			com.nostra13.universalimageloader.utils.L.disableLogging();
			break;

		case OUT_OF_MEMORY:
			// LogUtils.i("OOM，加载失败");
			com.nostra13.universalimageloader.utils.L.disableLogging();
			break;

		case UNKNOWN:
			// LogUtils.i("未知异常，加载失败");
			com.nostra13.universalimageloader.utils.L.disableLogging();
			break;
		default:
			break;
		}
	}

	@Override
	public void onLoadingCancelled(String imageUri, View view) {
		// LogUtils.i("加载关闭");

	}

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		// LogUtils.i("加载完成");
		
		if (loadedImage != null) {
			ImageView imageView = (ImageView) view;
			// 是否第一次显示
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) {
				// 图片淡入效果
				FadeInBitmapDisplayer.animate(imageView, 500);
				displayedImages.add(imageUri);
			}
		}
	}

}
