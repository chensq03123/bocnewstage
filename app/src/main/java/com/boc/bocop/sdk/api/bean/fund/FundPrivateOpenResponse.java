package com.boc.bocop.sdk.api.bean.fund;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 基金对私开户查询返回信息实体类
 * @author tongyapeng
返回报文内容
编号	字段	字段长度	说明	是否必输	备注
1	userid	X(50)	用户ID	是	
2	accrem	X(16)	卡唯一标识	是	
3	custid	X(17)	客户号	否	
4	fnacno	X(15)	基金交易账号	否	
5	cardno	X(19)	卡号	否	
6	name	X(80)	姓名	否	
7	sex	X(01)	性别	否	0：女
1：男
8	nation	X(02)	国籍	否	和BANCS保持一致
9	idtyp	X(02)	证件类型	否	和BANCS保持一致
10	idnum	X(32)	证件号码	否	
11	telnum	X(22)	电话号码	否	
12	mobnum	X(20)	手机号码	否	
13	fax	X(22)	传真号	否	
14	email	X(40)	电子邮箱	否	
15	addr	X(120)	通讯地址	否	
16	zipcde	X(8)	邮编	否	
17	addflg	9(02)	地址编号	否	01：家庭地址;
02：单位地址;
18	ftxseq	X(12)	基金交易流水号	否	
19	ispaper	X(01)	是否寄送对账单	否	Y：是;
N：否
20	addrpaper	X(120)	寄送对账单地址	否	
21	zippaper	X(6)	寄送对账单地址邮政编码	否	
22	ismobile	X(01)	是否开通短信服务	否	Y：是;
N：否
23	mobile	X(20)	短信服务手机号码	否	
返回JSON报文
{"userid":"?","accrem":"?","custid":"?","fnacno":"?","cardno":"?","name":"?","sex":"?","nation":"?","idtyp":"?","idnum":"?",
"telnum":"?","mobnum":"?","fax":"?","email":"?","addr":"?","zipcde":"?","addflg":"?","ftxseq":"?","ispaper":"?","addrpaper":"?","zippaper":"?"
,"ismobile":"?","mobile":"?"}
 */
public class FundPrivateOpenResponse extends ResponseBean {
	public String userid;
	public String accrem;
	public String custid;
	public String fnacno;
	public String cardno;
	public String name;
	public String sex;
	public String nation;
	public String idtyp;
	public String idnum;
	public String telnum;
	public String mobnum;
	public String fax;
	public String email;
	public String addr;
	public String zipcde;
	public String addflg;
	public String ftxseq;
	public String ispaper;
	public String addrpaper;
	public String zippaper;
	public String ismobile;
	public String mobile;
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
	public String getFnacno() {
		return fnacno;
	}
	public void setFnacno(String fnacno) {
		this.fnacno = fnacno;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
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
	public String getAddflg() {
		return addflg;
	}
	public void setAddflg(String addflg) {
		this.addflg = addflg;
	}
	public String getFtxseq() {
		return ftxseq;
	}
	public void setFtxseq(String ftxseq) {
		this.ftxseq = ftxseq;
	}
	public String getIspaper() {
		return ispaper;
	}
	public void setIspaper(String ispaper) {
		this.ispaper = ispaper;
	}
	public String getAddrpaper() {
		return addrpaper;
	}
	public void setAddrpaper(String addrpaper) {
		this.addrpaper = addrpaper;
	}
	public String getZippaper() {
		return zippaper;
	}
	public void setZippaper(String zippaper) {
		this.zippaper = zippaper;
	}
	public String getIsmobile() {
		return ismobile;
	}
	public void setIsmobile(String ismobile) {
		this.ismobile = ismobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "FundPrivateOpenResponse [userid=" + userid + ", accrem="
				+ accrem + ", custid=" + custid + ", fnacno=" + fnacno
				+ ", cardno=" + cardno + ", name=" + name + ", sex=" + sex
				+ ", nation=" + nation + ", idtyp=" + idtyp + ", idnum="
				+ idnum + ", telnum=" + telnum + ", mobnum=" + mobnum
				+ ", fax=" + fax + ", email=" + email + ", addr=" + addr
				+ ", zipcde=" + zipcde + ", addflg=" + addflg + ", ftxseq="
				+ ftxseq + ", ispaper=" + ispaper + ", addrpaper=" + addrpaper
				+ ", zippaper=" + zippaper + ", ismobile=" + ismobile
				+ ", mobile=" + mobile + "]";
	}
}
