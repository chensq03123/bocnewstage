package com.boc.bocop.sdk.api.bean.bill;

/**
 *  优惠商户--UE
 * @author huwei 20130823
 */
public class FavorableCriteria {
	private String branch_no;
	
	public FavorableCriteria() {
		super();
	}

	public FavorableCriteria(String branch_no) {
		super();
		this.branch_no = branch_no;
	}
	public String getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}
	
}
