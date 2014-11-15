package com.boc.bocop.sdk.api.bean.reserved;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.google.gson.annotations.Expose;


/**
 * 公积金账户交易明细查询(广东) 返回数据实体类
 * @author tongyapeng
 *
1	record_count	X(3)	记录数	否	
以下为循环
trandate		交易日期	否	
trantype		交易类型	否	
tranamt		交易金额	否	
 */
public class ReservedDealDetailListInfo extends ResponseBean  {
	@Expose
	private String record_count;
	/**
	 * 查询明细list
	 */
	@Expose
	private List<ReservedDealDetailInfo> saplist;
	
	public String getRecord_count() {
		return record_count;
	}
	public void setRecord_count(String record_count) {
		this.record_count = record_count;
	}
	public List<ReservedDealDetailInfo> getSaplist() {
		return saplist;
	}
	public void setSaplist(List<ReservedDealDetailInfo> saplist) {
		this.saplist = saplist;
	}
}
