package com.kenai.proeyes.proeyes;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.SystemClock;

import com.kenai.function.demon.XSrevice;
import com.kenai.function.message.XLog;
import com.kenai.function.message.XToast;
import com.kenai.function.setting.XSetting;
import com.kenai.proeyes.Broadcast;
import com.kenai.proeyes.R;

public class ProEyesService extends XSrevice implements OnSharedPreferenceChangeListener{
	public final static String SHIXIAN="pt_shixian";
	public final static String XIUXI="pt_xiuxi";
	public final static String XUNHUAN="pt_xunhuan";
	public final static String WORD="pt_word";
	private  long shixian=6*60*1000;
	private  long xiuxi=1*60*1000;
	private  long xunhuan=1*60*1000;
	private  long n=6;
	
	public ProEyesService(Context context) {
		super(context);
	}

	@Override
	public void xBind(Intent arg0) {

	}

	private long lastScreenOntime=0;
	private long lastScreenOfftime=0;
	private long totaltime=0;
	
	@Override
	public void xCreate() {
		shixian=XSetting.xget_int(context, SHIXIAN)*60*1000;
		xiuxi=XSetting.xget_int(context, XIUXI)*60*1000;
		xunhuan=XSetting.xget_int(context, XUNHUAN)*60*1000;
		shixian=Math.max(shixian, 60*1000);
		xiuxi=Math.max(xiuxi, 60*1000);
		xunhuan=Math.max(xunhuan, 60*1000);
		n=shixian/xiuxi;
		
		XLog.xLog("服务开启");
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(BRO_GENGXIN);
		context.registerReceiver(xBroadcast, filter);
		XSetting.getSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);
		lastScreenOntime=SystemClock.elapsedRealtime();
		sendUpdateBroadcast(Math.max(0, shixian-totaltime));
	}

	@Override
	public void xDestroy() {
		XLog.xLog("服务关闭");
		// TODO Auto-generated method stub
		context.unregisterReceiver(xBroadcast);
		XSetting.getSharedPreferences(context).unregisterOnSharedPreferenceChangeListener(this);
		cancel_noti();
		cancelUpdateBroadcast();
	}

	@Override
	public void xstart(Intent intent) {
		// TODO Auto-generated method stub

	}

	public final Broadcast xBroadcast = new Broadcast() {

		@Override
		public void onReceive(Context context, Intent intent) {
			super.onReceive(context, intent);
			String locString = intent.getAction();
			
			if (Intent.ACTION_SCREEN_OFF.equals(locString)) {
				XLog.xLog("屏幕关闭");
				long thisScreenOfftime=SystemClock.elapsedRealtime();
				long keepScreenOntime=thisScreenOfftime-lastScreenOntime;
				if(keepScreenOntime>shixian){
					keepScreenOntime=shixian;
				}
				lastScreenOfftime=thisScreenOfftime;
				
				totaltime=Math.min(shixian, totaltime+keepScreenOntime);
				XLog.xLog("totaltime屏幕关闭之后："+totaltime);
				
				screen_OFF();
			} else if (Intent.ACTION_SCREEN_ON.equals(locString)) {
				XLog.xLog("屏幕打开");
				long thisScreenOntime=SystemClock.elapsedRealtime();
				long keepScreenOfftime=thisScreenOntime-lastScreenOfftime;
				lastScreenOntime=thisScreenOntime;
				
				totaltime=Math.max(0, totaltime-n*keepScreenOfftime);
				XLog.xLog("totaltime屏幕打开之后："+totaltime);
				
				screen_ON();
			} else if (BRO_GENGXIN.equals(locString)) {
				XLog.xLog("收到提醒广播");
				cancel_noti();
				myNotification2(context, "a1156e07ad7e4f1bba05014c88b3b98c");
				if(XSetting.xget_boolean(context, "xunhuan_tixing"));
				    sendUpdateBroadcast(xunhuan);
				// ///////////////////////////////////////////////////////////////

			}
		}

		private void screen_ON() {
			long left = Math.max(0, shixian-totaltime);
			XLog.xLog("提示时间："+left);
			sendUpdateBroadcast(left);
			
			
			if (XSetting.xget_boolean(context, WORD)) {
				int i = (int) (left / (1000 * 60));
				String s = null;
				switch (i) {
				case 0:
					s = "不到一分钟后提醒";
					break;
				case 15:
					s = "一刻钟后提醒";
					break;
				case 30:
					s = "半小时后提醒";
					break;
				default:
					s = String.valueOf(i) + "分钟后提醒";
				}
				XToast.xToast(context, s);
			}
		}

		private void screen_OFF() {
			cancelUpdateBroadcast();
			cancel_noti();
		}

	};// 广播实例

	

	private final String BRO_GENGXIN = "com.kenai.shijiandaole";
	final int FLAG_CANCEL_CURRENT = 268435456;

	
	public void sendUpdateBroadcast(long left) {
		AlarmManager xAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		// 60秒后将产生广播,触发UpdateReceiver的执行,这个方法才是真正的更新数据的操作主要代码
		Intent intent = new Intent(BRO_GENGXIN);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, FLAG_CANCEL_CURRENT);
		xAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ left, pendingIntent);
		XLog.xLog("延时："+left/1000+"秒后发出提醒");
	}

	/**
	 * 取消定时执行(有如闹钟的取消)
	 * 
	 */
	public void cancelUpdateBroadcast() {
		AlarmManager xAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(BRO_GENGXIN);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, FLAG_CANCEL_CURRENT);
		xAlarmManager.cancel(pendingIntent);
		XLog.xLog("取消已存在的计时");
	}

	private final void myNotification2(Context context, String name) {

		Notification notification = new Notification(R.drawable.ic_launcher,
				"休息，休息一会儿~", System.currentTimeMillis());
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent("android.intent.action.MEIZU_LOCK_SCREEN_FOR_LAUNCHER");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, 0);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		

		notification.setLatestEventInfo(context, "休息，休息一会儿~", "点击立即锁屏休息",
				pendingIntent);
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.icon=R.drawable.ic_noti;
		long[] v = { 1,50 };
		notification.vibrate = v;
		manager.notify(1006, notification);
		XToast.xToast(context, "休息一会儿~");
	}

	void cancel_noti() {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(1006);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		shixian=XSetting.xget_int(context, SHIXIAN)*60*1000;
		xiuxi=XSetting.xget_int(context, XIUXI)*60*1000;
		xunhuan=XSetting.xget_int(context, XUNHUAN)*60*1000;
		shixian=Math.max(shixian, 60*1000);
		xiuxi=Math.max(xiuxi, 60*1000);
		xunhuan=Math.max(xunhuan, 60*1000);
		n=shixian/xiuxi;
	}

}
