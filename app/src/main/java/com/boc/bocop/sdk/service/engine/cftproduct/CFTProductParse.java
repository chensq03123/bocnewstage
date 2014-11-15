package com.boc.bocop.sdk.service.engine.cftproduct;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.cftproduct.BuyEdInfo;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProduct;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuy;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuyEdInfo;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductCancel;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductPrebuy;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductSearch;
import com.boc.bocop.sdk.api.bean.cftproduct.CustomRisk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class CFTProductParse {

	public static CFTProductSearch parseQueryResponse(String response)
			throws JSONException {
		// 解析Json数据
		JSONObject jsonObject = new JSONObject(response);

		// 生成返回数据
		CFTProductSearch data = new CFTProductSearch();
		data.setPageno(Long.parseLong(jsonObject.getString("pageno")));
		data.setRcdcnt(Integer.parseInt(jsonObject.getString("rcdcnt")));
		data.setHeadurl(jsonObject.getString("headurl"));

		if (Integer.parseInt(jsonObject.getString("rcdcnt")) > 0) {
			Type listType = new TypeToken<LinkedList<CFTProduct>>() {
			}.getType();
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			List<CFTProduct> products = new ArrayList<CFTProduct>();
			LinkedList<CFTProduct> jsonlists = gson.fromJson(
					jsonObject.getString("saplist"), listType);
			for (Iterator<CFTProduct> iterator = jsonlists.iterator(); iterator
					.hasNext();) {
				CFTProduct product =(CFTProduct) iterator.next();
				products.add(product);
			}
			data.setProducts(products);
		}
		return data;
	}

	public static CFTProductPrebuy parsePreBuyResponse(String response)
			throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		CFTProductPrebuy data = gson.fromJson(response, CFTProductPrebuy.class);
		return data;
	}

	public static CFTProductBuy parseBuyResponse(String response)
			throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		CFTProductBuy data = gson.fromJson(response, CFTProductBuy.class);
		return data;
	}

	public static CFTProductBuyEdInfo parseQueryBuyEdInfoResponse(
			String response) throws JSONException {
		// 解析Json数据
		JSONObject jsonObject = new JSONObject(response);

		// 生成返回数据
		CFTProductBuyEdInfo data = new CFTProductBuyEdInfo();

		data.setAccno(jsonObject.getString("accno"));
		data.setPageno(Long.parseLong(jsonObject.getString("pageno")));
		data.setRcdcnt(Integer.parseInt(jsonObject.getString("rcdcnt")));

		if (Integer.parseInt(jsonObject.getString("rcdcnt")) > 0) {

			Type listType = new TypeToken<LinkedList<BuyEdInfo>>() {
			}.getType();
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			List<BuyEdInfo> buyEdInfos = new ArrayList<BuyEdInfo>();
			LinkedList<BuyEdInfo> jsonlists = gson.fromJson(
					jsonObject.getString("saplist"), listType);
			for (Iterator<BuyEdInfo> iterator = jsonlists.iterator(); iterator
					.hasNext();) {
				BuyEdInfo buyEdInfo = (BuyEdInfo) iterator.next();
				buyEdInfos.add(buyEdInfo);
			}
			data.setBuyEdInfos(buyEdInfos);
		}

		return data;
	}

	public static CFTProductCancel parseCancelResponse(String response)
			throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		CFTProductCancel data = gson.fromJson(response, CFTProductCancel.class);
		return data;
	}

	public static CustomRisk parseCustomRiskResponse(String response)
			throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		CustomRisk data = gson.fromJson(response, CustomRisk.class);
		return data;
	}
}
