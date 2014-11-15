package com.boc.bocop.sdk.api.bean.medicaltreatment;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * 医保卡交易明细查询 返回数据实体类
 * @author tongyapeng
 *
1	record_count	X(3)	记录数	否	
以下为循环
trandate		交易日期	否	
trantype		交易类型	否	
tranamt		交易金额	否	
balance		余额	否	

 */
public class MTDealDetailListInfo extends ResponseBean {
	@Expose
	private String record_count;
	/**
	 * 查询明细list
	 */
	@Expose
	private List<MTDealDetailInfo> saplist;
	
	public String getRecord_count() {
		return record_count;
	}
	public void setRecord_count(String record_count) {
		this.record_count = record_count;
	}
	public List<MTDealDetailInfo> getSaplist() {
		return saplist;
	}
	public void setSaplist(List<MTDealDetailInfo> saplist) {
		this.saplist = saplist;
	}
}
