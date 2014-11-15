package com.boc.bocop.sdk.service.engine.rate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.rate.CurrencyRate;
import com.boc.bocop.sdk.api.bean.rate.CurrencyRateSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class RateParse {

	public static CurrencyRateSearch parseRateSearch(String response)
			throws JSONException {

		// 解析Json数据
		JSONObject jsonObject = new JSONObject(response);

		// 生成返回数据
		CurrencyRateSearch data = new CurrencyRateSearch();
		data.setRcdcnt(Integer.parseInt(jsonObject.getString("rcdcnt")));
		if (Integer.parseInt(jsonObject.getString("rcdcnt")) > 0) {
			Type listType = new TypeToken<LinkedList<CurrencyRate>>() {
			}.getType();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.create(); 
			List<CurrencyRate> rates = new ArrayList<CurrencyRate>();
			LinkedList<CurrencyRate> jsonlists = gson.fromJson(
					jsonObject.getString("saplist"), listType);
			for (Iterator<CurrencyRate> iterator = jsonlists.iterator(); iterator
					.hasNext();) {
				CurrencyRate rate = (CurrencyRate) iterator.next();
				rates.add(rate);
			}
			data.setRates(rates);
		}
		return data;
	}
}
