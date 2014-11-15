package com.boc.bocop.sdk.util;


public final class StringUtil {
	
	public static boolean isNullOrEmpty(String str) {
		if ((str == null) || str.equals("") || str.equals("null")
				|| str.equals("NULL")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String null2Blank(String str){
		return (str==null?"":str);
	}
}
