/**
 * 
 */
package com.boc.bocop.sdk.api.bean.bill;

import java.io.Serializable;

/**
 * @author ZY
 * 查询已出账单
 */
public class IssuedBillQueryCriteria {
	private String userid = " ";	//用户ID （必输）
	private String limitamt = " ";  //卡唯一标识 （必输）
	private String currency = " ";  //币种
	private String accdate = " ";   //账单日期（必输）
	private String stnum = "";  	//X(9)	开始条数,查询交易内容的起始位置
	private String selnum = "";  	//X(9)	选择条数,本次查询交易需要返回的结果数量
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLimitamt() {
		return limitamt;
	}
	public void setLimitamt(String limitamt) {
		this.limitamt = limitamt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAccdate() {
		return accdate;
	}
	public void setAccdate(String accdate) {
		this.accdate = accdate;
	}
	public String getStnum() {
		return stnum;
	}
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	public String getSelnum() {
		return selnum;
	}
	public void setSelnum(String selnum) {
		this.selnum = selnum;
	}
	@Override
	public String toString() {
		return "IssuedBillQueryCriteria [userid=" + userid + ", limitamt="
				+ limitamt + ", currency=" + currency + ", accdate=" + accdate
				+ ", stnum=" + stnum + ", selnum=" + selnum + "]";
	}
	
	
	
}
