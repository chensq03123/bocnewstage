package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.ResponseEnumType;
import com.google.gson.annotations.Expose;


/**
 * 理财产品预买入返回内容实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class CFTProductPrebuy extends ResponseBean {
	@Expose
	private String pronam = " ";
	@Expose
	private String procur = " ";
	@Expose
	private String isrnew = " ";
	@Expose
	private String esdate = " ";
	@Expose
	private String edate = " ";
	@Expose
	private String purval = " ";
	@Expose
	private String prorsk = " ";
	@Expose
	private String cusrnk = " ";
	@Expose
	private String byprsk = " ";
	@Expose
	private String grexp = " ";
	@Expose
	private String trsseq = " ";
	@Expose
	private String ordertm = " ";

	/**
	 * @return 返回 产品名称
	 */
	public String getPronam() {
		return pronam;
	}

	public void setPronam(String pronam) {
		this.pronam = pronam;
	}

	/**
	 * @return 产品币种code描述   001：人民币元 014：美元 012：英镑 013：港币 028:加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getProcurInfo(int procur) {
		return ResponseEnumType.ProductProcurType.getMessage(procur);
	}
	
	/**
	 * @return 产品币种code  001：人民币元 014：美元 012：英镑 013：港币 028:加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getProcurCode() {
		return procur;
	}

	public void setProcur(String procur) {
		this.procur = procur;
	}

	/**
	 * @return 是否自动续期 0：自动续期 1：不自动续期 为周期性产品时不为空 非周期产品返回空格
	 */
	public String getIsrnew() {
		return isrnew;
	}

	public void setIsrnew(String isrnew) {
		this.isrnew = isrnew;
	}

	/**
	 * @return 产品成立日 日期格式：yyyymmdd
	 */
	public String getEsdate() {
		return esdate;
	}

	public void setEsdate(String esdate) {
		this.esdate = esdate;
	}

	/**
	 * @return 产品到期日 日期格式：yyyymmdd
	 */
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	/**
	 * @return 购买价格
	 */
	public String getPurval() {
		return purval;
	}

	public void setPurval(String purval) {
		this.purval = purval;
	}

	/**
	 * @return 产品风险级别 0：极低风险产品 1：低风险产品 2：中等风险产品 3：较高风险产品 4：高风险产品
	 */
	public String getProrsk() {
		return prorsk;
	}

	public void setProrsk(String prorsk) {
		this.prorsk = prorsk;
	}

	/**
	 * @return 客户风险等级 必输 1：保守型投资者 2：稳健型投资者 3：平衡型投资者 4：成长型投资者 5：进取型投资者
	 */
	public String getCusrnk() {
		return cusrnk;
	}

	public void setCusrnk(String cusrnk) {
		this.cusrnk = cusrnk;
	}

	/**
	 * @return 客户是否超过产品风险等级 0：是（需提示） 1：否
	 */
	public String getByprsk() {
		return byprsk;
	}

	public void setByprsk(String byprsk) {
		this.byprsk = byprsk;
	}

	/**
	 * @return 客户是否无投资经验买了有投资经验 0：是 1：否
	 */
	public String getGrexp() {
		return grexp;
	}

	public void setGrexp(String grexp) {
		this.grexp = grexp;
	}

	/**
	 * @return 交易号 买入交易使用
	 */
	public String getTrsseq() {
		return trsseq;
	}

	public void setTrsseq(String trsseq) {
		this.trsseq = trsseq;
	}

	/**
	 * @return 是否处于挂单时间 0：是 1：否
	 */
	public String getOrdertm() {
		return ordertm;
	}

	public void setOrdertm(String ordertm) {
		this.ordertm = ordertm;
	}

}
