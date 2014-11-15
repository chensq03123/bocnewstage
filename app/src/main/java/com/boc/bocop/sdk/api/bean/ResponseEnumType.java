package com.boc.bocop.sdk.api.bean;

import java.util.HashMap;
import java.util.Map;

public class ResponseEnumType {

	/**
	 * ICCard 交易类型
	 */
	public enum ICCardTranType {
		BOCOP_ICCARD_CREDITLOAD("201", "圈存"), 
		BOCOP_ICCARD_PLEDGECASH("404", "押金"), 
		BOCOP_ICCARD_CHANGECARD("502", "换卡"), 
		BOCOP_ICCARD_CHANGECARD2("506", "换卡"), 
		BOCOP_ICCARD_REISSUECARD("514", "补卡"), 
		BOCOP_ICCARD_REISSUECARD2("518", "补卡"), 
		BOCOP_ICCARD_SHIFTCARD("528", "转卡"), 
		BOCOP_ICCARD_SHIFTCARD2("530", "转卡"), 
		BOCOP_ICCARD_SHIFTCARD3("531", "转卡"), 
		BOCOP_ICCARD_UNCOUNTCREDITLOAD(	"711", "非指定账户圈存"), 
		BOCOP_ICCARD_COUNTCREDITLOAD("721", "签约账户圈存"), 
		BOCOP_ICCARD_APPOINTCOUNTCREDITLOAD("731", "指定账户圈存"), 
		BOCOP_ICCARD_CASHCREDITLOAD("751", "现金圈存"), 
		BOCOP_ICCARD_AUTOCREDITLOAD("760", "自动圈存"), 
		BOCOP_ICCARD_AIRCREDITLOAD("770", "空中圈存"), 
		BOCOP_ICCARD_CASH("781", "现金"), 
		BOCOP_ICCARD_CASHCREDITBRINGUP("782", "补现金圈提"), 
		BOCOP_ICCARD_POINTCOUNTEXCHANGECREDITLOAD("784", "指定账户转账圈提"), 
		BOCOP_ICCARD_REISSUECOUNTCHANGECREDITLOAD("785", "补指定账户转账圈提"), 
		BOCOP_ICCARD_OFFLINECONSUMEREISSUE("790","脱机消费补录"), 
		BOCOP_ICCARD_BATCHCREDITLOAD("791", "批量圈存"), 
		BOCOP_ICCARD_CLOSECOUNTCREDITLOAD("803", "销户圈提"), 
		BOCOP_ICCARD_REISSUECLOSECOUNTCREDITLOAD("805",	"补销户圈提"), 
		BOCOP_ICCARD_CASHCLOSECOUNT("807", "现金销户"), 
		BOCOP_ICCARD_REISSUECASHCLOSECOUNT("809", "补现金销户"), 
		BOCOP_ICCARD_RECOVER("811", "回收"), 
		BOCOP_ICCARD_ADJUSTMENT("812", "调账"), 
		BOCOP_ICCARD_ADJUSTMENT2("813", "调账"), 
		BOCOP_ICCARD_SHIFTCARD4("BTI", "转卡"), 
		BOCOP_ICCARD_SHIFTCARD5("BTO", "转卡"), 
		BOCOP_ICCARD_OFFLINECONSUME("PCS", "脱机消费"), 
		BOCOP_ICCARD_REISSUELOG("741", "补登"), 
		BOCOP_ICCARD_RETURNGOODS("820", "退货"), 
		BOCOP_ICCARD_RETURNGOODS2("821", "退货"), 
		BOCOP_ICCARD_RETURNGOODS3("002", "退货"),
		;

		private String message;
		private String code;
		
		private static Map<String, String> mapCode = new HashMap<String, String>();
		static {
			for (ICCardTranType s : ICCardTranType.values()) {
				mapCode.put(s.code, s.message);
			}
		}

		private ICCardTranType(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public void setCode(String code) {
			this.code = code;
			this.message = mapCode.get(code);
		}

		public String toString() {
			return super.toString() + "(" + code + "," + message + ")";
		}

		public String getMessage() {
			return message;
		}

		public static String getMessage(String code) {
			return mapCode.get(code);
		}
		
		public String getCode(){
			return this.code;
		}
	}
	
