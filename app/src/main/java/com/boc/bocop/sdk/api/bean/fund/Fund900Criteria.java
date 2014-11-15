package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.Criteria;
/**
 * 基金挂单9000 输入信息实体类
 * @author typ0520
上送SAP的报文头
参见APP上送SAP请求公共报文头

上送SAP报文体内容
编号	字段	字段长度	说明	是否必输	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	txntyp	X(03)	交易类型。
基金转换各渠道上送036；
后台分036（同机构转换）、
038（跨机构转换）。	是	认购申请：020
申购申请：022
赎回申请：024
基金转换申请：036
修改分红方式申请：029
4	fnacno	X(15)	基金交易账号	否	
5	fncode	X(06)	基金代码	是	基金转换时填转出基金代码
6	fncodei	X(06)	转入基金代码	否	基金转换时填转入基金代码其余交易
7	txnamt	9(14)V9(2)	分红方式选择交易选输，其他情况必输（认申购填金额，赎回/转换时填份额）	否	认申购填金额，赎回/转换时填份额
8	cntflg	X(01)	连续赎回标志/顺延转换标志	是	赎回/转换
0:不连续赎回/非顺延
1:连续赎回/顺延
9	bnsmod	X(01)	分红方式	是	分红方式选择
1：现金
2：再投资
10	rskcfm	X(1)	认申购风险不匹配，客户二次确认时必输，其余情况选输	否	N：客户未确认风险
Y：客户已确认风险
11	autopt	9(07)	授权柜员	否	柜台必输，电子渠道
上送JSON报文
 {"userid":"?","accrem":"?","txntyp":"?","fnacno":"?","fncode":"?","fncodei":"?","txnamt":"?","cntflg":"?","bnsmod":"?","rskcfm":"?","autopt":"?"}
 */
public class Fund900Criteria extends Criteria {
	public String userid;
	public String accrem;
	public String txntyp;
	public String fnacno;
	public String fncode;
	public String fncodei;
	public String txnamt;
	public String cntflg;
	public String bnsmod;
	public String rskcfm;
	public String autopt;
}
