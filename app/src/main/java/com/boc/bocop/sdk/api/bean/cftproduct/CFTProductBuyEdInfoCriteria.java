package com.boc.bocop.sdk.api.bean.cftproduct;

import com.boc.bocop.sdk.api.bean.SearchCriteria;

/**
 * 理财产品买入情况查询
 * 
 * @author alan
 * 
 */
public class CFTProductBuyEdInfoCriteria extends SearchCriteria {

	private String alias = " ";
	private String memocd = " ";
	private String procur = " ";
	private String sdate = " ";
	private String edate = " ";
	private String pageno = " ";

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

	public String getMemocd() {
		return memocd;
	}

	/**
	 * 设置产品名牌代码
	 * 
	 * @param memocd
	 *            产品名牌代码 0：全部
	 */
	public void setMemocd(String memocd) {
		this.memocd = memocd;
	}

	public String getProcur() {
		return procur;
	}

	/**
	 * 设置币种
	 * 
	 * @param procur
	 *            币种 000：全部 001：人民币元 014：美元 012：英镑 013：港币 028: 加拿大元 029：澳元
	 *            038：欧元 027：日元
	 */
	public void setProcur(String procur) {
		this.procur = procur;
	}

	public String getSdate() {
		return sdate;
	}

	/**
	 * 设置起始日期
	 * 
	 * @param sdate
	 *            起始日期 必输 日期格式：yyyymmdd
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	/**
	 * 设置结束日期
	 * 
	 * @param edate
	 *            结束日期 必输 日期格式：yyyymmdd
	 */
	public void setEdate(String edate) {
		this.edate = edate;
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
