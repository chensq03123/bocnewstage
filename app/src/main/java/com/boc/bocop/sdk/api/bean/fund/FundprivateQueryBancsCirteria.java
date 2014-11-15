package com.boc.bocop.sdk.api.bean.fund;

import android.location.Criteria;

/**
 * 基金对私查询Bancs客户信息 输入信息实体类
 * @author tongyapeng
	上送SAP报文体内容
	编号	字段	字段长度	说明	是否必输	备注
	1	userid	X(50)	用户ID	是	
	2	accrem	X(16)	卡唯一标识	是	
	3	addflg	X(02)	地址编号	是	01：家庭地址;
	02：单位地址;
	上送JSON报文
	{"userid":"?","accrem":"?","addflg":"?"}
 */
public class FundprivateQueryBancsCirteria extends Criteria {
	public String userid;
	public String accrem;
	public String addflg;
	
	
	
	public FundprivateQueryBancsCirteria() {
		super();
	}



	public FundprivateQueryBancsCirteria(String userid, String accrem,
			String addflg) {
		super();
		this.userid = userid;
		this.accrem = accrem;
		this.addflg = addflg;
	}
	
}
