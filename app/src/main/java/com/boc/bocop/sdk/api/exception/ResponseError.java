package com.boc.bocop.sdk.api.exception;

/**
 * 中银开放平台支付Android SDK 錯誤处理类
 * 
 * @author CindyLiu
 * @version V1.0, 2013-1-10
 */
public class ResponseError extends Error {

	private static final long serialVersionUID = -7696653925745147418L;

	private String msgcde;
	private String rtnmsg;
	
	public ResponseError() {
		super();
	}

	public ResponseError(String msgcde,String rtnmsg ){
		super(rtnmsg);
		this.msgcde=msgcde;
		this.rtnmsg=rtnmsg;
	}
	
	/**
	 * @return 錯誤狀態碼，用户可以通过ResponseListener的onError，取得錯誤狀態碼
	 */
	public String getMsgcde() {
		return msgcde;
	}

	public void setMsgcde(String msgcde) {
		this.msgcde = msgcde;
	}

	/**
	 * @return 错误状态描述信息，用户可以通过ResponseListener的onError，取得错误状态描述信息
	 */
	public String getRtnmsg() {
		return rtnmsg;
	}

	public void setRtnmsg(String rtnmsg) {
		this.rtnmsg = rtnmsg;
	}

}
