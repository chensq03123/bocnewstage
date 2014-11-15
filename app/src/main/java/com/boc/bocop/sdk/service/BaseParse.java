package com.boc.bocop.sdk.service;

import org.json.JSONException;

import com.boc.bocop.sdk.api.exception.ResponseError;
import com.google.gson.Gson;


/**
 * 返回的公共报文解析类
 * 
 * @author CindyLiu 
 * 
 */
public class BaseParse {
	public static ResponseError parseResponseError(String json)
			throws JSONException {
		Gson gson = new Gson();
		ResponseError data = gson.fromJson(json, ResponseError.class);
		return data;
	}

}
