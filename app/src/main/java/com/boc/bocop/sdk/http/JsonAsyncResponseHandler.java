package com.boc.bocop.sdk.http;

/**
 * 处理json数据
 * @author tongyapeng
 * @param <T>
 */
public class JsonAsyncResponseHandler<T> extends SerializableAsyncResponseHandler<T> {

	public JsonAsyncResponseHandler(Class<T> responseBeanType) {
		super(responseBeanType);
	}

	@Override
	public <T> T responseToBean(String response, Class<T> clazz) {		
		return BeanUtils.jsonToObject(response, clazz);
	}	
}
