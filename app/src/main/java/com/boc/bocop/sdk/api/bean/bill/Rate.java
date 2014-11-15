package com.boc.bocop.sdk.api.bean.bill;

import java.io.Serializable;

public class Rate implements Serializable {
	
	private String crrncy;
	
	public Rate() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Rate(String crrncy) {
		super();
		this.crrncy = crrncy;
	}



	public String getCrrncy() {
		return crrncy;
	}



	public void setCrrncy(String crrncy) {
		this.crrncy = crrncy;
	}



	@Override
	public String toString() {
		return "Rate [crrncy=" + crrncy + "]";
	}


}
