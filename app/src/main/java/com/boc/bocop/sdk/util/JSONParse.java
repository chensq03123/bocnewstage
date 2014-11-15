package com.boc.bocop.sdk.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.oauth.OAuthResponseInfo;
import com.boc.bocop.sdk.api.bean.oauth.RegisterResponse;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.google.gson.Gson;


public class JSONParse {
	public Map<String, String> tokenParseJson(InputStream in) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		byte[] b = new byte[1024];
		int a = in.read(b);
		String jsonString = new String(b, 0, a);
		String ss = "[" + jsonString.split("\"user")[0] + "\"access_token"
				+ jsonString.split("access_token")[1] + "]";
		JSONArray jsonArray = new JSONArray(ss);
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String access_token = object.getString("access_token");
			String expires_in = object.getString("expires_in");
			String refresh_token = object.getString("refresh_token");
			map.put("access_token", access_token);
			map.put("expires_in", expires_in);
			map.put("refresh_token", refresh_token);
		}
		return map;

	}

	public String parseUserID(String json) {
		JSONArray mJsonArray = null;
		try {
			mJsonArray = new JSONArray(json);
			JSONObject mJsonObject = mJsonArray.getJSONObject(0);
			String UserID = mJsonObject.getString("uid");
			return UserID;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResponseError parseResponseError(String json)
			throws JSONException {
		Gson gson = new Gson();
		ResponseError data = gson.fromJson(json, ResponseError.class);
		return data;
	}
	
	public static OAuthResponseInfo decodeOAuth2Json(String json)
			throws JSONException {
		Gson gson = new Gson();
		OAuthResponseInfo data = gson.fromJson(json, OAuthResponseInfo.class);
		return data;
	}

	public static RegisterResponse decodeOAuthRegister2Json(String json)
			throws JSONException {
		Gson gson = new Gson();
		RegisterResponse data = gson.fromJson(json, RegisterResponse.class);
		
		return data;
	}
}
