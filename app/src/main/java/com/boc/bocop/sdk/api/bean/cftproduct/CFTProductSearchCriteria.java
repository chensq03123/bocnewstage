package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.SearchCriteria;

/**
 * 理财产品查询输入信息实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class CFTProductSearchCriteria extends SearchCriteria {

	private String alias = " ";
	private String proid = " ";
	private String brndid = " ";
	private String prorty = " ";
	private String procur = " ";
	private String protrm = " ";
	private String prosta = " ";
	private String pageno = " ";

	public String getAlias() {
		return alias;
	}

	/**
	 * 设置 别名
	 * 
	 * @param alias
	 *            别名
	 * 
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getProid() {
		return proid;
	}

	/**
	 * 设置 产品代码
	 * 
	 * @param proid
	 *            产品代码
	 * 
	 */
	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getBrndid() {
		return brndid;
	}

	/**
	 * 设置 产品品牌
	 * 
	 * @param brndid
	 *            产品品牌编码 0：全部
	 * 
	 */
	public void setBrndid(String brndid) {
		this.brndid = brndid;
	}

	public String getProrty() {
		return prorty;
	}

	/**
	 * 设置 产品风险类型
	 * 
	 * @param prorty
	 *            产品风险类型 0：全部 1：保本固定收益、 2：保本浮动收益、 3：非保本浮动收益
	 * 
	 */
	public void setProrty(String prorty) {
		this.prorty = prorty;
	}

	public String getProcur() {
		return procur;
	}

	/**
	 * 设置 产品币种
	 * 
	 * @param procur
	 *            产品币种 000：全部、 001：人民币元 014：美元 012：英镑 013：港币 028: 加拿大元 029：澳元
	 *            038：欧元 027：日元
	 * 
	 */

	public void setProcur(String procur) {
		this.procur = procur;
	}

	public String getProtrm() {
		return protrm;
	}

	/**
	 * 设置 产品期限
	 * 
	 * @param protrm
	 *            产品期限 0：全部、 1：3个月以内、 2：3个月-6个月、 3：6个月-12个月、 4：12个月-24个月、
	 *            5：24个月以上
	 * 
	 */

	public void setProtrm(String protrm) {
		this.protrm = protrm;
	}

	public String getProsta() {
		return prosta;
	}

	/**
	 * 设置 产品销售状态
	 * 
	 * @param prosta
	 *            设置产品销售状态 0：全部产品、 1：在售产品、 2：停售产品
	 * 
	 */

	public void setProsta(String prosta) {
		this.prosta = prosta;
	}

	public String getPageno() {
		return pageno;
	}

	/**
	 * 设置 页码
	 * 
	 * @param pageno
	 *            多包返回时，初始值（首包）赋值为1。若回复包中为000000表示无后续包
	 * 
	 */

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

}
