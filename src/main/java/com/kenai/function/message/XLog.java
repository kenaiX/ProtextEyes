package com.kenai.function.message;

import android.util.Log;

public class XLog {

	public final boolean TEST = true;
	public final boolean SELL = false;
	public static boolean model = false;

	public final static void xLog(String s1) {
		if (model)
			Log.v("kenai's log", s1);
	}
}
