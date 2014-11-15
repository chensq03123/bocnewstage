package com.boc.bocop.sdk.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.boc.bocop.sdk.api.event.ResponseListener;

public class AsyncPara {
	
	public AsyncPara(String url, String httpMethod, LinkedHashMap<String, String> paramsHead,LinkedHashMap<String, String> paramsBody,
			int type, ResponseListener listener) {
		super();
		this.paramsHead = paramsHead;
		this.paramsBody = paramsBody;
		this.httpMethod = httpMethod;
		this.url = url;
		this.listener = listener;
		this.type = type;
	}
	private LinkedHashMap<String, String> paramsHead;
	private LinkedHashMap<String, String> paramsBody;
	private String httpMethod;
	private String url;
	private ResponseListener listener;
	private int type;
	public Map<String, String> getParamsHead() {
		return paramsHead;
	}
	public void setParamsHead(LinkedHashMap<String, String> paramsHead) {
		this.paramsHead = paramsHead;
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
	public ResponseListener getListener() {
		return listener;
	}
	public void setListener(ResponseListener listener) {
		this.listener = listener;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
