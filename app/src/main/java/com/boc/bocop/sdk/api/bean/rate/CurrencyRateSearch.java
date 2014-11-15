package com.boc.bocop.sdk.api.bean.rate;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * @author fww5205 牌价查询下送报文实体类
 */

public class CurrencyRateSearch extends ResponseBean {

	private int rcdcnt = 0;

	private List<CurrencyRate> rates;

	/**
	 * @return 牌价查询 多笔循环内容
	 */
	public List<CurrencyRate> getRates() {
		return rates;
	}

	public void setRates(List<CurrencyRate> rates) {
		this.rates = rates;
	}

	/**
	 * @return 笔数
	 */
	public int getRcdcnt() {
		return rcdcnt;
	}

	public void setRcdcnt(int rcdcnt) {
		this.rcdcnt = rcdcnt;
	}

}
