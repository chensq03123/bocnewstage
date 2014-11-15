package com.boc.bocop.sdk.service.impl;

import org.json.JSONException;

import com.boc.bocop.sdk.api.bean.oauth.OAuthResponseInfo;
import com.boc.bocop.sdk.api.bean.oauth.RandomResponse;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.engine.oauth.RandomParse;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

/**
 * 登录认证异步处理类
 * 
 * @author CindyLiu
 */
public class OauthContainerRequest extends RequestAsyncTask {
	String successString = "success";
	String exceptionString = "exception";
	String errorString = "error";
	String cancelString = "cancel";

	@Override
	protected String doInBackground(AsyncPara... params) {
		// TODO Auto-generated method stub
		String resp = null;
		try {
			Logger.d("params[0].getUrl()" + params[0].getUrl());
			resp = HttpManager.openUrlAsr(params[0].getUrl(),
					params[0].getHttpMethod(), params[0].getParamsHead(),
					params[0].getParamsBody(), params[0].getType());
		} catch (BOCOPException e) {
			// TODO Auto-generated catch block
			params[0].getListener().onException(e);
			return exceptionString;
		} catch (JSONException e) {
			params[0].getListener().onException(e);
			return exceptionString;
		}

		if (isCancelled()) {
			params[0].getListener().onCancel();
			return cancelString;
		}
		Logger.d("oauth resp ------------->" + resp);
		if (StringUtil.isNullOrEmpty(resp)) {

			params[0].getListener().onError(
					new ResponseError("-1", errorString));
			return errorString;
		} else {
			switch (params[0].getType()) {
			case ParaType.OAUTH_CONTAINER:
			case ParaType.OAUTH_DEL: // 容器登录
				try {
					OAuthResponseInfo respBen = JSONParse.decodeOAuth2Json(resp);
					String strRes = respBen.getMsgcde();
					if (strRes.equalsIgnoreCase("ASR-000000")) {
						params[0].getListener().onComplete(respBen);
					} else {
						ResponseError error = new ResponseError();
						error.setMsgcde(respBen.getMsgcde());
						error.setRtnmsg(respBen.getRtnmsg());
						params[0].getListener().onError(error);
						return JSONParse.parseResponseError(resp).getRtnmsg();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					params[0].getListener().onException(e);
					return exceptionString;
				}
				break;
			case ParaType.OAUTH_APP: // 小应用登录

				if (resp.contains("msgcde") && resp.contains("rtnmsg")) {
					try {
						params[0].getListener().onError(
								JSONParse.parseResponseError(resp));
						return JSONParse.parseResponseError(resp).getRtnmsg();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						params[0].getListener().onException(e);
						return exceptionString;
					}

				} else {
					OAuthResponseInfo data = new OAuthResponseInfo();
					try {
						data = JSONParse.decodeOAuth2Json(resp);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						params[0].getListener().onException(e);
						return exceptionString;
					}
					params[0].getListener().onComplete(data);
				}
				break;
			case ParaType.GET_ROMDAM:
				if (resp.contains("msgcde") && resp.contains("rtnmsg")) {
					try {
						params[0].getListener().onError(
								JSONParse.parseResponseError(resp));
						return JSONParse.parseResponseError(resp).getRtnmsg();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						params[0].getListener().onException(e);
						return exceptionString;
					}

				} else {
				RandomResponse randomResponse = null;
				try {
					randomResponse = RandomParse.parseRandomDetail(resp);
					Logger.d("random resp ----:" + randomResponse.getRandom());
				} catch (JSONException e) {
					params[0].getListener().onException(e);
					return exceptionString;
				}
				params[0].getListener().onComplete(randomResponse);
				}
				break;
			}
		}
		return successString;
	}

}
