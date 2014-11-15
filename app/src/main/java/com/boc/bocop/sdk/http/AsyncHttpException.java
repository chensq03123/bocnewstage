package com.boc.bocop.sdk.http;

/**
 * 异步http客户端 框架异常
 * @author tongyapeng
 *
 */
public class AsyncHttpException extends RuntimeException {

	public AsyncHttpException() {
		super();
	}

	public AsyncHttpException(String detailMessage) {
		super(detailMessage);
	}
	
}
