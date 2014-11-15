package com.boc.bocop.sdk.service.engine.rate;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author fww5205
 * 牌价组报类
 */
public class RateBuilder {
	
	public static Map<String, String> searchRate(String currency) {
		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("crrncy", currency);
		
		return dataMap;
	}	

}
