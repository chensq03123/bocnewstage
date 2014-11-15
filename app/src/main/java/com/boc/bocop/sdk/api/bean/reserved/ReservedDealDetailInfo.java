package com.boc.bocop.sdk.api.bean.reserved;

import com.google.gson.annotations.Expose;



/**
 * 公积金账户交易明细查询(广东) 返回数据循环体实体类
 * @author tongyapeng
 *
trandate		交易日期	否	
trantype		交易类型	否	
tranamt		交易金额	否	

 */
public class ReservedDealDetailInfo {
	@Expose
	private String trandate;
	@Expose
	private String trantype;
	@Expose
	private String tranamt;
	
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	public String getTrantype() {
		return trantype;
	}
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}
	public String getTranamt() {
		return tranamt;
	}
	public void setTranamt(String tranamt) {
		this.tranamt = tranamt;
	}	
}
