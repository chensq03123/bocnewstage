package com.boc.bocop.sdk.http;

import java.util.concurrent.Executors;

import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.Logger;

/**
 * 异步请求类
 * @author tongyapeng
 */
public class AsyncHttpRequest implements Runnable {
	private static final String TAG = "AsyncHttpRequest";
	private RequestParams params;	
	 
	public AsyncHttpRequest(RequestParams params) {
		this.params = params;
	}	
	public void execute() {		
		Executors.newSingleThreadExecutor().execute(this);		
	}
	/**
	 * resp为null == 超时
	 * resp.contains("rcdcnt") && resp.contains("rtnmsg")
	 */
	@Override
	public void run() {	
		if (params.getResponseHandler() != null) {
			params.getResponseHandler().sendStartMessage();
		}		
		String response = null;
		try {
			// resp返回得到的结果
			response = HttpManager.openUrlSap(params.getUrl(), params.getHttpMethod(),
					params.getHeader(), params.getParamsBody());	
			Logger.d("AsyncHttpRequest resp ---->" + response);
			if (params.getResponseHandler() != null) {
				params.getResponseHandler().sendHandlerResponseMessage(response);
			}
		} catch (Exception e) {
			sendExceptionMessage(ExceptionDesc.obtainCommonDesc(e));
		} 
		if (params.getResponseHandler() != null) {
			params.getResponseHandler().sendFinishMessage();
		}		
	}
	private void sendExceptionMessage(ExceptionDesc desc) {
		if (params.getResponseHandler() != null) {
			params.getResponseHandler().sendExceptionMessage(desc);
		}	
	}
}
