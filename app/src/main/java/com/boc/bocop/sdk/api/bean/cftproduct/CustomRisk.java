package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * 理财产品查询客户风险等级返回结果实体类
 * 
 * @author alan
 * 
 */
public class CustomRisk extends ResponseBean {
	@Expose
	private String expfall = " ";
	@Expose
	private String cusex = " ";
	@Expose
	private String iseva = " ";
	@Expose
	private String evachn = " ";
	@Expose
	private String cusrnk = " ";
	@Expose
	private String isexp = " ";
	@Expose
	private String evadate = " ";
	@Expose
	private String evalif = " ";

	/**
	 * @return 风险评测是否到期 0：是 1：否 未做过风险评估/不存在此客户返回空格
	 */
	public String getExpfall() {
		return expfall;
	}

	public void setExpfall(String expfall) {
		this.expfall = expfall;
	}

	/**
	 * @return 是否存在此客户 0：存在且总协议有效 1：存在但总协议已过期 2：不存在，首次签约不限渠道 3：不存在但首次签约仅限柜面
	 */

	public String getCusex() {
		return cusex;
	}

	public void setCusex(String cusex) {
		this.cusex = cusex;
	}

	/**
	 * @return 是否做过风险评估 0：是 1：否 iseva
	 */
	public String getIseva() {
		return iseva;
	}

	public void setIseva(String iseva) {
		this.iseva = iseva;
	}

	/**
	 * @return 风险评估渠道 0：系统交易 1：ofp柜面 2：网银 3：电话银行 4：财富系统 未做过风险评估/不存在此客户返回空格
	 */
	public String getEvachn() {
		return evachn;
	}

	public void setEvachn(String evachn) {
		this.evachn = evachn;
	}

	/**
	 * @return 风险等级结果 必输 1：保守型投资者 2：稳健型投资者 3：平衡型投资者 4：成长型投资者 5：进取型投资者
	 */
	public String getCusrnk() {
		return cusrnk;
	}

	public void setCusrnk(String cusrnk) {
		this.cusrnk = cusrnk;
	}

	/**
	 * @return 是否有投资经验 0：是 1：否 未做过风险评估返回空格
	 */
	public String getIsexp() {
		return isexp;
	}

	public void setIsexp(String isexp) {
		this.isexp = isexp;
	}

	/**
	 * @return 风险评测日期 未做过风险评估返回空格
	 */
	public String getEvadate() {
		return evadate;
	}

	public void setEvadate(String evadate) {
		this.evadate = evadate;
	}

	/**
	 * @return 风险评估有效期 未做过风险评估返回空格
	 */
	public String getEvalif() {
		return evalif;
	}

	public void setEvalif(String evalif) {
		this.evalif = evalif;
	}

}
