package com.example.library.update;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.library.R;
import com.example.library.util.AppUtils;
import com.example.library.util.DialogTools;
import com.example.library.util.PackageUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author ...
 * @date 2016-08-04
 */

public class UpdateManager {
	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	/* 保存解析的XML信息 */
	HashMap<String, String> mHashMap;
	/* 下载保存路径 */
	private String mSavePath;
	/* 记录进度条数量 */
	public  int progress;
	/* 是否取消更新 */
	private boolean cancelUpdate = false;

	private Context mContext;
	
	/* 是否静默安装 */
	private boolean autoInstall=false;
	
	Handler handler;
	
	/**自动更新地址**/
	private String Url;
	/* 更新进度条 */
	private ProgressBar mProgress;
	private Dialog mDownloadDialog;
	
	private static UpdateManager updateManager;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
				mProgress.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				// 安装文件
				if(!autoInstall)//不静默安装
					installApk();
				else{//静默安装
					File apkfile = new File(mSavePath, mHashMap.get("name"));
					if (!apkfile.exists()) {
						return;
					}
					PackageUtils.install(mContext, apkfile.getAbsolutePath());
				}
				break;
			default:
				break;
			}
		};
	};

	private UpdateManager() {
		
	}
	
	public static UpdateManager getInstance(){
		if(updateManager==null){
			updateManager=new UpdateManager();
		}
		return updateManager;
	}
	
	public void init(Context context,boolean _autoInstall){
		this.mContext = context;
		this.autoInstall=_autoInstall;
	}
	

	/**
	 * 检测软件更新
	 */

	/**
	 * 检查软件是否有更新版本
	 * 
	 * @return
	 */
	// private boolean isUpdate()
	// {
	// // 获取当前软件版本
	// int versionCode = getVersionCode(mContext);
	//
	//
	// // 把version.xml放到网络上，然后获取文件信息
	// InputStream inStream =
	// ParseXmlService.class.getClassLoader().getResourceAsStream("version.xml");
	// // 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析
	// ParseXmlService service = new ParseXmlService();
	// try
	// {
	// mHashMap = service.parseXml(inStream);
	// } catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// if (null != mHashMap)
	// {
	// int serviceCode = Integer.valueOf(mHashMap.get("version"));
	// // 版本判断
	// if (serviceCode > versionCode)
	// {
	// return true;
	// }
	// }
	// return false;
	// }

	public  void isUpdate(String url) {
		// 启动新线程下载软件
		this.Url=url;
		new IsisUpdate().start();
	}

	private class IsisUpdate extends Thread {
		@Override
		public void run() {
			Looper.prepare(); 
			try {
					int versionCode = getVersionCode(mContext); // 获取当前软件版本
					URL url;// 定义网络中version.xml的连接
					try { // 一个测试
						url = new URL(Url);// 创建version.xml的连接地址。
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						InputStream inStream = connection.getInputStream();// 从输入流获取数据
						ParseXmlService service = new ParseXmlService();// 将数据通过ParseXmlService这个类解析
						mHashMap = service.parseXml(inStream);// 得到解析信息
					} catch (Exception e) {
						e.printStackTrace();// 测试失败
						Logger.i("------- 检测失败--------");
					}
					if (null != mHashMap) {
						int serviceCode = Integer.valueOf(mHashMap.get("version"));
						// 版本判断
						if (serviceCode > versionCode)
							showNoticeDialog();
						else
							Toast.makeText(mContext, R.string.soft_update_no, Toast.LENGTH_LONG).show();
					}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			 Looper.loop(); 
		}
	}

	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	private int getVersionCode(Context context) {
		int versionCode = 0;
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = AppUtils.getAppVersionCode(mContext);
		Logger.i("----------------当前版本号：" + versionCode);
		return versionCode;
	}

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog() {
		
		 handler=new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 0x100://点了确定按钮
					showDownloadDialog();
					break;

				default:
					break;
				}
			}
		};
		DialogTools.dialog_dismiss(mContext, mContext.getString(R.string.soft_update_title).toString(), mContext.getString(R.string.soft_update_info).toString(), handler, 0x100);
		
	}

	/**
	 * 显示软件下载对话框
	 */
	@SuppressLint("NewApi")
	private void showDownloadDialog() {
		
		// 构造软件下载对话框
		@SuppressWarnings("deprecation")
		Builder builder = new Builder(mContext);
		// 给下载对话框增加进度条
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.softupdate_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
		
		builder.setTitle(R.string.soft_updating);
		builder.setView(v);
		// 取消更新
		builder.setNegativeButton(R.string.soft_update_cancel,
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 设置取消状态
						cancelUpdate = true;
					}
				});
		 builder.setCancelable(false);
		mDownloadDialog = builder.create();
		mDownloadDialog.show();
		// 现在文件
		downloadApk();
	}

	/**
	 * 下载apk文件
	 */
	private void downloadApk() {
		// 启动新线程下载软件
		new downloadApkThread().start();
	}

	/**
	 * 下载文件线程
	 * 
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory()
							+ "/";
					mSavePath = sdpath + "download";
					URL url = new URL(mHashMap.get("url"));
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath, mHashMap.get("name"));
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[1024];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 取消下载对话框显示
			mDownloadDialog.dismiss();
		}
	};

	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, mHashMap.get("name"));
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
