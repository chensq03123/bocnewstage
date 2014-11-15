package com.boc.bocop.sdk.service.engine.accfund;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.accfund.AccFundBalInfo;
import com.boc.bocop.sdk.api.bean.accfund.AccFundDepositInfo;
import com.boc.bocop.sdk.api.bean.accfund.DepositInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class AccFundParse {

	public static AccFundBalInfo parseQueryAccFundBalInfoResponse(
			String response) throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create(); 
		AccFundBalInfo data = gson.fromJson(response, AccFundBalInfo.class);
		return data;
	}

	public static AccFundDepositInfo parseQueryAccFundDepositInfoResponse(
			String response) throws JSONException {

		// 解析Json数据
		JSONObject jsonObject = new JSONObject(response);

		// 生成返回数据
		AccFundDepositInfo data = new AccFundDepositInfo();
		data.setRcdcnt(Integer.parseInt(jsonObject.getString("rcdcnt")));
		if (Integer.parseInt(jsonObject.getString("rcdcnt")) > 0) {
			Type listType = new TypeToken<LinkedList<DepositInfo>>(){}.getType();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.create(); 
			List<DepositInfo> depositInfos = new ArrayList<DepositInfo>();
			LinkedList<DepositInfo> jsonlists = gson.fromJson(jsonObject.getString("saplist"), listType);
			for (Iterator<DepositInfo> iterator = jsonlists.iterator(); iterator.hasNext();) {
				DepositInfo depositInfo =  (DepositInfo) iterator.next();
				depositInfos.add(depositInfo);
			}

			data.setDepositInfos(depositInfos);
		}

		return data;
	}
}
