package com.kenai.proeyes;

import com.kenai.function.setting.XSetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Broadcast extends BroadcastReceiver {

	private final String ISAUTOSTART = "autostart";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())
				&& XSetting.xget_boolean(context, ISAUTOSTART)) {
			 context.startService(new Intent(context, KenaiService.class));  
		}

	}

}
