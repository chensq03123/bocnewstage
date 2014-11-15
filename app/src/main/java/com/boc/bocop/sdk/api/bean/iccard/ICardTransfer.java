package com.boc.bocop.sdk.api.bean.iccard;

import java.io.Serializable;

import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.ResponseEnumType;
import com.google.gson.annotations.Expose;


/**
 * ic卡交易明细查询中多笔返回数据实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class ICardTransfer extends ResponseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String trantm = " ";
	@Expose
	private String amount = " ";
	@Expose
	private String trntyp = " ";
	@Expose
	private String chnnal = " ";
	@Expose
	private String rtnmsg = " ";

	/**
	 * @return 交易日期时间 日期格式：yyyymmdd hhmmss
	 */
	public String getTrantm() {
		return trantm;
	}

	public void setTrantm(String trantm) {
		this.trantm = trantm;
	}

	/**
	 * @return 交易金额   格式为：整数为10位，小数为2位，位数不足时， 对于整数部分左补0 小数部分右补 000000000100
	 */
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return 交易类型码对应的描述
	 * 					201	圈存
	 * 					404	押金	
	 * 					502	换卡
	 * 					506	换卡	 
	 * 					514	补卡	 
	 * 					518	补卡	  
	 * 					528	转卡	 
	 * 					530	转卡	 
	 * 					531	转卡	 
	 * 					711	非指定账户圈存	
	 * 					721	签约账户圈存	  
	 * 					731	指定账户圈存	  
	 * 					751	现金圈存	
	 * 					760	自动圈存	
	 * 					770	空中圈存	
	 * 					781	现金圈提	
	 * 					782	补现金圈提	
	 * 					784	指定账户转账圈提	  
	 * 					785	补指定账户转账圈提	  
	 * 					790	脱机消费补录	  
	 * 					791	批量圈存	 
	 * 					803	销户圈提	 
	 * 					805	补销户圈提	 
	 * 					807	现金销户
	 * 					809	补现金销户	
	 * 					811	回收	   
	 * 					812	调账	
	 * 					813	调账	
	 * 					BTI	转卡	 
	 * 					BTO	转卡	
	 * 					PCS	脱机消费
	 * 					741	补登	 
	 * 					820	退货	  
	 * 					821	退货	  
	 * 					002	退货	 
	 *  
	 */
	public String getTrntypInfo(String trntyp) {
		return ResponseEnumType.ICCardTranType.getMessage(trntyp);
	}
	/**
	 * @return 交易类型码
	 * 					201	圈存
	 * 					404	押金	
	 * 					502	换卡
	 * 					506	换卡	 
	 * 					514	补卡	 
	 * 					518	补卡	  
	 * 					528	转卡	 
	 * 					530	转卡	 
	 * 					531	转卡	 
	 * 					711	非指定账户圈存	
	 * 					721	签约账户圈存	  
	 * 					731	指定账户圈存	  
	 * 					751	现金圈存	
	 * 					760	自动圈存	
	 * 					770	空中圈存	
	 * 					781	现金圈提	
	 * 					782	补现金圈提	
	 * 					784	指定账户转账圈提	  
	 * 					785	补指定账户转账圈提	  
	 * 					790	脱机消费补录	  
	 * 					791	批量圈存	 
	 * 					803	销户圈提	 
	 * 					805	补销户圈提	 
	 * 					807	现金销户
	 * 					809	补现金销户	
	 * 					811	回收	   
	 * 					812	调账	
	 * 					813	调账	
	 * 					BTI	转卡	 
	 * 					BTO	转卡	
	 * 					PCS	脱机消费
	 * 					741	补登	 
	 * 					820	退货	  
	 * 					821	退货	  
	 * 					002	退货	 
	 * 
	 */
	public String getTrntyp()
	{
		return trntyp;
	}

	public void setTrntyp(String trntyp) {
		this.trntyp = trntyp;
	}

	/**
	 * @return 交易渠道码对应的描述 
	 * 			11-BancsLink 
	 * 			03-自助 
	 * 			02-ATMC 
	 * 			60 -95566 
	 * 			61 -400 
	 * 			14 -POS（或空圈渠道）
	 * 			59 –网上银行
	 */
	public String getChnnalInfo(int chnnal) {
		return ResponseEnumType.ICCardChannalType.getMessage(chnnal);
	}
	/**
	 * @return 交易渠道码
	 * 			11-BancsLink 
	 * 			03-自助 
	 * 			02-ATMC 
	 * 			60 -95566 
	 * 			61 -400 
	 * 			14 -POS（或空圈渠道）
	 * 			59 –网上银行
	 */
	public String getChnnal() {
		return chnnal;
	}

	public void setChnnal(String chnnal) {
		this.chnnal = chnnal;
	}

	/**
	 * @return 备注
	 */
	public String getRemark() {
		return rtnmsg;
	}

	public void setRemark(String remark) {
		this.rtnmsg = remark;
	}
}
