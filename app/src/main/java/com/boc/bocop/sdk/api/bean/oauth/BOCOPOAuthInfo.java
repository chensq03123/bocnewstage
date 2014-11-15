package com.boc.bocop.sdk.api.bean.oauth;

import com.boc.bocop.sdk.api.bean.ResponseBean;

public class BOCOPOAuthInfo extends ResponseBean {
	private String access_token = " ";
	private String userId = " ";
	private String refresh_token = " ";
	/**
	 * 用户信息是否完整
	 */
	private String ismsgfull="";

	public String getIsmsgfull() {
		return ismsgfull;
	}

	public void setIsmsgfull(String ismsgfull) {
		this.ismsgfull = ismsgfull;
	}

	/**
	 * @return Access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * @return UserId
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return Refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
}
