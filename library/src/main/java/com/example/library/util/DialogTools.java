package com.example.library.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.library.R;
import com.example.library.dialog.AlertDialog;
import com.example.library.dialog.MyDialog;
import com.example.library.dialog.SVProgressHUD;


/**
 * 自定义dialog工具类
 * 
 * @时间 2016-08-04
 *
 */
public class DialogTools {
	private static int progress = 0;
	private static Handler mHandler;

	private DialogTools(){
		throw  new Error("Do not need instantiate!");
	}

	/**
	 * 带确定的dialog
	 * 
	 * @param conn
	 * @param msg
	 * @param ok
	 */
	public static void dialog_button(Context conn, String msg, String ok) {
		new AlertDialog(conn).builder().setMsg(msg)
				.setNegativeButton(ok, new OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				}).show();
	}

	/**
	 * 带确定和取消的dialog
	 * 
	 * @param conn
	 *            ...
	 * @param title
	 *            标题
	 * @param value
	 *            详细内容
	 * @param handler
	 *            ...
	 * @param _msg
	 *            消息类型
	 */
	public static void dialog_dismiss(Context conn, String title, String value,
			final Handler handler, int _msg) {
		final Message msg = new Message();
		msg.what = _msg;
		new AlertDialog(conn).builder().setTitle(title).setMsg(value)
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(View v) {
						msg.obj = "确定";
						handler.sendMessage(msg);
					}
				}).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {
						msg.obj = "取消";
					}
				}).show();
	}

	/**
	 * 仅仅是提示框
	 * 
	 * @param conn
	 * @param value
	 */
	public static void dialog_alert(Context conn, String value) {
		SVProgressHUD.showInfoWithStatus(conn, value,
				SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
	}

	/**
	 * 仅仅是不带图标的提示框
	 * 
	 * @param conn
	 * @param value
	 */
	public static void dialog_alertString(Context conn, String value) {
		SVProgressHUD.showInfoWithStatus_String(conn, value,
				SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
	}

	/**
	 * 仅仅是提示框_提交成功
	 * 
	 * @param conn
	 * @param value
	 */
	public static void dialog_alertsuccess(Context conn, String value) {
		SVProgressHUD.showSuccessWithStatus(conn, value);
	}

	/**
	 * 仅仅是提示框_error
	 * 
	 * @param conn
	 * @param value
	 */
	public static void dialog_alerterror(Context conn, String value) {
		SVProgressHUD.showErrorWithStatus(conn, value,
				SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
	}

	/**
	 * 仅仅是带进度条的dialog
	 * 
	 * @param conn
	 * @param value
	 */
	public static void dialog_progress(Context conn, String value) {
		SVProgressHUD.showWithStatus(conn, value);
	}

	/**
	 * 仅仅是带百分比的dialog
	 * 
	 * @param conn
	 */
	public static void dialog_percent(final Context conn) {

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				progress = progress + 5;
				if (SVProgressHUD.getProgressBar(conn).getMax() != SVProgressHUD
						.getProgressBar(conn).getProgress()) {
					SVProgressHUD.getProgressBar(conn).setProgress(progress);
					SVProgressHUD.setText(conn, "进度 " + progress + "%");
					mHandler.sendEmptyMessageDelayed(0, 500);
				} else {
					SVProgressHUD.dismiss(conn);
					SVProgressHUD.getProgressBar(conn).setProgress(0);
				}

			}
		};

		SVProgressHUD.showWithProgress(conn, "进度 " + progress + "%",
				SVProgressHUD.SVProgressHUDMaskType.Black);
		progress = 0;
		mHandler.sendEmptyMessageDelayed(0, 500);
	}

	/**
	 * 自定义视图的圆角dialog
	 * 
	 * @param conn
	 *            activity...
	 * @param style
	 *            样式
	 * @warning 只是样例
	 */
	public static void dialog_zidingy(Activity conn, int style) {
		View view = conn.getLayoutInflater()
				.inflate(R.layout.dialog_exit_dialog, null);// 自定义布局
		MyDialog dialog = new MyDialog(conn, view, R.style.dialog);
		dialog.show();
	}
	/**
	 * 自定义dialog，用于聊天界面本地数据加载完成后弹出
	 * 
	 */
	@SuppressLint("ResourceAsColor")
	public static void dialog_zidingy(Activity conn) {
//		View view = conn.getLayoutInflater()
//				.inflate(R.layout.end_loadlayout, null);// 自定义布局
//		MyDialog dialog = new MyDialog(conn, view, R.style.dialog,0,0);
//		dialog.show();
	}
	/**
	 * 自定义dialog，带编辑框及获取输入内容
	 * 
	 */
	public static void dialog_bz(Context conn, String title, String value,
			final Handler handler, int _msg) {
		final Message msg = new Message();
		msg.what = _msg;
		new AlertDialog(conn).builder().setTitle(title).setMsg(value).setEdt()
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(View v) {
						msg.obj = "确定";
						handler.sendMessage(msg);
					}
				}).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {
						msg.obj = "取消";
					}
				}).show();
	}
}
