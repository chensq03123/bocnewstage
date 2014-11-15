package com.boc.bocop.sdk.api.bean.oauth;

import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.common.Constants;

/**
 * 
 * @author liuwenchao
 *
 */
public class Token2CookieCriteria {
	public String user_id;
	
	public String client_id;
	/**
	 * 访问令牌
	 */
	public String access_token;
	/**
	 * 刷新令牌
	 */
	public String refresh_token;
	
	public String expiresTime;
	
	public Token2CookieCriteria() {
		client_id = AppInfo.getAppKeyValue();
	}
}
