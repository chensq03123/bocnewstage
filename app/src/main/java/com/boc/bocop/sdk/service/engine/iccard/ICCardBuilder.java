package com.boc.bocop.sdk.service.engine.iccard;

import java.util.LinkedHashMap;

import com.boc.bocop.sdk.api.bean.iccard.ICardCriteria;

public class ICCardBuilder {
	
	public static LinkedHashMap<String, String> queryICCardBalInfo(ICardCriteria searchCriteria) {
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("alias", searchCriteria.alias);
	
		return dataMap;
	}	
	
	public static LinkedHashMap<String, String> queryICCardTransferDetail(ICardCriteria searchCriteria) {
			LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
			dataMap.put("alias", searchCriteria.alias);
			dataMap.put("stdate", searchCriteria.stdate);
			dataMap.put("endate", searchCriteria.endate);
			dataMap.put("pagnum", searchCriteria.pagnum);
			dataMap.put("recdno", searchCriteria.recdno);
		
			return dataMap;
		}	

}