	/**
	 * ICCard 交易渠道
	 */
	public enum ICCardChannalType {
		BOCOP_ICCARDCHANNAL_BANCSLINK(11, "柜面"), 
		BOCOP_ICCARDCHANNAL_AUTO(3, "自助"), 
		BOCOP_ICCARDCHANNAL_ATMC(2, "ATM终端"), 
		BOCOP_ICCARDCHANNAL_95566(60, "电话银行"), 
		BOCOP_ICCARDCHANNAL_400(61, "电话银行"), 
		BOCOP_ICCARDCHANNAL_POS(14, "POS"), 
		BOCOP_ICCARDCHANNAL_EBANK(59, "网上银行"), 
		;
		private int code;
		private String message;
		
		
		private static Map<Integer,String> map = new HashMap<Integer,String>();
		static {
			for(ICCardChannalType s:ICCardChannalType.values()){
				map.put(s.code, s.message);
		    }
		}


		private ICCardChannalType(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public void setCode(int code) {
			this.code = code;
			this.message = map.get(code);
		}

		public String toString() {
			return super.toString() + "(" + code + "," + message + ")";
		}

		public String getMessage() {
			return message;
		}

		public static String getMessage(int code) {
			return map.get(code);
		}
		
		public int getCode(){
			return this.code;
		}
	
	}
	
	/**
	 * 产品币种类型定义 
	 */
	public enum ProductProcurType {
		BOCOP_PRODUCTPROCURTYPE_RMB(1, "人民币元"), 
		BOCOP_PRODUCTPROCURTYPE_UAS(14, "美元"), 
		BOCOP_PRODUCTPROCURTYPE_ENG(12, "英镑"), 
		BOCOP_PRODUCTPROCURTYPE_HK(13, "港币"), 
		BOCOP_PRODUCTPROCURTYPE_CAN(28, "加拿大元"), 
		BOCOP_PRODUCTPROCURTYPE_AUS(29, "澳元"), 
		BOCOP_PRODUCTPROCURTYPE_EUR(38, "欧元"), 
		BOCOP_PRODUCTPROCURTYPE_JAN(27, "日元"), 
		;
		private int code;
		private String message;
		
		
		private static HashMap<Integer,String> map = new HashMap<Integer,String>();
		static {
			for(ProductProcurType s:ProductProcurType.values()){
				map.put(s.code, s.message);
		    }
		}


		private ProductProcurType(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public void setCode(int code) {
			this.code = code;
			this.message = map.get(code);
		}

		public String toString() {
			return super.toString() + "(" + code + "," + message + ")";
		}

		public String getMessage() {
			return message;
		}

		public static String getMessage(int code) {
			return map.get(code);
		}
		
		public int getCode(){
			return this.code;
		}
	
	}
	
	/**
	 * 产品交易类型定义 
	 */
	public enum ProductTradeType {
		BOCOP_PRODUCTTRADETYPE_BYE(0, "认购"), 
		BOCOP_PRODUCTTRADETYPE_PREBYE(1, "申购"), 
		BOCOP_PRODUCTTRADETYPE_CASHBACK(2, "赎回"), 
		BOCOP_PRODUCTTRADETYPE_HLZT(3, "红利再投"), 
		BOCOP_PRODUCTTRADETYPE_HLFF(4, "红利发放"), 
		BOCOP_PRODUCTTRADETYPE_LXFH(5, "（经过）利息返还"), 
		BOCOP_PRODUCTTRADETYPE_BJFH(6, "本金返还"), 
		BOCOP_PRODUCTTRADETYPE_QXQSH(7, "起息前赎回"),
		BOCOP_PRODUCTTRADETYPE_LXZFE(8, "利息折份额"),
		;
		private int code;
		private String message;
		
		
		private static Map<Integer,String> map = new HashMap<Integer,String>();
		static {
			for(ProductTradeType s:ProductTradeType.values()){
				map.put(s.code, s.message);
		    }
		}


		private ProductTradeType(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public void setCode(int code) {
			this.code = code;
			this.message = map.get(code);
		}

		public String toString() {
			return super.toString() + "(" + code + "," + message + ")";
		}

		public String getMessage() {
			return message;
		}

		public static String getMessage(int code) {
			return map.get(code);
		}
		
		public int getCode(){
			return this.code;
		}
	
	}
	

}
