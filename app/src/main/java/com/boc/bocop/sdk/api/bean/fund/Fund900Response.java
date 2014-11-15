package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 基金挂单9000 服务器返回数据实体类
 * @author tongyapeng
返回报文内容
编号	字段	字段长度	说明	是否必输	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	fnacno	X(15)	基金交易账号	否	
4	cardno	X(19)	资金账号/卡号	否	
5	fncode	X(06)	基金代码	否	
6	fnname	X(20)	基金名称	否	
7	txntyp	X(03)	交易类型
下传的值同上传的值。	否	认购申请：020
申购申请：022
赎回申请：024
基金转换申请：036修改分红方式申请：029
8	fncodei	X(06)	转入基金代码	否	
9	fnnamei	X(20)	转入基金名称	否	
10	txnamt	X(17)	交易金额/交易份额	否	
11	curcde	X(03)	货币码	否	
12	appseq	X(12)	挂单流水号	否	
13	feemod	X(01)	收费方式	否	1：前端
2：后端
14	cntflg	X(01)	连续赎回标志/顺延转换标志	否	
15	bnsmod	X(01)	分红方式	否	
16	rskmatch	X(1)	风险匹配	否	N：风险不匹配
Y：风险匹配
17	name	X(80)	姓名/企业简称	否	汉字
18	fnrisklv	X(01)	基金产品风险等级	否	1：低风险
2：中低风险
3：中风险
4：中高风险
5：高风险
19	custlv	X(01)	客户风险等级	否	1：保守型
2：稳健型
3：平衡型
4：成长型
5：进取型
20	autopt	9(07)	授权柜员	否	
返回JSON报文
 {"userid":"?","accrem":"?","fnacno":"?","cardno":"?","fncode":"?","fnname":"?","txntyp":"?","fncodei":"?","fnnamei":"?",
"txnamt":"?","curcde":"?","appseq":"?","feemod":"?","cntflg":"?","bnsmod":"?","rskmatch":"?","name":"?","fnrisklv":"?","custlv":"?","autopt":"?"}
 */
public class Fund900Response extends ResponseBean {
	private String userid;
	private String accrem;
	private String fnacno;
	private String cardno;
	private String fncode;
	private String fnname;
	private String txntyp;
	private String fncodei;
	private String fnnamei;
	private String txnamt;
	private String curcde;
	private String appseq;
	private String feemod;
	private String cntflg;
	private String bnsmod;
	private String rskmatch;
	private String name;
	private String fnrisklv;
	private String custlv;
	private String autopt;
	
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
	public String getTxntyp() {
		return txntyp;
	}
	public void setTxntyp(String txntyp) {
		this.txntyp = txntyp;
	}
	public String getFncodei() {
		return fncodei;
	}
	public void setFncodei(String fncodei) {
		this.fncodei = fncodei;
	}
	public String getFnnamei() {
		return fnnamei;
	}
	public void setFnnamei(String fnnamei) {
		this.fnnamei = fnnamei;
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
	public String getAppseq() {
		return appseq;
	}
	public void setAppseq(String appseq) {
		this.appseq = appseq;
	}
	public String getFeemod() {
		return feemod;
	}
	public void setFeemod(String feemod) {
		this.feemod = feemod;
	}
	public String getCntflg() {
		return cntflg;
	}
	public void setCntflg(String cntflg) {
		this.cntflg = cntflg;
	}
	public String getBnsmod() {
		return bnsmod;
	}
	public void setBnsmod(String bnsmod) {
		this.bnsmod = bnsmod;
	}
	public String getRskmatch() {
		return rskmatch;
	}
	public void setRskmatch(String rskmatch) {
		this.rskmatch = rskmatch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getAutopt() {
		return autopt;
	}
	public void setAutopt(String autopt) {
		this.autopt = autopt;
	}	
	
	
	
}
