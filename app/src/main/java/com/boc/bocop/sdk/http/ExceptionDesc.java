package com.boc.bocop.sdk.http;

import java.io.Serializable;

/**
 * 一异常描述类
 * @author tongyapeng
 */
public class ExceptionDesc implements Serializable {
	/**
	 * 常见的异常
	 */
	public static final int COMMON_EXCEPTION = 0;
	/**
	 * 把服务器回送数据解析成相应的javabean，这个javaBean为null
	 */
	public static final int JSON_PARSE_NULL_EXCEPTION = 1 << 0;
	/**
	 * 连接超时
	 */
	public static final int CONNECT_OVERTIME = 1 << 1;	
	
	private int type;
	private Exception exception;	
		
/*	public ExceptionDesc(int type) {
		this.type = type;
	}*/

	public ExceptionDesc(int type, Exception exception) {
		this.type = type;
		this.exception = exception;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Exception getError() {
		return exception;
	}
	public void setError(Exception error) {
		this.exception = error;
	}
	
	public static ExceptionDesc obtainOvertimeDesc() {
		Exception e = new Exception("Json数据解析出错");
		ExceptionDesc desc = new ExceptionDesc(CONNECT_OVERTIME,e);		
		return desc;
	}
	public static ExceptionDesc obtainJsonParseNullDesc() {
		Exception e = new Exception("Json数据解析出的Bean为null");
		ExceptionDesc desc = new ExceptionDesc(JSON_PARSE_NULL_EXCEPTION,e);		
		return desc;
	}
	public static ExceptionDesc obtainCommonDesc(Exception exception) {
		ExceptionDesc desc = new ExceptionDesc(COMMON_EXCEPTION,exception);		
		return desc;
	}
}
