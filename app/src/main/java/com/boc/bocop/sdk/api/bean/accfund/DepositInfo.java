package com.boc.bocop.sdk.api.bean.accfund;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 公积金账户缴存信息查询返回多笔循环内容实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class DepositInfo extends ResponseBean {
	@Expose
	private String date = " ";
	@Expose
	private String amount = " ";
	@Expose
	@SerializedName("rtnmsg")
	private String DepositInfortnmsg = " ";
	
	/**
	 * @return 日期 日期格式：yyyymmdd
	 */
	public String getDate() {
		return date;
	}

	
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return 金额
	 */
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return 备注
	 */
	public String getRtnmsg() {
		return DepositInfortnmsg;
	}

	public void setRtnmsg(String rtnmsg) {
		this.DepositInfortnmsg = rtnmsg;
	}

}
