package com.boc.bocop.sdk.service.impl;

import java.util.LinkedHashMap;

import org.json.JSONException;

import android.content.Context;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoCriteria;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoSearch;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.userinfo.UserInfoBuilder;
import com.boc.bocop.sdk.service.engine.userinfo.UserInfoParse;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

/**
 * @author fww5205 用户信息service类
 */

public class UserInfoSerice extends BaseService {
	private  static final int REQUEST_URL_BASE_APPFINDUSRINFO = 1;

	public static void searchUserInfo(UserInfoCriteria criteria,
			ResponseListener listener) {
		Logger.d("BOCSDK:===>searchUser:url:"
				+ Constants.urlBaseAppfindusrinfo);

		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = (LinkedHashMap<String, String>) UserInfoBuilder
				.searchUserInfo(criteria);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlBaseAppfindusrinfo,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_BASE_APPFINDUSRINFO, listener);
		UserInfoSerice service = new UserInfoSerice();
		UserInfoSyncRequest requestTask = service.new UserInfoSyncRequest();
		requestTask.execute(para);

	}

	class UserInfoSyncRequest extends RequestAsyncTask {

		@Override
		protected String doInBackground(AsyncPara... params) {
			String resp = null;
			String result = "成功";
			try {
				resp = HttpManager.openUrlSap(params[0].getUrl(),
						params[0].getHttpMethod(), params[0].getParamsHead(),
						params[0].getParamsBody());
			} catch (BOCOPException e) {
				params[0].getListener().onException(e);
				result = e.getMessage();
				return result;
			} catch (JSONException e) {
				params[0].getListener().onException(e);
				 result = e.getMessage();
				 return result;
			}
			Logger.d("resp ------------->" + resp);

			if (StringUtil.isNullOrEmpty(resp)) {
				params[0].getListener().onException(
						new BOCOPException("服务器返回异常", -1));
				result = "服务器返回异常";
				return result;
			} else {
				if (resp.contains("msgcde") && resp.contains("rtnmsg")) {
					try {
						params[0].getListener().onError(
								JSONParse.parseResponseError(resp));
						result = JSONParse.parseResponseError(resp)
								.getRtnmsg();
						return result;
					} catch (JSONException e) {
						params[0].getListener().onException(e);
					}
					
				} else {
					switch (params[0].getType()) {
					case REQUEST_URL_BASE_APPFINDUSRINFO:
						UserInfoSearch info = null;
						try {
							info = UserInfoParse.parseUserInfoSearch(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(info);
						break;
					default:
						break;
					}
				}
			}
			return result;
		}
	}
	
	public static boolean checkSearchUserInfoOauthToken(Context context, final UserInfoCriteria criteria,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					UserInfoSerice.searchUserInfo(criteria, listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}
}
