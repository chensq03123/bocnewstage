package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 基金对私认购/申购交易返回信息实体类
 * @author tongyapeng
返回APP的公共报文头
参见APP接收SAP返回公共报文头
返回报文内容
编号	字段	字段长度	说明	是否必输	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	name	　X(80)	姓名/简称	否	
4	fnacno	X(15)	基金交易账号	否	
5	cardno	X(19)	资金账号/卡号	否	
6	fncode	X(06)	基金代码	否	
7	fnname	X(20)	基金名称	否	汉字
8	txnamt	X（17）	交易金额	否	
9	curcde	X(3)	货币码	否	
10	corseq	X(12)	核心流水号	否	
11	ftxseq	X(12)	基金交易流水号	否	
12	feemod	X(01)	收费方式	否	
13	autopt	9(07)	授权柜员	否	
14	date	X(08）	交易日期	否	
15	rskmatch	X(1)	风险匹配	否	N：风险不匹配
Y：风险匹配
16	fnrisklv	X(1)	基金产品风险等级	否	1：低风险
2：中低风险
3：中风险
4：中高风险
5：高风险
17	custlv	X(1)	客户风险等级	否	1：保守型
2：稳健型
3：平衡型
4：成长型
5：进取型
返回JSON报文
  {"userid":"?","accrem":"?","name":"?","fnacno":"?","cardno":"?","fncode":"?","fnname":"?","txnamt":"?","curcde":"?","corseq":"?",
"ftxseq":"?","feemod":"?","autopt":"?","date":"?","rskmatch":"?","fnrisklv":"?","custlv":"?"}
 */
public class FundPrivateDealResponse extends ResponseBean {
	private String userid;
	private String accrem;
	private String name;
	private String fnacno;
	private String cardno;
	private String fncode;
	private String fnname;
	private String txnamt;
	private String curcde;
	private String corseq;
	private String ftxseq;
	private String feemod;
	private String autopt;
	private String date;
	private String rskmatch;
	private String fnrisklv;
	private String custlv;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccrem() {
		return accrem;
	}
	public void setAccrem(String accrem) {
		this.accrem = accrem;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFnacno() {
		return fnacno;
	}
	public void setFnacno(String fnacno) {
		this.fnacno = fnacno;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getFncode() {
		return fncode;
	}
	public void setFncode(String fncode) {
		this.fncode = fncode;
	}
	public String getFnname() {
		return fnname;
	}
	public void setFnname(String fnname) {
		this.fnname = fnname;
	}
	public String getTxnamt() {
		return txnamt;
	}
	public void setTxnamt(String txnamt) {
		this.txnamt = txnamt;
	}
	public String getCurcde() {
		return curcde;
	}
	public void setCurcde(String curcde) {
		this.curcde = curcde;
	}
	public String getCorseq() {
		return corseq;
	}
	public void setCorseq(String corseq) {
		this.corseq = corseq;
	}
	public String getFtxseq() {
		return ftxseq;
	}
	public void setFtxseq(String ftxseq) {
		this.ftxseq = ftxseq;
	}
	public String getFeemod() {
		return feemod;
	}
	public void setFeemod(String feemod) {
		this.feemod = feemod;
	}
	public String getAutopt() {
		return autopt;
	}
	public void setAutopt(String autopt) {
		this.autopt = autopt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRskmatch() {
		return rskmatch;
	}
	public void setRskmatch(String rskmatch) {
		this.rskmatch = rskmatch;
	}
	public String getFnrisklv() {
		return fnrisklv;
	}
	public void setFnrisklv(String fnrisklv) {
		this.fnrisklv = fnrisklv;
	}
	public String getCustlv() {
		return custlv;
	}
	public void setCustlv(String custlv) {
		this.custlv = custlv;
	}
}
