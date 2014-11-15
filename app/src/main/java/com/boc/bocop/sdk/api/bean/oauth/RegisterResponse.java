package com.boc.bocop.sdk.api.bean.oauth;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * HTML5注册页面返回信息
 * @author liuwenchao
 *
 */
public class RegisterResponse extends ResponseBean{
	//{"access_token":"?","token_type":"?","refresh_token":"?","expires_in":"?","welcome":"?"}
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String welcome;
	
	private String userid;
	private String cookie;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
}
