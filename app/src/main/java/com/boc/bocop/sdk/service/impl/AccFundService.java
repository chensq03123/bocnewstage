package com.boc.bocop.sdk.service.impl;

import java.util.LinkedHashMap;

import org.json.JSONException;

import android.content.Context;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.accfund.AccFundBalInfo;
import com.boc.bocop.sdk.api.bean.accfund.AccFundDepositInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.accfund.AccFundBuilder;
import com.boc.bocop.sdk.service.engine.accfund.AccFundParse;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

public class AccFundService extends BaseService {

	private  static final int REQUEST_URL_ACCFUND_BALINFO = 1;
	private  static final int REQUEST_URL_ACCFUND_DEPOSITINFO = 2;

	public static void queryAccFundBalInfo(String alias,
			ResponseListener listener) {
		Logger.d("BOCSDK:===>queryAccFundBalInfo:url:"
				+ Constants.urlAccfundBalinfo);

		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = AccFundBuilder
				.queryAccFundBalInfo(alias);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlAccfundBalinfo,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_ACCFUND_BALINFO, listener);
		AccFundService service = new AccFundService();
		AccFundSyncRequest requestTask = service.new AccFundSyncRequest();
		requestTask.execute(para);

	}

	public static void queryAccFundDepositInfo(String alias, String sdate,
			String edate, ResponseListener listener) {
		Logger.d("BOCSDK:===>queryAccFundDepositInfo:url:"
				+ Constants.urlAccfundDepositinfo);
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = AccFundBuilder
				.queryAccFundBalInfo(alias);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlAccfundDepositinfo,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_ACCFUND_DEPOSITINFO, listener);
		AccFundService service = new AccFundService();
		AccFundSyncRequest requestTask = service.new AccFundSyncRequest();
		requestTask.execute(para);

	}

	class AccFundSyncRequest extends RequestAsyncTask {

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
			} 
			else {
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
					case REQUEST_URL_ACCFUND_BALINFO:
						AccFundBalInfo info = null;
						try {
							info = AccFundParse
									.parseQueryAccFundBalInfoResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(info);
						break;
					case REQUEST_URL_ACCFUND_DEPOSITINFO:
						AccFundDepositInfo response = null;
						try {
							response = AccFundParse
									.parseQueryAccFundDepositInfoResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(response);
						break;
					default:
						break;
					}
				}
			}
			return result;
		}
	}

	public static boolean checkAccFundBalInfoOauthToken(Context context,
			final String alias, final ResponseListener listener) {
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
					AccFundService.queryAccFundBalInfo(alias, listener);
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

	public static boolean checkAccFundDepositInfoOauthToken(Context context,
			final String alias, final String sdate, final String edate,
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
					AccFundService.queryAccFundDepositInfo(alias, sdate, edate,
							listener);
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
