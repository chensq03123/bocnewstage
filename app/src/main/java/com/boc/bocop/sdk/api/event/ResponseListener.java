package com.boc.bocop.sdk.api.event;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 监听器接口，用户需要在APP应用中重新实现该接口
 * 
 * @author CindyLiu
 * @version V1.0, 2013-1-10
 */
public interface ResponseListener {
	/**
	 * 调用成功时返回，用户需要根据具体的发送请求，得到不同的返回实体类
	 * 
	 * @param response
	 *            返回的实体类，用户需要根据具体的发送请求，得到不同的返回实体类。
	 */
   	public void onComplete(ResponseBean response);
   	
   	/**
	 * 出现异常时返回，用户需要得到BOCOPException的statusCode，进行具体处理。
	 * 
	 * @param e
	 *            BOCOPException
	 * @see BOCOPException
	 */
   	public void onException(Exception e);
   	
   	/**
	 * 服务器返回错误 时返回，用户需要得到ResponseError，进行具体处理
	 * 
	 * @param 
	 *            ResponseError
	 * @see ResponseError
	 */
   	public void onError(Error e);
   	
   	/**
	 * 请求取消时调用，用户可根据具体情况实现该接口
	 * 
	 */
   	public void onCancel();
}
