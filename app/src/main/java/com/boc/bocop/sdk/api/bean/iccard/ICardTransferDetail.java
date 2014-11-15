package com.boc.bocop.sdk.api.bean.iccard;

import java.io.Serializable;
import java.util.List;

import com.boc.bocop.sdk.api.bean.ResponseBean;

/**
 * ic卡交易明细查询返回数据实体类
 * 
 * @author CindyLiu
 * @version V1.0
 * 
 */
public class ICardTransferDetail extends ResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int recunt = 0;
	private int pageno = 0;
	private List<ICardTransfer> cardList;

	/**
	 * @return 查询笔数
	 */
	public int getRecunt() {
		return recunt;
	}

	public void setRecunt(int recunt) {
		this.recunt = recunt;
	}

	/**
	 * @return 查询页数 001 002 003…终端进行累加,返回时以000作为结束标识
	 */
	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	/**
	 * @return 当查询笔数大于O时，多笔返回返回 ICardTransfer信息
	 */
	public List<ICardTransfer> getCardList() {
		return cardList;
	}

	public void setCardList(List<ICardTransfer> cardList) {
		this.cardList = cardList;
	}

}
