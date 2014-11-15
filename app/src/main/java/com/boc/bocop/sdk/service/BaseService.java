package com.boc.bocop.sdk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import android.content.Context;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.ContainerInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.StringUtil;

public class BaseService {
	private static Context baseContext = null;

	public static LinkedHashMap<String, String> genSapHeader() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("clentid", AppInfo.getAppKeyValue());

		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());
		String mUserId = accessToken.getUserId();
		String token = accessToken.getToken();
//		String token = "87a3ff45-24e0-4758-b7d9-c72e5283569d";
		if (!StringUtil.isNullOrEmpty(mUserId)) {
			map.put("userid", mUserId);
		}

		if (!StringUtil.isNullOrEmpty(token)) {
			map.put("acton", token);
		}

		map.put("chnflg", "1");

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
		// 获取当前时间
		String nowData = format.format(new Date(System.currentTimeMillis()));
		map.put("trandt", nowData);

		SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
		// 获取当前时间
		String nowTime = formatTime
				.format(new Date(System.currentTimeMillis()));
		map.put("trantm", nowTime);
		
//		String strCookie = ContainerInfo.getSessionCookie();
//		Logger.d("strCookie --->" + strCookie);
//		if(!StringUtil.isNullOrEmpty(strCookie)){
//			Logger.d("head input cookie is --->" + strCookie);
//			map.put("cookie", strCookie);
//		}

		return map;
	}

	public static boolean checkOauthToken(Context context) {
		baseContext = context;
		boolean result = false;
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			result = true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(baseContext,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(baseContext, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
				}

			});
		}

		return result;
	}

	public static LinkedHashMap<String, String> genPublicAsrHeader(
			Context context) {
		return genSapHeader();
	}
	
	/**
	 * 生成公共报文头 header:clentid、userid、chnflg、trandt、trantm
	 * @param context
	 * @return
	 */
	/*public static LinkedHashMap<String, String> genPublicAsrHeader(
			Context context) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("clentid", Constants.containerAppId);

		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(context);
		String mUserId = null;
		if (accessToken != null) {
			mUserId = accessToken.getUserId();
		}
		if (!StringUtil.isNullOrEmpty(mUserId)) {
			map.put("userid", mUserId);
		} else {
			map.put("userid", " ");
		}

		map.put("chnflg", "1");

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
		// 获取当前时间
		String nowData = format.format(new Date(System.currentTimeMillis()));
		map.put("trandt", nowData);

		SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
		// 获取当前时间
		String nowTime = formatTime
				.format(new Date(System.currentTimeMillis()));
		map.put("trantm", nowTime);

		return map;
	}*/
}
