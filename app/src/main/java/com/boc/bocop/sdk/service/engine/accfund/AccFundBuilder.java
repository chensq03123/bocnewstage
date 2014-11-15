package com.boc.bocop.sdk.service.engine.accfund;

import java.util.LinkedHashMap;
import java.util.Map;

public class AccFundBuilder {

	public static LinkedHashMap<String, String> queryAccFundBalInfo(String alias) {
		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		
		dataMap.put("alias", alias);
		return dataMap;
	}	
	
	public static LinkedHashMap<String, String> queryAccFundDepositInfo(String alias,String sdate,String edate) {
		
		Map<String,String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("alias", alias);
		dataMap.put("sdate", sdate);	
		dataMap.put("edate", edate);
		
		return (LinkedHashMap<String, String>) dataMap;
	}		
	
}
