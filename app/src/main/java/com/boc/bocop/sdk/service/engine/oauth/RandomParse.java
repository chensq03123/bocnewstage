package com.boc.bocop.sdk.service.engine.oauth;

import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.oauth.RandomResponse;



public class RandomParse {

	public static RandomResponse parseRandomDetail(String response)
			throws JSONException {
		JSONObject jsonObject = new JSONObject(response);
		RandomResponse data = new RandomResponse();
		data.setRandomid(jsonObject.getString("randomid"));
		data.setRandom(jsonObject.getString("random"));
		return data;

	}
}
