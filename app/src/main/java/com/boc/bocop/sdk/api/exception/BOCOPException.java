
package com.boc.bocop.sdk.api.exception;


/**
 * 中银开放平台支付Android SDK 异常处理类
 * 
 * @author CindyLiu
 * @version V1.0, 2013-1-10
 */
public class BOCOPException extends Exception {

	private static final long serialVersionUID = 475022994858770424L;
	private int statusCode = -1;
	
    public BOCOPException(String msg) {
        super(msg);
    }

    public BOCOPException(Exception cause) {
        super(cause);
    }

    public BOCOPException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public BOCOPException(String msg, Exception cause) {
        super(msg, cause);
    }

    public BOCOPException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;
    }
    
	public BOCOPException() {
		super(); 
	}

	public BOCOPException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public BOCOPException(Throwable throwable) {
		super(throwable);
	}

	public BOCOPException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * @return 异常状态码，用户可以通过ResponseListener的onException，取得异常状态码
	 */
	public int getStatusCode() {
	        return this.statusCode;
    }
	
	/**
	 * @return 异常状态码，用户可以通过ResponseListener的onException，取得异常状态码
	 */
	public String getStatusMsg() {
	        return super.getMessage();
    }
}
