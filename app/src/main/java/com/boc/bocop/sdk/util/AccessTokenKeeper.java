package com.boc.bocop.sdk.util;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * 该类用于保存Oauth2AccessToken到sharepreference，并提供读取功能
 * @author atcindyliu@hotmail.com
 *
 */

public class AccessTokenKeeper {
	/**
	 * 用于存放用户登陆信息
	 */
	private static Oauth2AccessToken oAuthToken;
	private static final String PREFERENCES_NAME = "com_bocop_sdk_android";
	/**
	 * 保存accesstoken到SharedPreferences
	 * @param context Activity 上下文环境
	 * @param token Oauth2AccessToken
	 */
	public static void keepAccessToken(Context context, Oauth2AccessToken token) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(ParaType.KEY_ACCESS_TOKEN, token.getToken());
		editor.putString(ParaType.KEY_REFRESH_TOKEN, token.getRefreshToken());
		editor.putLong(ParaType.KEY_EXPIRES_IN, token.getExpiresTime());
		editor.putString(ParaType.KEY_USERID, token.getUserId());
		editor.commit();
	}
	/**
	 * 清空sharepreference
	 * @param context
	 */
	public static void clear(Context context){
	    SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
	    Editor editor = pref.edit();
	    editor.clear();
	    editor.commit();
	}

	/**
	 * 从SharedPreferences读取accessstoken
	 * @param context
	 * @return Oauth2AccessToken
	 */
	public static Oauth2AccessToken readAccessToken(Context context){
		Oauth2AccessToken token = new Oauth2AccessToken();
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		token.setToken(pref.getString(ParaType.KEY_ACCESS_TOKEN, ""));
		token.setRefreshToken(pref.getString(ParaType.KEY_REFRESH_TOKEN, ""));
		token.setExpiresTime(pref.getLong(ParaType.KEY_EXPIRES_IN, 0));
		token.setUserId(pref.getString(ParaType.KEY_USERID, ""));
		return token;
	}

}
