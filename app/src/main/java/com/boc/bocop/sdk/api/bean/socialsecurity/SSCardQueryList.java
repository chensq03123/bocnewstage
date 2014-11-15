package com.boc.bocop.sdk.api.bean.socialsecurity;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;


/**
 * 社保卡资料查询返回数据实体类
 * @author tongyapeng
1	usrename	X(50)	客户姓名	否	
2	credno	X(20)	证件号码	否	
3	credtype	X(2)	证件类型	否	
4	societyacc	X(20)	社保卡号	否	
5	medicineacc	X(30)	医保卡号	否	
6	balance	X(12)	医保卡余额	否	
 */
public class SSCardQueryList extends ResponseBean {
	private String record_count;
	private List<SSCardQueryInfo> saplist;
	public String getRecord_count() {
		return record_count;
	}
	public void setRecord_count(String record_count) {
		this.record_count = record_count;
	}
	public List<SSCardQueryInfo> getSaplist() {
		return saplist;
	}
	public void setSaplist(List<SSCardQueryInfo> saplist) {
		this.saplist = saplist;
	}
}
