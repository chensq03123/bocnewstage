package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.Criteria;

/**
 * 基金对私认购/申购交易 输入信息实体类
 * @author tongyapeng
上送SAP的报文头
参见APP上送SAP请求公共报文头

上送SAP报文体内容
编号	字段	字段长度	说明	是否必输	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	buytyp	X (01)	购买类型	是	1认购
2:申购
4	fnacno	X(15)	基金交易账号	否	电子渠道为选输项
5	fncode	X(06)	基金代码	是	
6	txnamt	9(14)V9(2)	买入金额	是	
7	autopt	9(07)	授权柜员	否	如果大于等于5万元，则填写授权柜员，电子渠道选输
8	enddat	X(04)	信用卡失效日期	否	信用卡时柜台必须上送，电渠选输。
9	rskcfm	X(1)	客户风险确认	否	N：客户未确认风险
Y：客户已确认风险
上送JSON报文
{"userid":"?","accrem":"?","buytyp":"?","fnacno":"?","fncode":"?","txnamt":"?","autopt":"?","enddat":"?","rskcfm":"?"}
 */
public class FundPrivateDealCriteria extends Criteria {
	public String userid;
	public String accrem;
	public String buytyp;
	public String fnacno;
	public String fncode;
	public String txnamt;
	public String autopt;
	public String enddat;
	public String rskcfm;
	
	
	public FundPrivateDealCriteria() {
		super();
	}

	public FundPrivateDealCriteria(String userid, String accrem, String buytyp,
			String fnacno, String fncode, String txnamt, String autopt,
			String enddat, String rskcfm) {
		super();
		this.userid = userid;
		this.accrem = accrem;
		this.buytyp = buytyp;
		this.fnacno = fnacno;
		this.fncode = fncode;
		this.txnamt = txnamt;
		this.autopt = autopt;
		this.enddat = enddat;
		this.rskcfm = rskcfm;
	}

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

	public String getBuytyp() {
		return buytyp;
	}

	public void setBuytyp(String buytyp) {
		this.buytyp = buytyp;
	}

	public String getFnacno() {
		return fnacno;
	}

	public void setFnacno(String fnacno) {
		this.fnacno = fnacno;
	}

	public String getFncode() {
		return fncode;
	}

	public void setFncode(String fncode) {
		this.fncode = fncode;
	}

	public String getTxnamt() {
		return txnamt;
	}

	public void setTxnamt(String txnamt) {
		this.txnamt = txnamt;
	}

	public String getAutopt() {
		return autopt;
	}

	public void setAutopt(String autopt) {
		this.autopt = autopt;
	}

	public String getEnddat() {
		return enddat;
	}

	public void setEnddat(String enddat) {
		this.enddat = enddat;
	}

	public String getRskcfm() {
		return rskcfm;
	}

	public void setRskcfm(String rskcfm) {
		this.rskcfm = rskcfm;
	}
}
