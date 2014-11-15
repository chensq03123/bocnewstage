package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.Criteria;

/**
 * 基金对私赎回 输入信息实体类
 * @author tongyapeng
 
SA0034基金对私赎回
上送SAP的URL
https://openapi.boc.cn/banking/fundprivateredeem

请求方式
POST

上送SAP的报文头
参见APP上送SAP请求公共报文头

上送SAP报文体内容
编号	字段	字段长度	说明	是否必输	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	fnacno	X(15)	基金交易账号	否	电子渠道为选输项
4	fncode	X(06)	基金代码	是	
5	txnquty	9(14)V9(2)	赎回份额	是	
6	cntflg	X(01)	连续赎回标志	否	0:不连续赎回
1:连续赎回
7	autopt	9(07)	授权柜员	否	如果交易申请份额大于5万份必输，电子渠道选输
上送JSON报文
{"userid":"?","accrem":"?","fnacno":"?","fncode":"?","txnquty":"?","cntflg":"?","autopt":"?"}
 */
public class FundPrivateRedeemCriteria extends Criteria {
	public String userid;
	public String accrem;
	public String fnacno;
	public String fncode;
	public String txnquty;
	public String cntflg;
	public String autopt;
	
	public FundPrivateRedeemCriteria() {
		super();
	}

	public FundPrivateRedeemCriteria(String userid, String accrem,
			String fnacno, String fncode, String txnquty, String cntflg,
			String autopt) {
		super();
		this.userid = userid;
		this.accrem = accrem;
		this.fnacno = fnacno;
		this.fncode = fncode;
		this.txnquty = txnquty;
		this.cntflg = cntflg;
		this.autopt = autopt;
	}
}
