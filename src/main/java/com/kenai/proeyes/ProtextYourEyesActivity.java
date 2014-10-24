package com.kenai.proeyes;



import com.kenai.function.message.XLog;
import com.kenai.function.state.XState;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceClickListener;

public class ProtextYourEyesActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
   
	
	
	final String my_mzstore="android.intent.action.VIEW";
	@Override
    public void onCreate(Bundle savedInstanceState) {
		XLog.model=true;
		/**
		 * 适配4.0的主题
		 */
		if (XState.get_issdk14())
			setTheme(16974123);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		onCreateDialog(2).show();
		
		PreferenceScreen tuijian_dream = (PreferenceScreen) findPreference("tuijian_dream");
		tuijian_dream.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/dba0d8630ada406dbab446434568bd32")));
				return true;
			}
		});
		
		PreferenceScreen zhichi = (PreferenceScreen) findPreference("zhichi");
		zhichi.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(my_mzstore,
						Uri.parse("mstore:http://app.meizu.com/phone/apps/8d41d920100e4400ae05fdd3544296f9")));
				return true;
			}

		});
		
		PreferenceScreen buy = (PreferenceScreen) findPreference("buy");
		buy.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {
				
					startActivity(new Intent(my_mzstore,
							Uri.parse("mstore:http://app.meizu.com/phone/apps/a1156e07ad7e4f1bba05014c88b3b98c")));
				

				return true;
			}

		});
		load_button_tuijian();
		
		
		PreferenceScreen esc = (PreferenceScreen) findPreference("Esc");
		esc.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {
				
				Intent it = new Intent(ProtextYourEyesActivity.this, KenaiService.class);
				stopService(it);
				finish();
				
				return true;
			}

		});
		PreferenceScreen call=(PreferenceScreen)findPreference("call");
		call.setOnPreferenceClickListener(new OnPreferenceClickListener(){

			public boolean onPreferenceClick(Preference preference) {
				Uri emailUri = Uri.parse("mailto:lx_yjq@qq.com");
				Intent returnIt = new Intent(Intent.ACTION_SENDTO, emailUri);
				startActivity(returnIt);
				
				return false;
			}
			
		});
		
		
		
		
		
		
		
		
		
		
//		CheckBoxPreference xunhuan_tixing = (CheckBoxPreference) findPreference("xunhuan_tixing");
//		xunhuan_tixing
//				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
//
//					public boolean onPreferenceChange(Preference arg0,
//							Object arg1) {
//						myNotification();
//
//						return true;
//					}
//
//				});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
        startService(new Intent(getBaseContext(), KenaiService.class));  
        
    }



	private void load_button_tuijian() {
		PreferenceScreen tuijian1 = (PreferenceScreen) findPreference("tuijian1");
		tuijian1.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent("android.intent.action.VIEW", Uri
						.parse("mstore:http://app.meizu.com/phone/apps/a1156e07ad7e4f1bba05014c88b3b98c")));
				return true;
			}
		});
		PreferenceScreen tuijian2 = (PreferenceScreen) findPreference("tuijian2");
		tuijian2.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/ca95b051060448b097ee347275726b23")));
				return true;
			}
		});
		PreferenceScreen tuijian3 = (PreferenceScreen) findPreference("tuijian3");
		tuijian3.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/a4f1c55754764b03b39ec80115c24319")));
				return true;
			}
		});
		PreferenceScreen tuijian4 = (PreferenceScreen) findPreference("tuijian4");
		tuijian4.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/e29297ac81ce4d0ba80fad1cc8a52fed")));
				return true;
			}
		});
		PreferenceScreen tuijian5 = (PreferenceScreen) findPreference("tuijian5");
		tuijian5.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/4b6530303d964735b58985c1cf82887f")));
				return true;
			}
		});
		PreferenceScreen tuijian6 = (PreferenceScreen) findPreference("tuijian6");
		tuijian6.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/082e2554efb948bf88bf2d3db55f3b00")));
				return true;
			}
		});
		PreferenceScreen tuijian7 = (PreferenceScreen) findPreference("tuijian7");
		tuijian7.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/d61f7548bea742588ea6ae8c571aff45")));
				return true;
			}
		});
		PreferenceScreen tuijian8 = (PreferenceScreen) findPreference("tuijian8");
		tuijian8.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/8d41d920100e4400ae05fdd3544296f9")));
				return true;
			}
		});
		PreferenceScreen tuijian9 = (PreferenceScreen) findPreference("tuijian9");
		tuijian9.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/cec1a0e3790f47a09af1a0333406233b")));
				return true;
			}
		});
		PreferenceScreen tuijian10 = (PreferenceScreen) findPreference("tuijian10");
		tuijian10.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/7ddf8636871844849a9062a0adc137ce")));
				return true;
			}
		});
		PreferenceScreen tuijian11 = (PreferenceScreen) findPreference("tuijian11");
		tuijian11.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/587d13ef073248f3b8e17a7a43d94fb0")));
				return true;
			}
		});
		PreferenceScreen tuijian12 = (PreferenceScreen) findPreference("tuijian12");
		tuijian12.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/e4f6ea40cd134c57878623089096fa99")));
				return true;
			}
		});
		PreferenceScreen tuijian13 = (PreferenceScreen) findPreference("tuijian13");
		tuijian13.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/dba0d8630ada406dbab446434568bd32")));
				return true;
			}
		});
		PreferenceScreen tuijian14 = (PreferenceScreen) findPreference("tuijian14");
		tuijian14.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {

				startActivity(new Intent(
						"android.intent.action.VIEW",
						Uri.parse("mstore:http://app.meizu.com/phone/apps/d208014990f942cc94fbbe184d0a6d87")));
				return true;
			}
		});

	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 2:
			default:
			return new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("更新说明")
					.setMessage("护眼原理：在长时间持续看屏幕后，kenai会提醒您休息一会儿\n此软件尚不完善，欢迎各位MY通过下方联系作者反馈建议以及BUG")
					.setPositiveButton("完毕",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).create();
		}
	}

}
