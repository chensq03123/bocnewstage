package com.boc.bocop.sdk.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;

import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.rate.CurrencyRateSearch;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.rate.RateParse;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

public class RateService extends BaseService {
	private  static final int REQUEST_URL_RATE_SEARCH = 1;

	public static void queryRate(String currency, ResponseListener listener) {
		Logger.d("BOCSDK:===>rateSearch:url:" + Constants.urlRateSearch);
		// 构造请求数据
		LinkedHashMap<String, String> head = RateService.genSapHeader();

		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put("crrncy", currency);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlRateSearch,
				ParaType.HTTPMETHOD_POST, head, body, REQUEST_URL_RATE_SEARCH,
				listener);
		RateService service = new RateService();
		RateSyncRequest requestTask = service.new RateSyncRequest();
		requestTask.execute(para);

	}

	public static LinkedHashMap<String, String> genSapHeader() {

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("clentid", AppInfo.getAppKeyValue());

		map.put("userid", "");
		map.put("acton", "");

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

		return (LinkedHashMap<String, String>) map;
	}

	class RateSyncRequest extends RequestAsyncTask {

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
					case REQUEST_URL_RATE_SEARCH:
						CurrencyRateSearch info = null;
						try {
							info = RateParse.parseRateSearch(resp);
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

}
