/**
 * 
 */
package com.hustunique.bocp.Utils;

import java.io.Serializable;


/**
 * @author feiweiwei card balance bean
 */
public class BalanceCriteria implements Serializable {

	/**
	 * card No
	 */
	private String userid = " ";	//用户ID 
	private String lmtamt = " ";  //卡唯一标识 
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLmtamt() {
		return lmtamt;
	}

	public void setLmtamt(String lmtamt) {
		this.lmtamt = lmtamt;
	}

	@Override
	public String toString() {
		return "BalanceCriteria [userid=" + userid + ", lmtamt=" + lmtamt + "]";
	}
	
	
}
