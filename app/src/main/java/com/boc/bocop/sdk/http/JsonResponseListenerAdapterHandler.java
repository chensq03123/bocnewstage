package com.boc.bocop.sdk.http;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.ResponseError;

/**
 * 处理json数据 兼容老的回调接口
 * @author tongyapeng
 * @param <T> 访问成功时，与服务器返回数据像对应的bean
 */
public class JsonResponseListenerAdapterHandler<T> extends JsonAsyncResponseHandler<T> {
	private ResponseListener listener;

	/**
	 * 构造服务器返回数据的处理器
	 * @param responseBeanType 问成功时，与服务器返回数据像对应的bean的Class
	 * @param listener 老的数据回调接口
	 */
	public JsonResponseListenerAdapterHandler(Class<T> responseBeanType,ResponseListener listener) {
		super(responseBeanType);
		this.listener = listener;
	}
	
	public void onSuccess(T responseBean) {
		listener.onComplete((ResponseBean) responseBean);
	};
	@Override
	public void onError(ResponseError error) {
		listener.onError(error);
	}	
	@Override
	public void onException(ExceptionDesc desc) {
		listener.onException(desc.getError());
	}
}
