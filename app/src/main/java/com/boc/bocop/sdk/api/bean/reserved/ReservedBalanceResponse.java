package com.boc.bocop.sdk.api.bean.reserved;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * 公积金帐号余额查询返回数据实体类
 * @author tongyapeng
 返回报文内容
编号	字段	字段长度	说明	是否必输	备注
1	usrename	X(50)	客户姓名		
2	credno	X(20)	证件号码		
3	credtype	X(2)	证件类型		
4	usercode	X(20)	公积金账号		
5	balance	X(30)	公积金余额		
返回JSON报文
{"usrename":"?","credno":"?","credtype":"?","usercode":"?","balance":"?"}
 */
public class ReservedBalanceResponse extends ResponseBean {
	@Expose
	private String usrename;
	@Expose
	private String credno;
	@Expose
	private String credtype;
	@Expose
	private String usercode;
	@Expose
	private String balance;
	
	public String getUsrename() {
		return usrename;
	}
	public void setUsrename(String usrename) {
		this.usrename = usrename;
	}
	public String getCredno() {
		return credno;
	}
	public void setCredno(String credno) {
		this.credno = credno;
	}
	public String getCredtype() {
		return credtype;
	}
	public void setCredtype(String credtype) {
		this.credtype = credtype;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
