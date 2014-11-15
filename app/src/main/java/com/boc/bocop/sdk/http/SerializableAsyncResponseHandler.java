package com.boc.bocop.sdk.http;

import com.boc.bocop.sdk.api.exception.ResponseError;


/**
 * 把服务器返回的数据序列化为  指定类型的JavaBean 具体的序列化逻辑有子类的实现
 * @author tongyapeng
 * @param <T> 目标JavaBean的类型
 */
public abstract class SerializableAsyncResponseHandler<T> extends AsyncResponseHandler {
	protected Class<T> responseBeanType;
	
	public SerializableAsyncResponseHandler(Class<T> responseBeanType) {
		if (responseBeanType == null) {
			throw new IllegalArgumentException("The responseBeanType can not be null");
		}
		this.responseBeanType = responseBeanType;
	}	
	
	public abstract  <T>  T  responseToBean(String content,Class<T> clazz);
	
	@Override
	protected final void dispatchSuccessResponse(String content) {
		super.dispatchSuccessResponse(content);
		try {
			T responseBean = responseToBean(content, responseBeanType);
			if (responseBean != null) {
				onSuccess(responseBean);			
			} else {
				onException(ExceptionDesc.obtainJsonParseNullDesc());
			}
		} catch (Exception e) {
			onException(ExceptionDesc.obtainCommonDesc(e));
		}
	}
	
	@Override
	protected void dispatchErrorResponse(String content) {
		super.dispatchErrorResponse(content);
		try {
			ResponseError error = responseToBean(content, ResponseError.class);
			if (error != null) {
				onError(error);			
			} else {
				onException(ExceptionDesc.obtainJsonParseNullDesc());
			}
		} catch (Exception e) {
			onException(ExceptionDesc.obtainCommonDesc(e));
		}
	}	
	/**
	 * 服务器返回错误时调用
	 * @param error 代表错误信息的bean
	 */
	public void onError(ResponseError error) {
		
	}
	/**
	 * 成功时的回调函数
	 * @param responseBean 序列化完毕的JavaBean
	 */
	public void onSuccess(T responseBean) {		
		
	}	
}
