package com.boc.bocop.sdk.api.bean.cftproduct;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 理财产品买入情况查询 返回数据
 * 
 * @author alan
 * 
 */
public class CFTProductBuyEdInfo extends ResponseBean {
	private String accno = " ";
	private long pageno = 0;
	private int rcdcnt = 0;

	private List<BuyEdInfo> buyEdInfos;

	/**
	 * @return 帐号
	 */
	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * @return 页码 000001 000002 000003…终端进行累加,返回时以000000作为结束标识
	 */
	public long getPageno() {
		return pageno;
	}

	public void setPageno(long pageno) {
		this.pageno = pageno;
	}

	/**
	 *  @return 笔数
	 */
	public int getRcdcnt() {
		return rcdcnt;
	}

	public void setRcdcnt(int rcdcnt) {
		this.rcdcnt = rcdcnt;
	}

	/**
	 * @return 理财产品信息，多笔返回
	 */
	public List<BuyEdInfo> getBuyEdInfos() {
		return buyEdInfos;
	}

	public void setBuyEdInfos(List<BuyEdInfo> buyEdInfos) {
		this.buyEdInfos = buyEdInfos;
	}

}
