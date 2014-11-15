package com.boc.bocop.sdk.api.bean.oauth;

import java.io.Serializable;

import com.boc.bocop.sdk.api.bean.ResponseBean;


/**
 * @author feiweiwei
 * 2013-08-13 获取pds随机数返回报文bean
 */
public class RandomResponse extends ResponseBean  implements Serializable{
	private String randomid;
	private String random;
	
	public String getRandomid() {
		return randomid;
	}
	public void setRandomid(String randomid) {
		this.randomid = randomid;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	
}
