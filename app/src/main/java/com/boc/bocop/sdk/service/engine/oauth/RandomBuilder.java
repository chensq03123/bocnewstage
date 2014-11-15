package com.boc.bocop.sdk.service.engine.oauth;

import java.util.LinkedHashMap;

import com.boc.bocop.sdk.api.bean.oauth.RandomCriteria;


public class RandomBuilder {

	public static LinkedHashMap<String, String> buildRandom(RandomCriteria randomCriteria) {
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("clentid", randomCriteria.getClientId());
		dataMap.put("userid", randomCriteria.getUserId());
		dataMap.put("acton", randomCriteria.getAction());
		dataMap.put("chnflg", randomCriteria.getChannel());
		dataMap.put("trandt", randomCriteria.getTradeDate());
		dataMap.put("trantm", randomCriteria.getTradetime());
		return dataMap;
	}
}
