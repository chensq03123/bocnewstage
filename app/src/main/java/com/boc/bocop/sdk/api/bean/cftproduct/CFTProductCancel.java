package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.ResponseEnumType;
import com.google.gson.annotations.Expose;


/**
 * 理财产品撤销挂单返回结果实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class CFTProductCancel extends ResponseBean {
	@Expose
	private String prodid = " ";
	@Expose
	private String pronam = " ";
	@Expose
	private String trstyp = " ";
	@Expose
	private String trscur = " ";
	@Expose
	private String charcd = " ";
	@Expose
	private String txcnt = " ";
	@Expose
	private String barprc = " ";
	@Expose
	private String txamt = " ";

	/**
	 * @return 产品代码
	 */
	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	/**
	 * @return 产品名称
	 */
	public String getPronam() {
		return pronam;
	}

	public void setPronam(String pronam) {
		this.pronam = pronam;
	}

	/**
	 * @return 交易类型code 00：认购 01：申购 02：赎回 03：红利再投 04：红利发放 05：（经过）利息返还 06：本金返还
	 *         07：起息前赎回 08：利息折份额
	 */
	public String getTrstyp() {
		return trstyp;
	}
	
	/**
	 * @return 交易类型code描述    00：认购 01：申购 02：赎回 03：红利再投 04：红利发放 05：（经过）利息返还 06：本金返还
	 *         07：起息前赎回 08：利息折份额
	 */
	public String getTrstypInfo(int code) {
		return ResponseEnumType.ProductTradeType.getMessage(code);
	}


	public void setTrstyp(String trstyp) {
		this.trstyp = trstyp;
	}

	/**
	 * @return 交易币种 code 001：人民币元 014：美元 012：英镑 013：港币 028:加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getTrscur() {
		return trscur;
	}
	
	/**
	 * @return 交易币种 code描述  001：人民币元 014：美元 012：英镑 013：港币 028:加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getTrscurInfo(int code) {
		return ResponseEnumType.ProductProcurType.getMessage(code);
	}

	public void setTrscur(String trscur) {
		this.trscur = trscur;
	}

	/**
	 * @return 钞汇标识 1：钞 2：汇 0：人民币钞汇
	 */
	public String getCharcd() {
		return charcd;
	}

	public void setCharcd(String charcd) {
		this.charcd = charcd;
	}

	/**
	 * @return 交易份额 格式为：整数为11位，小数为6位，位数不足时， 对于整数部分左补0 小数部分右补
	 *         00000000001000000
	 */
	public String getTxcnt() {
		return txcnt;
	}

	public void setTxcnt(String txcnt) {
		this.txcnt = txcnt;
	}

	/**
	 * @return 成交价格 如果是挂单的撤单，则此域无意义，返回0
	 */
	public String getBarprc() {
		return barprc;
	}

	public void setBarprc(String barprc) {
		this.barprc = barprc;
	}

	/**
	 * @return 交易金额 格式为：整数为15位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补
	 *         00000000000000100
	 */
	public String getTxamt() {
		return txamt;
	}

	public void setTxamt(String txamt) {
		this.txamt = txamt;
	}

}
