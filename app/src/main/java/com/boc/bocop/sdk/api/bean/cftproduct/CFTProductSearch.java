package com.boc.bocop.sdk.api.bean.cftproduct;

import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * 
 * 理财产品查询返回结果实体类
 */
public class CFTProductSearch extends ResponseBean {

	private long pageno = 0;
	private int rcdcnt = 0;
	private String headurl = " ";
	private List<CFTProduct> products;

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
	 * @return 笔数
	 */
	public int getRcdcnt() {
		return rcdcnt;
	}

	public void setRcdcnt(int rcdcnt) {
		this.rcdcnt = rcdcnt;
	}
	
	/**
	 * @return 总协议url
	 */
	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	/** 
	 * @return 当rcdcnt 大于0时，返回 CFTProduct信息 列表
	 */
	public List<CFTProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CFTProduct> products) {
		this.products = products;
	}
}
