package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * 理财买入返回内容实体类
 * 
 */
public class CFTProductBuy extends ResponseBean {
	@Expose
	private String tsdate = " ";
	@Expose
	private String purcnt = " ";
	@Expose
	private String trdflg = " ";

	/**
	 * @return 交易日期 日期格式：yyyymmdd
	 */
	public String getTsdate() {
		return tsdate;
	}

	public void setTsdate(String tsdate) {
		this.tsdate = tsdate;
	}

	/**
	 * @return 购买份额 必输 格式为：整数为11位，小数为6位，位数不足时， 对于整数部分左补0 小数部分右补0
	 *         00000000001000000
	 */
	public String getPurcnt() {
		return purcnt;
	}

	public void setPurcnt(String purcnt) {
		this.purcnt = purcnt;
	}

	/**
	 * @return 成交类型 0:正常 1:挂单
	 */
	public String getTrdflg() {
		return trdflg;
	}

	public void setTrdflg(String trdflg) {
		this.trdflg = trdflg;
	}

}
