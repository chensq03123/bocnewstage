package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.ResponseBean;


/**
 * 基金对私查询Bancs客户信息 服务器返回信息实体类
 * @author tongyapeng
	返回报文内容
	编号	字段	字段长度	说明	是否必输	备注
	1	userid	X(50)	用户ID	是	
	2	accrem	X(16)	卡唯一标识	是	
	3	custid	X(17)	客户号	否	
	4	cardno	X(19)	卡号	否	
	5	name	X(80)	姓名	否	
	6	sex	X(01)	性别	否	0：女
	1：男
	7	nation	X(02)	国籍	否	和BANCS保持一致
	8	idtyp	X(02)	证件类型	否	和BANCS保持一致
	9	idnum	X(32)	证件号码	否	
	10	telnum	X(22)	电话号码	否	
	11	mobnum	X(20)	手机号码	否	
	12	fax	X(22)	传真号	否	
	13	email	X(40)	电子邮箱	否	
	14	addr	X(120)	通讯地址	否	
	15	zipcde	X(8)	邮编	否	
	返回JSON报文
     {"userid":"?","accrem":"?","custid":"?","cardno":"?","name":"?","sex":"?","nation":"?","idtyp":"?","idnum":"?",
"telnum":"?","mobnum":"?","fax":"?","email":"?","addr":"?","zipcde":"?"}
 */
public class FundprivateQueryBancsResponse extends ResponseBean {
	public String userid;
	public String accrem;
	public String custid;
	public String cardno;
	public String name;
	public int sex;
	public String nation;
	public String idtyp;
	public String idnum;
	public String telnum;
	public String mobnum;
	public String fax;
	public String email;
	public String addr;
	public String zipcde;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccrem() {
		return accrem;
	}
	public void setAccrem(String accrem) {
		this.accrem = accrem;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIdtyp() {
		return idtyp;
	}
	public void setIdtyp(String idtyp) {
		this.idtyp = idtyp;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getMobnum() {
		return mobnum;
	}
	public void setMobnum(String mobnum) {
		this.mobnum = mobnum;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZipcde() {
		return zipcde;
	}
	public void setZipcde(String zipcde) {
		this.zipcde = zipcde;
	}
	
	
	
}
