package com.kenai.proeyes;

import com.kenai.function.message.XToast;
import com.kenai.function.setting.XSetting;
import com.kenai.function.time.XTime;
import com.kenai.function.update.XUpDate;
import com.kenai.proeyes.proeyes.ProEyesService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class KenaiService extends Service{
	private final  ProEyesService my=new ProEyesService(KenaiService.this);
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		my.xCreate();
		if (XSetting.xget_int(getBaseContext(), "gengxin") != Integer
				.parseInt(XTime.gettime_partly_String(3))) {
			XSetting.xset_string_int(getBaseContext(), "gengxin",
					XTime.gettime_partly_String(3));
			new XUpDate().checkANDnoti(getBaseContext(), "kenaibaohu",
					"8d41d920100e4400ae05fdd3544296f9");
		}
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		my.xDestroy();
		XToast.xToast(getBaseContext(), "kenai丟誘桉 眒壽敕");
	}
}
