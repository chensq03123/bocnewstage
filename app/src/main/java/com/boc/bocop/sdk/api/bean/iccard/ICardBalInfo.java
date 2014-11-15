package com.boc.bocop.sdk.api.bean.iccard;

import java.io.Serializable;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * ic卡电子现金余额查询返回数据实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class ICardBalInfo extends ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Expose
	private String accno = " ";
	@Expose
	private String bal1 = " ";
	@Expose
	private String bal2 = " ";
	@Expose
	private String bal3 = " ";
	@Expose
	private String bal4 = " ";
	@Expose
	private String bal5 = " ";
	@Expose
	private String bal6 = " ";

	/**
	 * @return ic卡号
	 */
	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * @return 电子现金账户余额   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getBal1() {
		return bal1;
	}

	public void setBal1(String bal1) {
		this.bal1 = bal1;
	}

	/**
	 * @return 电子现金余额最高上限   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getBal2() {
		return bal2;
	}

	public void setBal2(String bal2) {
		this.bal2 = bal2;
	}

	/**
	 * @return 自动圈存阈值等   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getBal3() {
		return bal3;
	}

	public void setBal3(String bal3) {
		this.bal3 = bal3;
	}

	/**
	 * @return 补登余额   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getBal4() {
		return bal4;
	}

	public void setBal4(String bal4) {
		this.bal4 = bal4;
	}

	/**
	 * @return 账户当前总金额   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getBal5() {
		return bal5;
	}

	public void setBal5(String bal5) {
		this.bal5 = bal5;
	}

	/**
	 * @return 最大可圈存金额   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getBal6() {
		return bal6;
	}

	public void setBal6(String bal6) {
		this.bal6 = bal6;
	}

}
