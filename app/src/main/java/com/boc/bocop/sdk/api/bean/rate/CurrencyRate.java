package com.boc.bocop.sdk.api.bean.rate;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * @author fww5205 汇率实体类
 */
public class CurrencyRate extends ResponseBean {
	@Expose
	private String crrncy = " ";
	@Expose
	private String bbuyrt = " ";
	@Expose
	private String bsalrt = " ";
	@Expose
	private String mrate = " ";
	@Expose
	private String hbuyrt = " ";
	@Expose
	private String hsalrt = " ";

	/**
	 * @return 币别
	 */
	public String getCurrency() {
		return crrncy;
	}

	public void setCurrency(String currency) {
		this.crrncy = currency;
	}

	/**
	 * @return 现钞买入价 格式为：整数为5位，小数为4位，位数不足时， 对于整数部分左补0 小数部分右补 000010000
	 */
	public String getbBuyRate() {
		return bbuyrt;
	}

	public void setbBuyRate(String bBuyRate) {
		this.bbuyrt = bBuyRate;
	}

	/**
	 * @return 现钞卖出价 格式为：整数为5位，小数为4位，位数不足时， 对于整数部分左补0 小数部分右补 000010000
	 */
	public String getbSaleRate() {
		return bsalrt;
	}

	public void setbSaleRate(String bSaleRate) {
		this.bsalrt = bSaleRate;
	}

	/**
	 * @return 中间价 格式为：整数为5位，小数为4位，位数不足时， 对于整数部分左补0 小数部分右补 000010000
	 */
	public String getRate() {
		return mrate;
	}

	public void setRate(String rate) {
		this.mrate = rate;
	}

	/**
	 * @return 汇买入价 格式为：整数为5位，小数为4位，位数不足时， 对于整数部分左补0 小数部分右补 000010000
	 */
	public String gethBuyRate() {
		return hbuyrt;
	}

	public void sethBuyRate(String hBuyRate) {
		this.hbuyrt = hBuyRate;
	}

	/**
	 * @return 汇卖出价 格式为：整数为5位，小数为4位，位数不足时， 对于整数部分左补0 小数部分右补 000010000
	 */
	public String gethSaleRate() {
		return hsalrt;
	}

	public void sethSaleRate(String hSaleRate) {
		this.hsalrt = hSaleRate;
	}

}
