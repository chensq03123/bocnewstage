package com.boc.bocop.sdk.api.bean.useinfo;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * @author fww5205 用户资料实体类
 */
public class UserInfo extends ResponseBean {
	@Expose
	private String userid = " ";
	@Expose
	private String accno = " ";
	@Expose
	private String alias = " ";
	@Expose
	private String trntyp = " ";
	@Expose
	private String lmtamt = " ";
	@Expose
	private String time = " ";
	@Expose
	private String ifncal = " ";
	@Expose
	private String probank = " ";
		
	/**
	 * @return 用户id
	 */
	public String getUsrid() {
		return userid;
	}

	public void setUsrid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return 账号 账号的内容为1232xxxx1234
	 */
	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * @return 别名
	 */
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return 卡用途分类
	 */
	public String getTrantype() {
		return trntyp;
	}

	public void setTrantype(String trntyp) {
		this.trntyp = trntyp;
	}

	/**
	 * @return 单笔交易限额
	 */
	public String getLimitamt() {
		return lmtamt;
	}

	public void setLimitamt(String lmtamt) {
		this.lmtamt = lmtamt;
	}

	/**
	 * @return 日期 日期格式：yyyymmddhhmmss
	 */
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return 是否登记理财账户 0-未登记 1-已登记 2-未明；“空格”-没有去后台查询。
	 */
	public String getIs_financial() {
		return ifncal;
	}

	public void setIs_financial(String ifncal) {
		this.ifncal = ifncal;
	}

	/**
	 * @return 卡所属省分行简称
	 */
	public String getProbank() {
		return probank;
	}

	public void setProbank(String probank) {
		this.probank = probank;
	}

}
