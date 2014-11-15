package com.boc.bocop.sdk.util;

import java.text.DecimalFormat;



public class UtilTool {	
	public static boolean isNull(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	/**
	 * 去掉负号，截取负号后面的String
	 * @param str
	 * @return
	 */
	public static String removeSign(String str){
		if(str.contains("-")){
			return str.substring(str.indexOf("-")+1);
		}else{
			return str;
		}
	}
	/**
	 * 
	 * @param number
	 * @return double类型转换成String,保留两位小数点
	 */
	public static String formatdot(Double number) {
		if (number != null) {
			DecimalFormat df = new DecimalFormat("#0.00");
			return df.format(number);
		}
		return "";
	}
	public static double conversePoint(String number){
		boolean hasSign = false;
		if (isNotEmpty(number)) {
			if(number.contains("-")){
				hasSign = true;
				number = number.replace("-", "");
			}
			if (number.length() == 0) {
				return 0.00;
			}
			if (Double.parseDouble(number) == 0.0) {//返回数据为0，直接返回0.00
				return 0.00;
			} else {
				String targetNumber;
				if (number.length() <= 2) {
					if (number.length() == 1) {
						targetNumber = "0.0" + number;
					} else if (number.length() == 2) {
						targetNumber = "0." + number;
					} else {
						return 0.00;
					}
				} else {
					String pointNumber=number.substring(number.length()-2, number.length());
					String beforNumber=number.substring(0, number.length()-2);
					targetNumber=beforNumber+"."+pointNumber;
				}
				return Double.parseDouble(hasSign ? "-"+targetNumber : targetNumber);
			}
		}
		return 0.00;
		
	}
	public static boolean isNotEmpty(String s)
	{
		return s != null && !"".equals(s.trim());
	}
}
