package com.example.library.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.R;


/**
 * Toast统一管理类
 * 
 */
public class ToastUtils {

	private ToastUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isShow = true;

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (isShow)
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param resId
	 */
	public static void showShort(Context context, int resId) {
		if (isShow)
			Toast.makeText(context, context.getString(resId),
					Toast.LENGTH_SHORT).show();
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow)
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param resId
	 */
	public static void showLong(Context context, int resId) {
		if (isShow)
			Toast.makeText(context, context.getString(resId), Toast.LENGTH_LONG)
					.show();
	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		if (isShow)
			Toast.makeText(context, message, duration).show();
	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (isShow)
			Toast.makeText(context, message, duration).show();
	}

	/** 显示自定义Toast提示(来自String) 时间 **/
	@SuppressLint("InflateParams")
	public static void showCustomToast(Context context, String text,int duration) {
		if (isShow) {
			View toastRoot = LayoutInflater.from(context).inflate(
					R.layout.toast_common, null);
			((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
			Toast toast = new Toast(context);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.setDuration(duration);
			toast.setView(toastRoot);
			toast.show();
		}
	}

	/** 显示自定义Toast提示(来自res) 时间**/
	@SuppressLint("InflateParams")
	public static void showCustomToast(Context context, int resId,int duration) {
		if (isShow) {
			View toastRoot = LayoutInflater.from(context).inflate(
					R.layout.toast_common, null);
			((TextView) toastRoot.findViewById(R.id.toast_text)).setText(context
					.getString(resId));
			Toast toast = new Toast(context);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.setDuration(duration);
			toast.setView(toastRoot);
			toast.show();
		}
	}

	/** 显示自定义Toast提示(来自res) **/
	@SuppressLint("InflateParams")
	public static void showCustomToast(Context context, int resId) {
		if (isShow) {
			View toastRoot = LayoutInflater.from(context).inflate(
					R.layout.toast_common, null);
			((TextView) toastRoot.findViewById(R.id.toast_text)).setText(context
					.getString(resId));
			Toast toast = new Toast(context);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastRoot);
			toast.show();
		}
	}


	/** 显示自定义Toast提示(来自String) **/
	@SuppressLint("InflateParams")
	public static void showCustomToast(Context context, String text) {
		if (isShow) {
			View toastRoot = LayoutInflater.from(context).inflate(
					R.layout.toast_common, null);
			((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
			Toast toast = new Toast(context);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastRoot);
			toast.show();
		}
	}

}