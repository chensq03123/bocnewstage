package com.boc.bocop.sdk.http;

/**
 * 处理xml格式的数据
 * @author tongyapeng
 *
 * @param <T>
 */
public class XmlAsyncResponseHandler<T> extends SerializableAsyncResponseHandler<T> {

	public XmlAsyncResponseHandler(Class<T> responseBeanType) {
		super(responseBeanType);
	}

	@Override
	public <T> T responseToBean(String response, Class<T> clazz) {
		return BeanUtils.xmlToObject(response, clazz);
	}
}
