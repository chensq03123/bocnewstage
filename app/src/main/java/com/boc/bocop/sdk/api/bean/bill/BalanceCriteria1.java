/**
 * 
 */
package com.boc.bocop.sdk.api.bean.bill;

import java.io.Serializable;


/**
 * @author feiweiwei card balance bean
 */
public class BalanceCriteria1 implements Serializable {

	/**
	 * card No
	 */
	private String userid = " ";	//用户ID 
	private String accrem = " ";  //卡唯一标识 
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getAccrem() {
		return accrem;
	}

	public void setAccrem(String accrem) {
		this.accrem = accrem;
	}

	@Override
	public String toString() {
		return "BalanceCriteria [userid=" + userid + ", accrem=" + accrem + "]";
	}
	
	
}
