package com.kenai.function.state;

import java.util.List;

import com.kenai.function.message.XLog;
import com.kenai.function.setting.XSetting;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class XState {
	//
	private static boolean isMEIZU = false;
	private static boolean issdk14 = false;
	private static long lastScreenON;
	private static long lastScreenOFF;
	private static boolean isScreenON=true;
	//

	// //////////////////////////////////////////////////////////gggggggggggggggeeeeeeeeeeeeeeeeeettttttttttttttttt
	private static int isMeizuhasdone = 0;

	public static boolean get_isMeizu() {
		if (isMeizuhasdone == 0) {
			isMeizuhasdone = 1;
			String shouji = android.os.Build.MODEL;
			String[] sj = TextUtils.split(shouji, " ");
			if (TextUtils.equals(sj[0], "MEIZU")
					^ TextUtils.equals(sj[0], "M9")
					^ TextUtils.equals(sj[0], "MX"))
				isMEIZU = true;
		}
		return true;
	}

	private static int issdk14hasdone = 0;

	public static boolean get_issdk14() {
		if (issdk14hasdone == 0) {
			issdk14hasdone = 1;
			String shouji = android.os.Build.VERSION.SDK;
			int sdk = Integer.parseInt(shouji);
			if (sdk < 12)
				issdk14 = false;
			else
				issdk14 = true;
		}
		return issdk14;
	}

	public static boolean get_isScreenON() {
		return isScreenON;
	}

	public static long get_lastScreenON() {
		return lastScreenON;
	}

	public static long get_lastScreenOFF() {
		return lastScreenOFF;
	}

	
	private static TelephonyManager telephonyManager;
	private static boolean ishasbind=false;
	public static boolean get_isCalling(Context context) {
		if(!ishasbind){
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		ishasbind=true;
		}
		if (telephonyManager.getCallState() == 0)
			return false;
		else
			return true;

	}
	public static int get_isCalling_super(Context context) {
		if(!ishasbind){
			telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			ishasbind=true;
			}
		return telephonyManager.getCallState();
	}
	
	
	/**
	 * name:类名全称
	 * 
	 * @param name
	 * @return
	 */
	public static boolean get_isServiceRuning(String name, Context context) {
		ActivityManager mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> mServiceList = mActivityManager
				.getRunningServices(1000);
		// 我要判断的服务名字，我在launcher2 里加了一个音乐服务
		final String musicClassName = name;
		boolean b = MusicServiceIsStart(mServiceList, musicClassName);
		return b;

	}

	// 通过Service 的类名来判断是否启动某个服务
	private static boolean MusicServiceIsStart(
			List<ActivityManager.RunningServiceInfo> mServiceList,
			String className) {
		for (int i = 0; i < mServiceList.size(); i++) {
			if (className.equals(mServiceList.get(i).service.getClassName())) {
				return true;
			}
		}
		return false;
	}
	public static boolean get_isfirst(Context context){
		int ver1=0;
		try {
		PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		ver1 = info.versionCode;
		} catch (NameNotFoundException e) {

		}
		int ver2=XSetting.xget_int(context, "ver_kenai");
		if(ver1==ver2){
			return false;
		}else{
			XSetting.xset_string_int(context, "ver_kenai", String.valueOf(ver1));
			return true;
		}
	}
	
	

	// //////////////////////////////////////////////////////////sssssssssssseeeeeeeeeeeeeeeeeettttttttttttttttt
	/*
	 * 设置是否为测试模式，测试模式下可以输出LOG
	 */
	public static void xSetTestModel(boolean b) {
		XLog.model = b;
	}

	public static void xSetTIMELastScreenON(long time) {
		lastScreenON = time;
	}

	public static void xSetTIMELastScreenOFF(long time) {
		lastScreenOFF = time;
	}

	public static void xSetisScreenON(boolean is) {
		isScreenON = is;
	}
	public static String get_kenaiString(Context context) {
		return XSetting.xget_string(context, "kenaistring");
	}

}
