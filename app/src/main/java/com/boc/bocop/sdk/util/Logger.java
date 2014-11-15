package com.boc.bocop.sdk.util;

import com.boc.bocop.sdk.common.Constants;

import android.util.Log;

public class Logger {
	

	private static final String TAG = "Logger";
	private static final int MaxBufferSize = 8 * 1024;

	// Debug Info
	public static void d(String sMessage) {
		if (Constants.debugOn) {
			d(TAG, sMessage);
		}
	}

	public static void d(String sTag, String sMessage) {
		if (Constants.debugOn) {
			if (null != sMessage) {
				Log.d(sTag, sMessage);
			}
		}
	}
	
	// Warning Info
	public static void w(String sTag, String sMessage) {
		if (Constants.debugOn) {
			if (null != sMessage) {
				Log.w(sTag, sMessage);
			}
		}
	}

	// Error Info
	public static void e(String sMessage) {
		if (Constants.debugOn) {
			if (null != sMessage) {
				e(TAG, sMessage);
			}
		}
	}

	public static void e(String sTag, String sMessage) {
		if (Constants.debugOn) {
			if (null != sMessage) {
				Log.e(sTag, sMessage);
			}
		}
	}

}
