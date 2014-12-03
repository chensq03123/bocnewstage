package com.hustunique.bocp.Utils;
/**
 *@author  liuwenchao
 *@date 2014-4-21
 *@declare 普通卡卡转账上送报文
 */
public class ContactTransCriteria {
	private String userid;
	private String lmtamtout;//卡唯一标识
	private String lmtamtin;//转入卡唯一标识
	private String amount;//交易金额
	private String currency;//币种
	private String expdate;//卡有效期（可不传）
	private String username;
//	private String etokenval;//ETOKEN值
//	private String chkcode;//手机验证码
//	private String mobleno;//手机号
//	private String trantype;
	private String cardnumin;//输入卡号
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLmtamtout() {
		return lmtamtout;
	}
	public void setLmtamtout(String lmtamtout) {
		this.lmtamtout = lmtamtout;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	public String getLmtamtin() {
		return lmtamtin;
	}
	public void setLmtamtin(String lmtamtin) {
		this.lmtamtin = lmtamtin;
	}
	public String getCardnumin() {
		return cardnumin;
	}
	public void setCardnumin(String cardnumin) {
		this.cardnumin = cardnumin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
//	public String getTrantype() {
//		return trantype;
//	}
//	public void setTrantype(String trantype) {
//		this.trantype = trantype;
//	}
	


}
