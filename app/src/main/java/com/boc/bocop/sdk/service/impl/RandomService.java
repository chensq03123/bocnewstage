package com.boc.bocop.sdk.service.impl;

import java.util.LinkedHashMap;

import org.json.JSONException;

import com.boc.bocop.sdk.api.bean.oauth.RandomCriteria;
import com.boc.bocop.sdk.api.bean.oauth.RandomResponse;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseParse;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.oauth.RandomParse;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

import android.content.Context;
import android.util.Log;

public class RandomService  extends BaseService{

	/*public static RandomRequest getRandomResult(Context context,
			RandomCriteria randomCriteria, ResponseListener listener){

		//LinkedHashMap<String, String> head = RandomBuilder.buildRandom(randomCriteria);
		LinkedHashMap<String, String> head = BaseService.genPublicAsrHeader(context);
		LinkedHashMap<String, String> body = new LinkedHashMap();

		AsyncPara para = new AsyncPara(Constants.urlGetTrdNum,
				ParaType.HTTPMETHOD_POST, head, body, 1,
				listener);
		Log.i("RandomService", para.toString());
		RandomService service = new RandomService();
		RandomRequest requestTask = service.new RandomRequest();
		requestTask.execute(para);
		return requestTask;
	}*/
	
	public class RandomRequest extends RequestAsyncTask {
		@Override
		protected String doInBackground(AsyncPara... params) {
			String resp = null;
			String result = "成功";
			try {
				// resp返回得到的结果
				resp = HttpManager.openUrlSap(params[0].getUrl(),
						params[0].getHttpMethod(), params[0].getParamsHead(),
						params[0].getParamsBody());
				Logger.d("RandomRequest", "resp -- " + resp);
			} catch (BOCOPException e) {
				params[0].getListener().onException(e);
				result = e.getMessage();
				return result;
			} catch (JSONException e) {
				params[0].getListener().onException(e);
				result = e.getMessage();
				return result;
			}
			Logger.d("resp ---random------>" + resp);
			// 如果返回结果为空，直接报异常
			if (StringUtil.isNullOrEmpty(resp)) {
				params[0].getListener().onException(
						new BOCOPException("服务器返回异常", -1));
				result = "服务器返回异常";
				Logger.d("random result :"+result);
				return result;
			} else {
				if (resp.contains("msgcde") && resp.contains("rtnmsg")) {
					try {
						params[0].getListener().onError(
								BaseParse.parseResponseError(resp));
						result = BaseParse.parseResponseError(resp).getRtnmsg();
						return result;
					} catch (JSONException e) {
						params[0].getListener().onException(e);
						return e.getMessage();
					}
				}
				else {
				// 返回的errcode不为0，就会有描述errmsg
					RandomResponse randomResponse= null;
					try {
						randomResponse = RandomParse.parseRandomDetail(resp);
						Logger.d("random resp ----:"+randomResponse.getRandom());
					} catch (JSONException e) {
						params[0].getListener().onException(e);
					}
					params[0].getListener().onComplete(randomResponse);
				
				}
				return result;
			}
		}
	}
}
