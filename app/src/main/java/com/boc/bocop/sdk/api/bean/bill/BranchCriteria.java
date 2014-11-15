package com.boc.bocop.sdk.api.bean.bill;

import java.io.Serializable;


/**
 * 我的附近模块 获取网点信息请求参数封装bean 上送SAP的URL
 * http://openapi.boc.cn/bocop/base/unlogin/querycardindcusinfo 请求方式 POST
 * 上送json报文 {"longitude":"?","latitude":"?","type":"?","range":"?"}
 * @author typ
 */
public class BranchCriteria implements Serializable {
	private String longitude;// 经度 9(3)v9(6)S
								// 数字型，总长度10位，小数点前3位，后6位，小数点不占位，符号在最后一位；范围为[-180，180]
	private String latitude;// 纬度 9(3)v9(6)S
							// 数字型，总长度10位，小数点前3位，后6位，小数点不占位，符号在最后一位；范围为[-90，90]
	private String type;// 地点类型 X（1） 0:all 1：ATM，2：网点，3：商户
	private String range;// 范围半径 9(5) 是 数字型，总长度5，最大为9999米
//	private String keyword;

	public BranchCriteria() {
		
	}

//	public BranchCriteria(String longitude, String latitude, String range,
//			String type, String keyword) {
//		this.longitude = longitude;//BranchService.loc_s2l(longitude);
//		this.latitude = latitude;//BranchService.loc_s2l(latitude);
//		this.type = type;
//		this.range = range;
//		this.keyword = keyword;
//	}
	public BranchCriteria(String longitude, String latitude, String range,
			String type) {
		this.longitude = longitude;//BranchService.loc_s2l(longitude);
		this.latitude = latitude;//BranchService.loc_s2l(latitude);
		this.type = type;
		this.range = range;
	}
	public BranchCriteria(double longitude, double latitude, int range,
			int type) {
		this(longitude+"",latitude+"",range+"",type+"");
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	

//	public String getKeyword() {
//		return keyword;
//	}
//
//	public void setKeyword(String keyword) {
//		this.keyword = keyword;
//	}

	@Override
	public String toString() {
		return "BranchCriteria [longitude=" + longitude + ", latitude="
				+ latitude + ", type=" + type + ", range=" + range+ "]";
	}
}
