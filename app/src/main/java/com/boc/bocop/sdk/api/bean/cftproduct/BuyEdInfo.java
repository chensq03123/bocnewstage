package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.ResponseEnumType;
import com.google.gson.annotations.Expose;


/**
 * 理财产品买入情况查询返回多笔循环内容实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class BuyEdInfo extends ResponseBean {
	@Expose
	private String tsdate = " ";
	@Expose
	private String proid = " ";
	@Expose
	private String pronam = " ";
	@Expose
	private String trstyp = " ";
	@Expose
	private String trscur = " ";
	@Expose
	private String barprc = " ";
	@Expose
	private String texamt = " ";
	@Expose
	private String chrrcd = " ";
	@Expose
	private String txcnt = " ";
	@Expose
	private String trschn = " ";
	@Expose
	private String status = " ";
	@Expose
	private String iscncl = " ";
	@Expose
	private String trsseq = " ";
	@Expose
	private String attr = " ";

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
	 * @return 产品代码
	 */
	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
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
	 * @return 交易类型code  0：认购 1：申购 2：赎回 3：红利再投 4：红利发放 5：（经过）利息返还 06：本金返还 07：起息前赎回
	 *         08：利息折份额
	 */
	public  String getTrstyp() {
		return trstyp;
	}
	
	/**
	 * @return 交易类型code对应描述    0：认购 1：申购 2：赎回 3：红利再投 4：红利发放 5：（经过）利息返还 06：本金返还 07：起息前赎回
	 *         08：利息折份额
	 */
	public String getTrstypInfo(int code) {
		return ResponseEnumType.ProductTradeType.getMessage(code);
	}

	public void setTrstyp(String trstyp) {
		this.trstyp = trstyp;
	}

	/**
	 * @return 交易币种code 001：人民币元 014：美元 012：英镑 013：港币 028:加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getTrscur() {
		return trscur;
	}
	
	/**
	 * @return 交易币种 code描述   001：人民币元 014：美元 012：英镑 013：港币 028:加拿大元 029：澳元 038：欧元
	 *         027：日元
	 */
	public String getTrscurInfo(int code) {
		return ResponseEnumType.ProductProcurType.getMessage(code);
	}

	public void setTrscur(String trscur) {
		this.trscur = trscur;
	}

	/**
	 * @return 成交价格 格式为：整数为6位，小数为6位，位数不足时， 对于整数部分左补0 小数部分右补0 000001000000
	 */
	public String getBarprc() {
		return barprc;
	}

	public void setBarprc(String barprc) {
		this.barprc = barprc;
	}

	/**
	 * @return 交易金额 必输 格式为：整数为11位，小数为6位，位数不足时， 对于整数部分左补0 小数部分右补0
	 *         00000000000000100
	 */
	public String getTexamt() {
		return texamt;
	}

	public void setTexamt(String texamt) {
		this.texamt = texamt;
	}

	/**
	 * @return 钞汇标识 1：钞 2：汇 0：人民币钞汇
	 */
	public String getChrrcd() {
		return chrrcd;
	}

	public void setChrrcd(String chrrcd) {
		this.chrrcd = chrrcd;
	}

	/**
	 * @return 交易份额 必输 格式为：整数为11位，小数为6位，位数不足时， 对于整数部分左补0 小数部分右补0
	 *         00000000001000000
	 */
	public String getTxcnt() {
		return txcnt;
	}

	public void setTxcnt(String txcnt) {
		this.txcnt = txcnt;
	}

	/**
	 * @return 渠道 交易渠道 0：理财系统交易 1：（核心系统ofp）柜面 2：网银 3：电话银行
	 */
	public String getTrschn() {
		return trschn;
	}

	public void setTrschn(String trschn) {
		this.trschn = trschn;
	}

	/**
	 * @return 状态 状态 0：正常 1：失败 2：已赎回 3：已撤销 4：已冲正 8：挂单委托 9：挂单撤销
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return 是否可撤单 0：是 1：否
	 *         (当交易属性为挂单成交、状态为挂单委托，此域表示挂单委托是否可撤单。与理财夜间批量冲突等特定时段也返回‘否’)
	 */
	public String getIscncl() {
		return iscncl;
	}

	public void setIscncl(String iscncl) {
		this.iscncl = iscncl;
	}

	/**
	 * @return 交易流水号 理财系统交易流水号
	 */
	public String getTrsseq() {
		return trsseq;
	}

	public void setTrsseq(String trsseq) {
		this.trsseq = trsseq;
	}

	/**
	 * @return 交易属性 00：普通交易 01：自动续约交易 02：预约成交交易 03：质押成交 04：投资成交 05：挂单成交
	 */
	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

}
