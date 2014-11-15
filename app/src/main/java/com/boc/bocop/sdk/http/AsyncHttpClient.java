package com.boc.bocop.sdk.http;

import java.util.LinkedHashMap;

import com.boc.bocop.sdk.util.ParaType;

/**
 * 异步http客户端
 * @author tongyapeng
 */
public class AsyncHttpClient {	

	public  void get(String url,AsyncResponseHandler responseHandler) {
		get(url,null, null,responseHandler);
	}
	public  void get(String url,LinkedHashMap<String, String> header,LinkedHashMap<String, String> paramsBody,AsyncResponseHandler responseHandler) {
		sendRequest(url, ParaType.HTTPMETHOD_GET,header, paramsBody,responseHandler);
	}
	public  void get(String url,LinkedHashMap<String, String> header,Object bodyCriteria,AsyncResponseHandler responseHandler) {
		get(url,header,BeanUtils.criteriaToHashMap(bodyCriteria),responseHandler);		
	}
	public void post(String url,LinkedHashMap<String, String> header,LinkedHashMap<String, String> paramsBody,AsyncResponseHandler responseHandler) {
		sendRequest(url, ParaType.HTTPMETHOD_POST,header, paramsBody,responseHandler);
	}
	public  void post(String url,Object headerCriteria,Object bodyCriteria,AsyncResponseHandler responseHandler) {
		post(url,BeanUtils.criteriaToHashMap(headerCriteria), BeanUtils.criteriaToHashMap(bodyCriteria),responseHandler);
	}
	public  void post(String url,LinkedHashMap<String, String> header,Object bodyCriteria,AsyncResponseHandler responseHandler) {
		post(url,header,BeanUtils.criteriaToHashMap(bodyCriteria),responseHandler);		
	}		
	private void sendRequest(String url,String method,LinkedHashMap<String, String> header,LinkedHashMap<String, String> paramsBody,AsyncResponseHandler responseHandler) {
		RequestParams params = new RequestParams(url,method,header,paramsBody,responseHandler);		
		AsyncHttpRequest request  = new AsyncHttpRequest(params);	
		request.execute();
	}
	public void put(String url, LinkedHashMap<String, String> header, Object criteria,AsyncResponseHandler responseHandler) {
//		put(url,BeanUtils.criteriaToHashMap(header),BeanUtils.criteriaToHashMap(criteria),responseHandler);
		put(url,header,BeanUtils.criteriaToHashMap(criteria),responseHandler);
	}
	
	public void put(String url, LinkedHashMap<String, String> header,
			LinkedHashMap<String, String> paramsBody,
			AsyncResponseHandler responseHandler) {
		sendRequest(url, ParaType.HTTPMETHOD_PUT,header,paramsBody,responseHandler);
	}
}
