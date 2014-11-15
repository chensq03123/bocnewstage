package com.boc.bocop.sdk.http;

import java.util.LinkedHashMap;

/**
 * 访问相关参数的封装类
 * @author tongyapeng
 *
 */
public class RequestParams {
	private LinkedHashMap<String, String> header;
	private LinkedHashMap<String, String> paramsBody;
	private String httpMethod;
	private String url;
	private AsyncResponseHandler responseHandler;
	
	public RequestParams(String url,String httpMethod, LinkedHashMap<String, String> header,LinkedHashMap<String, String> paramsBody,
			AsyncResponseHandler responseHandler) {
		this.header = header;
		this.paramsBody = paramsBody;
		this.httpMethod = httpMethod;
		this.url = url;
		this.responseHandler = responseHandler;
	}
	
	public LinkedHashMap<String, String> getHeader() {
		return header;
	}

	public void setHeader(LinkedHashMap<String, String> header) {
		this.header = header;
	}

	public LinkedHashMap<String, String> getParamsBody() {
		return paramsBody;
	}

	public void setParamsBody(LinkedHashMap<String, String> paramsBody) {
		this.paramsBody = paramsBody;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public AsyncResponseHandler getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(AsyncResponseHandler responseHandler) {
		this.responseHandler = responseHandler;
	}
	
}
