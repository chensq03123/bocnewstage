package com.boc.bocop.sdk.api.bean.oauth;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 登陆返回报文
 * @author cindy
 *
 */
public class OAuthResponseInfo extends ResponseBean{
	private String access_token = " ";
	private String token_type = " ";
	private String state;
	private String refresh_token = " ";
	private int expires_in = 0;
	private String welcome = " ";
	private String ismsgfull="";
	private String user_id;
	private String usersid;
	private String limit;
	private String usrlamt;
	private String usrhamt;
	private String usrdamt;
	private String usrmamt;
	private String usertype;
	private String userstatus;
	private String regtime;
	private String stime;
	private String ftime;
	private String failnum;
	
	private String realname;//实名制客户标识 0：否  1：是
	
	public String getIsmsgfull() {
		return ismsgfull;
	}
	public void setIsmsgfull(String ismsgfull) {
		this.ismsgfull = ismsgfull;
	}
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

	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int value) {
		this.expires_in = value;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsersid() {
		return usersid;
	}
	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getUsrlamt() {
		return usrlamt;
	}
	public void setUsrlamt(String usrlamt) {
		this.usrlamt = usrlamt;
	}
	public String getUsrhamt() {
		return usrhamt;
	}
	public void setUsrhamt(String usrhamt) {
		this.usrhamt = usrhamt;
	}
	public String getUsrdamt() {
		return usrdamt;
	}
	public void setUsrdamt(String usrdamt) {
		this.usrdamt = usrdamt;
	}
	public String getUsrmamt() {
		return usrmamt;
	}
	public void setUsrmamt(String usrmamt) {
		this.usrmamt = usrmamt;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getFailnum() {
		return failnum;
	}
	public void setFailnum(String failnum) {
		this.failnum = failnum;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
}
