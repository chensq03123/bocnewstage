package com.boc.bocop.sdk.api.bean.useinfo;

import com.boc.bocop.sdk.api.bean.SearchCriteria;

/**
 * @author fww5205 用户信息查询输入信息实体类
 */
public class UserInfoCriteria extends SearchCriteria {

	private String accno = " ";// 账号
	private String alias = " ";// 别名
	private String trantype = " ";// 卡用途分类
	private String is_financial = " ";// 查询理财账户标识 0-不查询 1-查询
	private String pageno = " ";// 页码

	public String getAccno() {
		return accno;
	}

	/**
	 * 设置账号
	 * 
	 * @param accno
	 *            账号
	 */

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getAlias() {
		return alias;
	}

	/**
	 * 设置别名
	 * 
	 * @param alias
	 *            别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTrantype() {
		return trantype;
	}

	/**
	 * 设置卡用途分类
	 * 
	 * @param trantype
	 *            卡用途分类 选输 01-查询 02-支付
	 */
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}

	public String getIs_financial() {
		return is_financial;
	}

	/**
	 * 设置查询理财账户标志
	 * 
	 * @param is_financial
	 *            查询理财账户标志 必输 不查询 查询
	 */
	public void setIs_financial(String is_financial) {
		this.is_financial = is_financial;
	}

	public String getPageno() {
		return pageno;
	}

	/**
	 * 设置页码
	 * 
	 * @param pageno
	 *            多包返回时，初始值（首包）赋值为1。 若回复包中为000000表示无后续包
	 */
	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

}
