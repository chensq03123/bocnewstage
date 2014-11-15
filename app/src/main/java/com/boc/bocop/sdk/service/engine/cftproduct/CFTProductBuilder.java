package com.boc.bocop.sdk.service.engine.cftproduct;

import java.util.LinkedHashMap;
import java.util.Map;

import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuyEdInfoCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductPrebuyCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductSearchCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CustomRiskCriteria;

public class CFTProductBuilder {

	public static LinkedHashMap<String, String> queryCFTProduct(CFTProductSearchCriteria searchCriteria) {

		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("alias", searchCriteria.getAlias());
		dataMap.put("proid", searchCriteria.getProid());
		dataMap.put("brndid",searchCriteria.getBrndid());
		dataMap.put("prorty",searchCriteria.getProrty());
		dataMap.put("procur",searchCriteria.getProcur());
		dataMap.put("prosta",searchCriteria.getProsta());
		dataMap.put("protrm",searchCriteria.getProtrm());
		dataMap.put("pageno",searchCriteria.getPageno());	

		return dataMap;
	}
	
	public static LinkedHashMap<String, String> cancelCFTProduct(String alias,String trsseq,String isorder) {
		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		
		dataMap.put("alias", alias);
		dataMap.put("trsseq", trsseq);	
		dataMap.put("isorder", isorder);	
		
		return dataMap;
	}	
	
	public static LinkedHashMap<String, String> prebuyCFTProduct(CFTProductPrebuyCriteria preBuyCriteria) {
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		
		dataMap.put("alias", preBuyCriteria.getAlias());
		dataMap.put("proid", preBuyCriteria.getProid());
		dataMap.put("chrcde", preBuyCriteria.getChrcde());
		dataMap.put("puramt", preBuyCriteria.getPuramt());
		dataMap.put("makno", preBuyCriteria.getMakno());
		
		dataMap.put("isrnew", preBuyCriteria.getIsrnew());
		dataMap.put("cyccnt", preBuyCriteria.getCyccnt());
		dataMap.put("agrhed", preBuyCriteria.getAgrhed());
		dataMap.put("agrpro", preBuyCriteria.getAgrpro());

		return dataMap;
	}	
	
	public static LinkedHashMap<String, String> buyCFTProduct(String alias,String trsseq,String etoken) {
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();

		dataMap.put("alias",alias);
		dataMap.put("trsseq", trsseq);
		dataMap.put("etoken", etoken);
		
		return dataMap;
	}	
	
	public static LinkedHashMap<String, String> queryCFTProductBuyEdInfo(CFTProductBuyEdInfoCriteria searchCriteria) {
		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		
		dataMap.put("alias", searchCriteria.getAlias());
		dataMap.put("memocd", searchCriteria.getMemocd());
		dataMap.put("procur", searchCriteria.getProcur());
		dataMap.put("sdate",searchCriteria.getSdate());

		dataMap.put("edate", searchCriteria.getEdate());
		dataMap.put("pageno", searchCriteria.getPageno());

		return dataMap;
	}	
	
	
	
	public static LinkedHashMap<String, String> searchCustomRisk(CustomRiskCriteria searchCriteria) {
		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();

		return dataMap;
	}		
}
