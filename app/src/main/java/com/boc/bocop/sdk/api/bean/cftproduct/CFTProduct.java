package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.ResponseEnumType;
import com.google.gson.annotations.Expose;


/**
 * 理财产品查询返回多笔循环内容实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class CFTProduct extends ResponseBean {
	@Expose
	private String proid = " "; // PROID X(20) 产品代码
	@Expose
	private String pronam = " "; // PRONAM X(50) 产品名称 产品简称
	@Expose
	private String procur = " ";
	@Expose
	private String exyield = " "; // EXYIELD 9(15)V99 预计年收益率（%）
	@Expose
	private String purval = " "; // PURVAL 9(6)V9(6) 购买价格
	@Expose
	private String protrm = " "; // PROTERM X(5) 产品期限 以天数表示的产品到期日期限，等于产品到日期减去产品起息日。
	@Expose
	private String appobj = " ";
	@Expose
	private String prosta = " ";
	@Expose
	private String brndid = " "; // BRANDID 9(6) 产品名牌编码
	@Expose
	private String brndnm = " "; // BRANDNAME X(50) 产品品牌 产品系列所归属的产品品牌。中文
	@Expose
	private String subpmt = " "; // SUBPAMT 9(15)V99 认购起点金额
	@Expose
	private String addamt = " "; // ADDAMT 9(15)V99 追加认申购起点金额 addamt
	@Expose
	private String pursdt = " "; // PURSDATE X(8) 销售起始日期
	@Expose
	private String puredt = " "; // PUREDATE X(8) 销售结束日期
	@Expose
	private String intsdt = " "; // INTSDATE X(8) 产品起息日
	@Expose
	private String edate = " "; // EDATE X(8) 产品到期日
	@Expose
	private String ispre = " ";
	@Expose
	private String cyccnt = " "; // CYCCNT X(4) 剩余可购买最大期数 非周期性产品返回空
	@Expose
	private String prorsk = " ";
	@Expose
	private String autopm = " ";
	@Expose
	private String impwpm = " ";
	@Expose
	private String prourl = " ";
	//private String headurl = " ";

	/**
	 * @return 产品代码
	 */
	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	/**
	 * @return 产品名称 产品简称
	 */
	public String getPronam() {
		return pronam;
	}

	public void setPronam(String pronam) {
		this.pronam = pronam;
	}

	/**
	 * @return 产品币种code 001：人民币元 014：美元 012：英镑 013：港币 028: 加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getProcur() {
		return procur;
	}
	
	/**
	 * @return 产品币种code描述  001：人民币元 014：美元 012：英镑 013：港币 028: 加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getProcurInfo(int procur) {
		return ResponseEnumType.ProductProcurType.getMessage(procur);
	}


	public void setProcur(String procur) {
		this.procur = procur;
	}

	/**
	 * @return 预计年收益率（%）
	 */
	public String getExyield() {
		return exyield;
	}

	public void setExyield(String exyield) {
		this.exyield = exyield;
	}

	/**
	 * @return 购买价格 格式为：整数为9位，小数为9位，位数不足时， 对于整数部分左补0 小数部分右补零 000001000000
	 */
	public String getPurval() {
		return purval;
	}

	public void setPurval(String purval) {
		this.purval = purval;
	}

	/**
	 * @return 产品期限 以天数表示的产品到期日期限，等于产品到日期减去产品起息日。
	 */
	public String getProtrm() {
		return protrm;
	}

	public void setProtrm(String protrm) {
		this.protrm = protrm;
	}

	/**
	 * @return 适用对象 0：有投资经验 1：无投资经验
	 */
	public String getAppobj() {
		return appobj;
	}

	public void setAppobj(String appobj) {
		this.appobj = appobj;
	}

	/**
	 * @return 产品销售状态 1：在售产品、 2：停售产品
	 */
	public String getProsta() {
		return prosta;
	}

	public void setProsta(String prosta) {
		this.prosta = prosta;
	}

	/**
	 * @return 产品名牌编码
	 */
	public String getBrndid() {
		return brndid;
	}

	public void setBrndid(String brndid) {
		this.brndid = brndid;
	}

	/**
	 * @return 产品品牌 产品系列所归属的产品品牌。中文
	 */
	public String getBrndnm() {
		return brndnm;
	}

	public void setBrndnm(String brndnm) {
		this.brndnm = brndnm;
	}

	/**
	 * 
	 * @return 认购起点金额 格式为：整数为15位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补零
	 *         00000000000000100
	 */
	public String getSubpmt() {
		return subpmt;
	}

	public void setSubpmt(String subpmt) {
		this.subpmt = subpmt;
	}

	/**
	 * @return 追加认申购起点金额 格式为：整数为15位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补零
	 *         00000000000000100
	 */
	public String getAddamt() {
		return addamt;
	}

	public void setAddamt(String addamt) {
		this.addamt = addamt;
	}

	/**
	 * @return 销售起始日期 日期格式：yyyymmdd
	 */
	public String getPursdt() {
		return pursdt;
	}

	public void setPursdt(String pursdt) {
		this.pursdt = pursdt;
	}

	/**
	 * @return 销售结束日期 日期格式：yyyymmdd
	 */
	public String getPuredt() {
		return puredt;
	}

	public void setPuredt(String puredt) {
		this.puredt = puredt;
	}

	/**
	 * @return 产品起息日 日期格式：yyyymmdd
	 */
	public String getIntsdt() {
		return intsdt;
	}

	public void setIntsdt(String intsdt) {
		this.intsdt = intsdt;
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
	 * @return 是否周期性产品 0：是 1：否
	 */
	public String getIspre() {
		return ispre;
	}

	public void setIspre(String ispre) {
		this.ispre = ispre;
	}

	/**
	 * @return 剩余可购买最大期数 非周期性产品返回空 最大期数：9999
	 */
	public String getCyccnt() {
		return cyccnt;
	}

	public void setCyccnt(String cyccnt) {
		this.cyccnt = cyccnt;
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
	 * @return 是否允许定投与自动投资 0：是 1：否
	 */
	public String getAutopm() {
		return autopm;
	}

	public void setAutopm(String autopm) {
		this.autopm = autopm;
	}

	/**
	 * @return 是否可组合购买 0：是 1：否 即是否可质押
	 */
	public String getImpwpm() {
		return impwpm;
	}

	public void setImpwpm(String impwpm) {
		this.impwpm = impwpm;
	}

	/**
	 * @return 产品说明书url
	 */
	public String getProurl() {
		return prourl;
	}

	public void setProurl(String prourl) {
		this.prourl = prourl;
	}
	
}
