package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.Criteria;

/**
 * 基金对私开户 输入信息实体类
 * @author tongyapeng
 https://openapi.boc.cn/banking/fundprivateopen

请求方式
POST

上送SAP的报文头
参见APP上送SAP请求公共报文头

上送SAP报文体内容
序号	字段名	类型长度	说明	上送字段	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	addflg	X(02)	地址编号	是	01：家庭地址;
02：单位地址;
4	etokenval	X(12)	动态密码	是	
5	chkcode	X(20)	短信验证码	否	
上送JSON报文
{"userid":"?","accrem":"?","addflg":"?","etokenval":"?","chkcode":"?"}
 */
public class FundPrivateOpenCriteria extends Criteria {
	public String userid;
	public String accrem;
	public String addflg;
	public String etokenval;	
	public String chkcode;	
}
