package com.boc.bocop.sdk.api.bean.accfund;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * 公积金账户余额返回数据实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class AccFundBalInfo extends ResponseBean {
	@Expose
	private String accno = " ";
	@Expose
	private String bal = " ";

	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * @return 公积金账号
	 */
	public String getAccno() {
		return accno;
	}

	public void setBal(String bal) {
		this.bal = bal;
	}

	/**
	 * @return 账户余额
	 */
	public String getBal() {
		return bal;
	}

}
