package com.boc.bocop.sdk.util;

public class ParaType {
	public static final String KEY_APPID = "client_id";
	public static final String KEY_APPSECRET = "client_secret";
	public static final String KEY_USER = "user_id";
	public static final String KEY_PWD = "password";
	public static final String KEY_ENCTYP = "enctyp";
	public static final String KEY_GRANTP = "grant_type";
	public static final String KEY_RESPONSE_TYPE = "response_type"; 
	public static final String KEY_ACTON = "acton";
	public static final String KEY_ENRANDOM = "enrandom";
	public static final String KEY_RANDOMID = "randomid";
	public static final String KEY_STATE = "state";
		
	public static final String KEY_ACCESS_TOKEN = "access_token";
	public static final String KEY_TOKEN_TYPE = "token_type";
	public static final String KEY_REFRESH_TOKEN = "refresh_token";
	public static final String KEY_EXPIRES_IN = "expires_in";
	public static final String KEY_USERID = "token_userid";
	
	public static final String KEY_ERROR = "error";
	public static final String KEY_DESCRIPTION = "error_description";
	
	public static final String HTTPMETHOD_POST = "POST";
	public static final String HTTPMETHOD_DELETE = "DELETE";
	public static final String HTTPMETHOD_PUT = "PUT";
	public static final String HTTPMETHOD_GET = "GET";
	
	public static final int OAUTH_CONTAINER = 0; // 容器登录
	public static final int OAUTH_APP = 1; // 小应用登录
	public static final int OAUTH_DEL  = 2;//容器注销
	public static final int GET_ROMDAM = 3;
	public static final int OAUTH_REFRESH = 4;
	public static final int OAUTH_REGISTER = 5; // 注册
	
	public static final String KEY_MSGCDE = "msgcde";
	public static final String KEY_RENMSG = "rtnmsg";
	public static final int RESULT_SUCCESS = 1; //成功
	
	public static final String END_FLAG = "endflg"; // END_FLAG	X(1)	包结束标志	1：仍有后续包	0：结束。	endflg
	public static final String PAGENO = "pageno"; // pageNo	9(6)	页码	END_FLAG为0时无意义。END_FLAG为1时，将该值填入后续请求包pageNo中。	pageno	 
	public static final String RECORD_COUNT  = "rcdcnt";//RECORD_COUNT	9(2)	笔数		rcdcnt
	public static final String SAPLIST = "saplist"; //  
		
	//Product
	public static final String OBJDATA = "objdata";
	
	public static final String PROID = "proid"; // PROID X(20) 产品代码
	public static final String PRONAM = "pronam"; // PRONAM X(50) 产品名称 产品简称
	public static final String PROCUR  = "procur";
	public static final String EXYIELD = "exyield"; // EXYIELD 9(15)V99 预计年收益率（%）
	public static final String PURVAL = "purval"; // PURVAL 9(6)V9(6) 购买价格
	public static final String PROTRM = "protrm"; // PROTERM X(5) 产品期限 以天数表示的产品到期日期限，等于产品到日期减去产品起息日。
	public static final String APPOBJ = "appobj";
	public static final String PROSTA = "prosta";
	public static final String BRNDID = "brndid";// BRANDID 9(6) 产品名牌编码
	public static final String BRADNM = "brndnm"; // BRANDNAME X(50) 产品品牌 产品系列所归属的产品品牌。中文
	public static final String SUBPMT = "subpmt";// SUBPAMT 9(15)V99 认购起点金额
	public static final String ADDAMT = "addamt";// ADDAMT 9(15)V99 追加认申购起点金额 addamt
	public static final String PURSDT = "pursdt";// PURSDATE X(8) 销售起始日期
	public static final String PUREDT = "puredt";// PUREDATE X(8) 销售结束日期
	public static final String INTSDT = "intsdt";// INTSDATE X(8) 产品起息日
	public static final String EDATE = "edate";// EDATE X(8) 产品到期日
	public static final String ISPRE = "ispre";
	public static final String CYCCNT = "cyccnt";// CYCCNT X(4) 剩余可购买最大期数 非周期性产品返回空
	
	public static final String PRORSK = "prorsk";
	public static final String AUTOPM = "autopm";
	public static final String IMPWPM = "impwpm";
	public static final String PROURL = "prourl";
	public static final String HEADURL = "headurl";
	
	//ICARDBALINFO
	public static final String ACCNO = "accno";
	public static final String BAL1 = "bal1";
	public static final String BAL2 = "bal2";
	public static final String BAL3 = "bal3";
	public static final String BAL4 = "bal4";
	public static final String BAL5 = "bal5";
	public static final String BAL6 = "bal6";
}

