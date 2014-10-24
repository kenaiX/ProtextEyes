package com.kenai.function.update;

import com.kenai.function.setting.XSetting;
import com.kenai.function.state.XState;
import com.kenai.function.time.XTime;
import com.kenai.proeyes.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class XUpDate {

	
	final static String UPDATE_TIME="update_time";
	

	
	
	//还需要加上一个时间的验证！
	
	public 
//	static 
	void checkANDnoti(final Context context, final String key,
			final String name) {
		new Thread() {
			public void run() {
//				Looper.prepare();
				//每天验证仅一次
			    Log.v("my", "inter");
				if (XSetting.xget_int(context, UPDATE_TIME)!= Integer.parseInt(XTime
						.gettime_partly_String(3))) {
					
					HttpHelper hp = new HttpHelper();
					String shouji = hp
							.getHtml("http://kenai123.5d6d.net/thread-1-1-1.html");
					if (shouji != null) {
						// 获取当前时间，并更新时间。
						XSetting.xset_string_int(context, UPDATE_TIME,
								XTime.gettime_partly_String(3));
						// 获取字符信息
						String[] sj = TextUtils.split(shouji, "&lt;" + key
								+ "string" + "&gt;");
						if (sj[1] != null)
							XSetting.xset_string_int(context, "kenaistring",
									sj[1]);
						// 获取版本信息
						String[] sj2 = TextUtils.split(shouji, "&lt;" + key
								+ "&gt;");
						PackageInfo info = null;
						try {
							info = context.getPackageManager().getPackageInfo(
									context.getPackageName(), 0);
						} catch (NameNotFoundException e) {

						}
						int versionCode = info.versionCode;
						if (sj2.length > 1)
							if (Integer.parseInt(sj2[1]) > versionCode) {
								myNotification2(context, name);
							}
					}
				}
//				Looper.loop();
			}
		}.start();
	}
	
	
	
//	private final static void myNotification(Context context, String name) {
//		RemoteViews contentView = new RemoteViews(context.getPackageName(),
//				R.layout.notification);
//		contentView.setImageViewResource(R.id.image, R.drawable.ic_launcher);
//		contentView.setTextViewText(R.id.text1, "kenai软件有更新！");
//
//		String s = XState.get_kenaiString(context);
//		if (s != null)
//			contentView.setTextViewText(R.id.text3, s);
//		else
//			contentView.setTextViewText(R.id.text3, "点击进入更新页面");
//
//		Notification notification = new Notification(R.drawable.gengxin,
//				"检测到新版本", System.currentTimeMillis());
//		NotificationManager manager = (NotificationManager) context
//				.getSystemService(Context.NOTIFICATION_SERVICE);
//
//		Intent intent = new Intent("android.intent.action.VIEW",
//				Uri.parse("mstore:http://app.meizu.com/phone/apps/" + name));
//
//		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
//				intent, 0);
//		notification.flags |= Notification.FLAG_AUTO_CANCEL;
//		notification.internalApp = 1;
//		notification.contentView = contentView;
//		notification.contentIntent = pendingIntent;
//
//		manager.notify(999, notification);
//	}

	
	
	
	
	
private final void myNotification2(Context context, String name) {
		
		Notification notification = new Notification(R.drawable.ic_launcher,
				"检测到新版本", System.currentTimeMillis());
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent("android.intent.action.VIEW",
				Uri.parse("mstore:http://app.meizu.com/phone/apps/" + name));
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		
		String s = XState.get_kenaiString(context);
		
		
		notification.setLatestEventInfo(context, "kenai软件有更新！", s,
				pendingIntent);
		notification.defaults = Notification.DEFAULT_SOUND;
		manager.notify(1006, notification);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
