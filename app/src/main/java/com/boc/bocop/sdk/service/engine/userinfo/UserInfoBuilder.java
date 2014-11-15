package com.boc.bocop.sdk.service.engine.userinfo;

import java.util.LinkedHashMap;
import java.util.Map;

import com.boc.bocop.sdk.api.bean.useinfo.UserInfoCriteria;

/**
 * @author fww5205
 * 用户资料组报类
 */
public class UserInfoBuilder {

public static Map<String, String> searchUserInfo(UserInfoCriteria criteria) {
		
		LinkedHashMap<String,String> dataMap = new LinkedHashMap<String, String>();
		dataMap.put("accno", criteria.getAccno());
		dataMap.put("alias", criteria.getAlias());
		dataMap.put("trntyp", criteria.getTrantype());
		dataMap.put("ifncal", criteria.getIs_financial());
		dataMap.put("pageno", criteria.getPageno());
		
		return dataMap;
	}	
}
