/**
 * 
 */
package com.boc.bocop.sdk.api.bean.bill;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * @author feiweiwei
 * Balance response Message bean
 */
public class BalanceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * card CNY balance
	 */
	@Expose
	private String balance;//余额
	@Expose
	private String xfjf;
	@Expose
	private String ztxe;//整体限额
	@Expose
	private String ztkyed;//整体可用额度
	@Expose
	private String qxxe;
	@Expose
	private String kyqxed;
	@Expose
	private String fqed;
	@Expose
	private String fqkye;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public String getXfjf() {
		return xfjf;
	}

	public void setXfjf(String xfjf) {
		this.xfjf = xfjf;
	}

	public String getZtxe() {
		return ztxe;
	}

	public void setZtxe(String ztxe) {
		this.ztxe = ztxe;
	}

	public String getZtkyed() {
		return ztkyed;
	}

	public void setZtkyed(String ztkyed) {
		this.ztkyed = ztkyed;
	}

	public String getQxxe() {
		return qxxe;
	}

	public void setQxxe(String qxxe) {
		this.qxxe = qxxe;
	}

	public String getKyqxed() {
		return kyqxed;
	}

	public void setKyqxed(String kyqxed) {
		this.kyqxed = kyqxed;
	}

	public String getFqed() {
		return fqed;
	}

	public void setFqed(String fqed) {
		this.fqed = fqed;
	}

	public String getFqkye() {
		return fqkye;
	}

	public void setFqkye(String fqkye) {
		this.fqkye = fqkye;
	}

	
	
	
}
