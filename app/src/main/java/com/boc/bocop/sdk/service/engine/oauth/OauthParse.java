package com.boc.bocop.sdk.service.engine.oauth;

import org.json.JSONException;

import com.boc.bocop.sdk.api.bean.oauth.OAuthResponseInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class OauthParse {
	public static OAuthResponseInfo parseOauthInfo(String response)
			throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create(); 
		OAuthResponseInfo data = gson.fromJson(response, OAuthResponseInfo.class);
		return data;
	}

}
