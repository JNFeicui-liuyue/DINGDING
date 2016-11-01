package com.example.library.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @类名 HideInput.class
 * 
 * @描述 用于切换输入法显示状态
 * 
 * @时间 2016年5月3日 by google
 */
public class KeyBoardUtils {

	private Activity activity;
	private Context context;

	public KeyBoardUtils() {
		super();
	}

	public KeyBoardUtils(Activity _activity, Context _context) {
		this.activity = _activity;
		this.context = _context;
	}
	
	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public  void openKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 切换软键盘的状态 如当前为收起变为弹出,若当前为弹出变为收起
	 */
	public void toggleInput(Context context) {
		InputMethodManager inputMethodManager =
				(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null) {
			inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 强制隐藏输入法键盘
	 */
	public void hideInput(Context context, View view) {
		InputMethodManager inputMethodManager = 
				(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		view.clearFocus();
		// 隐藏软键盘
		if (inputMethodManager != null) {
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * 获取输入发是否为打开状态
	 * 	true:打开		 false:关闭
	 */
	public boolean isActive(Context context) {

		// 1.得到InputMethodManager对象
		InputMethodManager imm = 
				(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 获取状态信息
		boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
		return isOpen;
	}

	/**
	 * 不用提供绑定的view，直接隐藏输入法，但需要用有参构造，一定要将activity传过来
	 */
	public void withoutInput() {
		InputMethodManager imm = 
				(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null&&isActive(context)) {
			imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
		}
	}
}
