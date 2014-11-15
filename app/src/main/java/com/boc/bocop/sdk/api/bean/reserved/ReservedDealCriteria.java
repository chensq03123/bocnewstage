package com.boc.bocop.sdk.api.bean.reserved;

import com.boc.bocop.sdk.api.bean.Criteria;
import com.boc.bocop.sdk.api.bean.medicaltreatment.MTCardDealQueryCriteria;

/**
 * 公积金账户交易明细查询(广东)输入信息实体类
 * @author tongyapeng
SA0031公积金账户交易明细查询-广东
上送SAP的URL
https://openapi.boc.cn/banking/reserveddealquery

请求方式
POST

上送SAP的报文头
参见APP上送SAP请求公共报文头

上送SAP报文体内容
编号	字段	字段长度	说明	是否必输	备注
1	 userid	 X(50)	 用户ID	 是	
2	 a_route	 X(5)	 省行联行号	 是	 值为新线省行号
3	 accrem	 X(16)	 卡唯一标识	 是	
4	 agent_code	 X(10)	 代理单位号	 是	 参见广东代理单位号表
5	 sdate	 X(8)	 起始日期	 是	
6	 edate	 X(8)	 结束日期	 是	
7	 index	 X(3)	 续查序号	 是	
8	 citycode	 X(20)	 城市区号	 是	 必输：如:20 - 广州
0756 – 珠海
上送JSON报文
{"userid":"?","a_route":"?","accrem":"?","agent_code":"?","sdate":"?","edate":"?","index":"?","citycode":"?"}
 */
public class ReservedDealCriteria extends Criteria {
	//public String userid;		
	public String a_route;
	public String accrem;
	public String agent_code;
	public String sdate;
	public String edate;
	public String index;
	public String citycode;
}
