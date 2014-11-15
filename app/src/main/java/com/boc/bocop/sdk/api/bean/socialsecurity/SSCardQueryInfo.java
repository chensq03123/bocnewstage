package com.boc.bocop.sdk.api.bean.socialsecurity;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


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
public class SSCardQueryInfo extends ResponseBean {
	@Expose
	private String usrename;
	@Expose
	private String credno;
	@Expose
	private String credtype;
	@Expose
	private String societyacc;
	@Expose
	private String medicineacc;
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
	public String getSocietyacc() {
		return societyacc;
	}
	public void setSocietyacc(String societyacc) {
		this.societyacc = societyacc;
	}
	public String getMedicineacc() {
		return medicineacc;
	}
	public void setMedicineacc(String medicineacc) {
		this.medicineacc = medicineacc;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
}
