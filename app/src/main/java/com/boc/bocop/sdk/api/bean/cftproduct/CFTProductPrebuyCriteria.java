package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.NormalCriteria;

/**
 * 理财产品预买入
 * 
 * @author alan
 * 
 */
public class CFTProductPrebuyCriteria extends NormalCriteria {

	private String alias = " ";
	private String proid = " ";
	private String chrcde = " ";
	private String puramt = " ";
	private String makno = " ";
	private String isrnew = " ";
	private String cyccnt = " ";
	private String agrhed = " ";
	private String agrpro = " ";

	public String getAlias() {
		return alias;
	}

	/**
	 * 设置 别名
	 * 
	 * @param alias
	 *            别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getProid() {
		return proid;
	}

	/**
	 * 设置 产品代码 必输
	 * 
	 * @param proid
	 *            产品代码
	 */
	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getChrcde() {
		return chrcde;
	}

	/**
	 * 设置 钞汇标识
	 * 
	 * @param chrcde
	 *            钞汇标识 1：钞 2：汇 0：人民币
	 */
	public void setChrcde(String chrcde) {
		this.chrcde = chrcde;
	}

	public String getPuramt() {
		return puramt;
	}

	/**
	 * 设置 购买金额
	 * 
	 * @param puramt
	 *            购买金额 必输 格式为：整数为15位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补0
	 *            00000000000000100
	 */
	public void setPuramt(String puramt) {
		this.puramt = puramt;
	}

	public String getMakno() {
		return makno;
	}

	/**
	 * 设置 柜员营销代码
	 * 
	 * @param makno
	 *            柜员营销代码 输入人力部员工号，
	 */
	public void setMakno(String makno) {
		this.makno = makno;
	}

	public String getIsrnew() {
		return isrnew;
	}

	/**
	 * 设置 是否自动续期
	 * 
	 * @param isrnew
	 *            是否自动续期 0：自动续期 1：不自动续期 为周期性产品时不为空 非周期产品上送空格
	 */
	public void setIsrnew(String isrnew) {
		this.isrnew = isrnew;
	}

	public String getCyccnt() {
		return cyccnt;
	}

	/**
	 * 设置买入期数
	 * 
	 * @param cyccnt
	 *            买入期数
	 */
	public void setCyccnt(String cyccnt) {
		this.cyccnt = cyccnt;
	}

	public String getAgrhed() {
		return agrhed;
	}

	/**
	 * 设置 总协议签署情况
	 * 
	 * @param agrhed
	 *            总协议签署情况 未签 已签
	 */
	public void setAgrhed(String agrhed) {
		this.agrhed = agrhed;
	}

	public String getAgrpro() {
		return agrpro;
	}

	/**
	 * 设置 产品协议签署情况
	 * 
	 * @param agrpro
	 *            产品协议签署情况 未签 已签
	 */
	public void setAgrpro(String agrpro) {
		this.agrpro = agrpro;
	}

}
