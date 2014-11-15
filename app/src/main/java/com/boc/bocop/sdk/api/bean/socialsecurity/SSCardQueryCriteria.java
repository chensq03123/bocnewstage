package com.boc.bocop.sdk.api.bean.socialsecurity;

import com.boc.bocop.sdk.api.bean.Criteria;


/**
 * SA0028社保卡资料查询-广东  输入信息实体类
 * @author tongyapeng
 *上送SAP的报文头
参见APP上送SAP请求公共报文头
上送SAP报文体内容
编号	字段	字段长度	说明	是否必输	备注
1	 userid	 X(50)	 用户id	 是	
2	 a_route	 X(5)	 省行机构号	 是	 值为新线省行号
3	 accrem	 X(16)	 卡唯一标识	 是	
4	 agent_code	 X(10)	 代理单位号	 是	 参见广东代理单位号表
5	 citycode	 X(10)	 城市区号	 是	 如:20 - 广州
0756 – 珠海
上送JSON报文
{"userid":"?","a_route":"?","accrem":"?","agent_code":"?","citycode":"?"}
 */
public class SSCardQueryCriteria extends Criteria {
	/**
	 * 省行机构号 (值为新线省行号)
	 */
	public String a_route;
	/**
	 * 卡唯一标识
	 */
	public String accrem;
	/**
	 * 代理单位号 (参见广东代理单位号表)
	 */
	public String agent_code;
	
	public String citycode;
	
	
	public SSCardQueryCriteria() {
		super();
	}

	public SSCardQueryCriteria(String a_route, String accrem,
			String agent_code,String citycode) {
		this.a_route = a_route;
		this.accrem = accrem;
		this.agent_code = agent_code;
		this.citycode = citycode;
	}	
}
