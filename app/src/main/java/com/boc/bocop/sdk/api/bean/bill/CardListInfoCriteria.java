package com.boc.bocop.sdk.api.bean.bill;
/**
 * 查询用户卡资料
 * @author CindyLiu
 *
 */
public class CardListInfoCriteria {
	private String userid = " ";  //用户ID  X(50)  是  
	private String accno = " ";//   账户  X(22)  否   
	private String alias = " ";//   别名  X(50)  否   
	private String trntyp = " ";//   卡用途分类  X(2)  是  01-查询 02-支付  
	private String ifncal = " ";//   查询理财账户标志  X(1)  否  0-不查询 1-查询  
	private String pageno = " ";//   页码  9(6)  是  多包返回时，初始值（首包）赋值为1	若回复包中为000000表示无后续包  
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTrntyp() {
		return trntyp;
	}
	public void setTrntyp(String trntyp) {
		this.trntyp = trntyp;
	}
	public String getIfncal() {
		return ifncal;
	}
	public void setIfncal(String ifncal) {
		this.ifncal = ifncal;
	}
	public String getPageno() {
		return pageno;
	}
	public void setPageno(String pageno) {
		this.pageno = pageno;
	}
}
