package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.Criteria;
import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 基金对私认购/申购交易返回信息实体类
 * @author tongyapeng
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
public class FundPrivateRedeemResponse extends ResponseBean {
	public String userid;
	public String accrem;
	public String addflg;
	public String ispaper;
	public String addrpaper;
	public String zippaper;
	public String ismobile;
	public String mobile;
}
