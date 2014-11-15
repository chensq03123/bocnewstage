package com.boc.bocop.sdk.service.impl;

import org.json.JSONException;

import com.boc.bocop.sdk.api.bean.oauth.RegisterResponse;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

/**
 *@author  liuwenchao
 *@date 2014-5-16
 *@declare 
 */
public class OauthRegisterRequest extends RequestAsyncTask {

	String successString = "success";
	String exceptionString = "exception";
	String errorString = "error";
	String cancelString = "cancel";

	@Override
	protected String doInBackground(AsyncPara... params) {
		String resp = null;
		try {
			Logger.d("params[0].getUrl()" + params[0].getUrl());
			resp = HttpManager.openUrlAsr(params[0].getUrl(),
					params[0].getHttpMethod(), params[0].getParamsHead(),
					params[0].getParamsBody(), params[0].getType());
		} catch (BOCOPException e) {
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
		if (StringUtil.isNullOrEmpty(resp)) {

			params[0].getListener().onError(
					new ResponseError("-1", errorString));
			return errorString;
		} else {
			try {
				RegisterResponse respBen = new RegisterResponse();
				respBen = JSONParse.decodeOAuthRegister2Json(resp);
				String strRes = respBen.getMsgcde();
				if (strRes.equalsIgnoreCase("ASR-000000")) {
					params[0].getListener().onComplete(respBen);
				//TODO
					
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
			
		}
		return successString;
	}
}
