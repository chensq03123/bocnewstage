package com.boc.bocop.sdk.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 服务器返回信息处理类的基类
 * @author typ0520
 * 
 */
public class AsyncResponseHandler {
	protected static final int START_MESSAGE = 1;
	protected static final int FINISH_MESSAGE = 2;
	protected static final int EXCEPTION_MESSAGE = 3;
	protected static final int HANDLER_RESPONSE_MESSAGE = 4;

	protected Handler handler;

	public AsyncResponseHandler() {
		if (Looper.myLooper() != null) {
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					AsyncResponseHandler.this.handleMessage(msg);
				}
			};
		}
	}

	/**
	 * 当请求开始时调用
	 */
	public void onStart() {
		
	}

	/**
	 * 当请求结束时调用
	 */
	public void onFinish() {
		
	}

	/**
	 * 当请求成功时调用
	 * @param content   服务器返回的字符串
	 */
	public void onSuccess(String response) {
		
	}

	/**
	 * 当服务器返回错误时调用
	 * @param response 服务器返回的错误信息
	 */
	public void onError(String response) {

	}

	/**
	 * 当访问过程中出现异常时调用 例如:连接超时、解析出为null的responseBean
	 * @param desc
	 */
	public void onException(ExceptionDesc desc) {

	}
	protected void sendStartMessage() {
		sendMessage(START_MESSAGE, null);
	}
	protected void sendFinishMessage() {
		sendMessage(FINISH_MESSAGE, null);
	}
	protected void sendExceptionMessage(ExceptionDesc desc) {
		sendMessage(EXCEPTION_MESSAGE, desc);
	}
	
	private void sendMessage(int what, Object obj) {
		Message msg = Message.obtain();
		msg.what = what;
		msg.obj = obj;
		handler.sendMessage(msg);
	}

	protected void sendHandlerResponseMessage(String response) {
		sendMessage(HANDLER_RESPONSE_MESSAGE, response);
	}
	/**
	 * 处理服务器返回的信息
	 * @param response 服务器返回信息   null说明连接超时
	 */
	public final void handlerResponse(String response) {
		// 如果返回结果为空，直接报异常
		if (response == null || "".equals(response)) {
			// 连接超时
			onException(ExceptionDesc.obtainOvertimeDesc());
		} else {
			if (response.contains("msgcde") && response.contains("rtnmsg")) {
				// 服务器返回的公共的错误码
				dispatchErrorResponse(response);
			} else {
				dispatchSuccessResponse(response);
			}
		}
	}

	/**
	 * 分发成功信息，覆盖此方法可以扩展此类，子类应该调用父类中这个方法
	 * @param response
	 */
	protected void dispatchSuccessResponse(String response) {
		onSuccess(response);
	}
	/**
	 * 分发错误服务器信息信息
	 * 
	 * @param response
	 */
	protected void dispatchErrorResponse(String response) {
		onError(response);
	}

	protected void handleMessage(Message msg) {
		switch (msg.what) {
		case START_MESSAGE:
			onStart();
			break;
		case FINISH_MESSAGE:
			onFinish();
			break;
		case EXCEPTION_MESSAGE:
			onException((ExceptionDesc) msg.obj);
			break;
		case HANDLER_RESPONSE_MESSAGE:
			handlerResponse((String) msg.obj);
			break;
		}
	};
}
