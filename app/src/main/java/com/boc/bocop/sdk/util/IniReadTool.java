package com.boc.bocop.sdk.util;

import com.boc.bocop.sdk.common.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class IniReadTool {
	private static final String PREFERENCES_IPPORT = "com_bocop_ip_port";

	// 读取配置服务信息

	public static void readHTTPSharedPreferences(Context context) {

		String httpIp = "";
		int httpPort = 0;
		boolean isShowRegister;
		String registerHttp = "";
		SharedPreferences ipPort = context.getSharedPreferences(
				PREFERENCES_IPPORT, Context.MODE_PRIVATE);

		httpIp = ipPort.getString("httpIP", "");
		httpPort = ipPort.getInt("httpPort", 0);
		isShowRegister = ipPort.getBoolean("isShowRegister", false);
		registerHttp = ipPort.getString("registerHttp", "");
				
		if(StringUtil.isNullOrEmpty(httpIp) || 0 == httpPort)
		{
			httpIp = "http://22.188.20.56";
			httpPort = 80;
		}
		Constants.setHttpPrefixPort(httpIp, httpPort, isShowRegister, registerHttp);
	}

	// 写入配置信息
	public static void writeHTTPSharedPreferences(Context context, String httpIp, int httpPort, 
			boolean isShowRegister, String httpApsPrefix) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				PREFERENCES_IPPORT, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();// 获取编辑器
		editor.putString("httpIP", httpIp);
		editor.putInt("httpPort", httpPort);
		editor.putBoolean("isShowRegister", isShowRegister);
		editor.putString("registerHttp", httpApsPrefix);
		editor.commit();// 提交修改
	}
	
	/**
	 * 清空sharepreference
	 * @param context
	 */
	public static void clearHTTPSharedPreferences(Context context){
	    SharedPreferences pref = context.getSharedPreferences(PREFERENCES_IPPORT, Context.MODE_PRIVATE);
	    Editor editor = pref.edit();
	    editor.clear();
	    editor.commit();
	}

}
