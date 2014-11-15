package com.boc.bocop.sdk.api.bean.iccard;

import com.boc.bocop.sdk.api.bean.SearchCriteria;

/**
 * ic卡查询输入信息实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class ICardCriteria extends SearchCriteria {

	public String alias = " ";
	public String stdate = " ";
	public String endate = " ";
	public String pagnum = " ";
	public String recdno = " ";

	/**
	 * @param alias
	 *            别名 当进行ic卡电子现金余额查询时，只需要设置 别名
	 */
	public ICardCriteria(String alias) {
		super();
		this.alias = alias;
	}

	/**
	 * @param alias
	 *            别名 当进行ic卡电子现金余额查询时，只需要设置 别名
	 * @param stdate
	 *            查询开始日期 日期格式：yyyymmdd
	 * @param endate
	 *            查询结束日期 日期格式：yyyymmdd
	 * @param pagnum
	 *            查询页数 001002003…终端进行累加,返回时以000作为结束标识
	 * @param recdno
	 *            每页记录数 前端每页需要展示的记录数
	 */
	public ICardCriteria(String alias, String stdate, String endate,
			String pagnum, String recdno) {
		super();
		this.alias = alias;
		this.stdate = stdate;
		this.endate = endate;
		this.pagnum = pagnum;
		this.recdno = recdno;
	}

	public String getAlias() {
		return alias;
	}

	/**
	 * 设置 别名
	 * 
	 * @param alias
	 *            别名 当进行ic卡电子现金余额查询时，只需要设置 别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStdate() {
		return stdate;
	}

	/**
	 * 设置 查询开始日期
	 * 
	 * @param stdate
	 *            查询开始日期 日期格式：yyyymmdd
	 */
	public void setStdate(String stdate) {
		this.stdate = stdate;
	}

	public String getEndate() {
		return endate;
	}

	/**
	 * 设置 查询结束日期
	 * 
	 * @param endate
	 *            查询结束日期 日期格式：yyyymmdd
	 */
	public void setEndate(String endate) {
		this.endate = endate;
	}

	public String getPagenum() {
		return pagnum;
	}

	/**
	 * 设置 查询页数
	 * 
	 * @param pagenum
	 *            查询页数 001002003…终端进行累加,返回时以000作为结束标识
	 */
	public void setPagenum(String pagenum) {
		this.pagnum = pagenum;
	}

	public String getRecdno() {
		return recdno;
	}

	/**
	 * 设置 每页记录数
	 * 
	 * @param recdno
	 *            每页记录数 前端每页需要展示的记录数
	 */
	public void setRecdno(String recdno) {
		this.recdno = recdno;
	}

}
