package com.boc.bocop.sdk.api.bean.accfund;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 公积金账户缴存信息查询返回数据实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class AccFundDepositInfo extends ResponseBean {

	private int rcdcnt = 0;
	private List<DepositInfo> depositInfos;

	/**
	 * @return 笔数
	 */
	public int getRcdcnt() {
		return rcdcnt;
	}

	public void setRcdcnt(int rcdcnt) {
		this.rcdcnt = rcdcnt;
	}

	/**
	 * @return 当笔数大于O时，返回 多笔循环内容
	 */
	public List<DepositInfo> getDepositInfos() {
		return depositInfos;
	}

	public void setDepositInfos(List<DepositInfo> depositInfos) {
		this.depositInfos = depositInfos;
	}

}
