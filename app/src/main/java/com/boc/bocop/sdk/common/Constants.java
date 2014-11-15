package com.boc.bocop.sdk.common;

import com.boc.bocop.sdk.api.bean.AppInfo;

public class Constants {

	public static String httpPrefix = "https://opendtp.boc.cn";

	public static String httpApsPrefix = "https://openapi.boc.cn";
	public static boolean isShowRegister = true;
	public static int httpPort = 9080;
	// https
	public static int httpsPort = 443;
	
	// 理财产品
	public static String urlCftproductSearch = httpPrefix
			+ "/cftproduct/search";
	public static String urlCftproductPrebuy = httpPrefix
			+ "/cftproduct/prebuy";
	public static String urlCftproductBuy = httpPrefix
			+ "/cftproduct/buy";
	public static String urlCftproductBuyedinfo = httpPrefix
			+ "/cftproduct/buyedinfo";
	public static String urlCftproductCancel = httpPrefix
			+ "/cftproduct/cancel";
	public static String urlCftproductCusrisksearch = httpPrefix
			+ "/cftproduct/cusrisksearch";

	// 牌价
	public static String urlRateSearch = httpPrefix + "/rate/search";
	//信用卡余额
	public static String urlCreditbalsearch = httpPrefix + "/app/creditbalsearch";
	//多币种
	public static String urlQueryCreditbalsearch = httpPrefix + "/banking/querymoremoneybalance";
	//未出账单
	public static String urlNotbillquery = httpPrefix + "/app/notbillquery";
	//已出账单
	public static String urlYetbillquery = httpPrefix + "/app/yetbillquery";
	//卡卡转账
	public static String urlCardtransfer = httpPrefix + "/base/asr/transfer";
	//普通卡卡转账
		public static String urlCommonCardtransfer = httpPrefix + "/base/asr/cardtransfer";
	// IC卡
	public static String urlIcardBalinfo = httpPrefix
			+ "/icard/balinfo";
	public static String urlIcardTransferdetail = httpPrefix
			+ "/icard/transferdetail";

	// 公积金
	public static String urlAccfundBalinfo = httpPrefix
			+ "/accfund/balinfo";
	public static String urlAccfundDepositinfo = httpPrefix
			+ "/accfund/depositinfo";

	/**
	 *  登录验证
	 * 
	 */
	public static String urlOauthToken = httpPrefix + "/oauth/token";
	public static String urlOauthGetrdnum = httpPrefix
			+ "/oauth/getrdnum";
	/**
	 * ASR 登录验证
	 */
	public static String urlOauthTokenAsr = httpPrefix + "/oauth/authorize";
	
	/**
	 * 删除用户授权信息
	 */
	public static String urlDelOauth = httpPrefix + "/oauth/delauth";
	/*
	 * ASR 容器注销
	 */
	public static String urlDelContainerReg = httpPrefix
			+ "/appserver/oauth/delauth";
	/**
	 * 修改用户授权信息
	 */
	public static String urlRefreshOauth = httpPrefix + "/oauth/token";
	/**
	 * 获取随机数
	 */
	//public static String urlGetTrdNum = httpPrefix + "/oauth/getrdnum";
	public static String urlGetTrdNum = httpPrefix + "/oauth/getrdnum";
	/*
	 * ASR 容器中小应用登陆
	 */
	/*public static String urlAppLogin = httpPrefix
			+ "/appserver/appLogin";*/
	public static String urlAppLogin = httpPrefix
			+ "through/appserver/appLogin";

	// 基础
	public static String urlBaseInsertsigncontract = httpPrefix
			+ "/base/insertsigncontract";
	public static String urlBaseDelsigncontract = httpPrefix
			+ "/base/delsigncontract";
	public static String urlBaseResetpsw = httpPrefix
			+ "/base/resetpsw";
	public static String urlBaseInsertuserinfo = httpPrefix
			+ "/base/insertuserinfo";
	public static String urlBaseApsfindusrinfo = httpPrefix
			+ "/base/apsfindusrinfo";
	public static String urlBaseModifyusrinfo = httpPrefix
			+ "/base/modifyusrinfo";
	public static String urlBaseDelusrinfo = httpPrefix
			+ "/base/delusrinfo";
	public static String urlBaseUsrregist = httpPrefix
			+ "/base/usrregist";
	public static String urlBaseAppfindusrinfo = httpPrefix
			+ "/appfindusrinfo";
	
	//二期
		/**
		 * SA0028社保卡资料查询-广东
		 */
		public static String urlKeepbankinfoquery= httpPrefix+ "/banking/keepbankinfoquery";
		/**
		 * SA0029医保卡交易明细查询-广东
		 */
		public static String urlKeepbankdealquery= httpPrefix+ "/banking/keepbankdealquery";
		/**
		 * SA0030公积金帐号余额查询-广东
		 */
		public static String urlReservedbalancequery= httpPrefix+ "/banking/reservedbalancequery";
		/**
		 * SA0031公积金账户交易明细查询-广东
		 */
		public static String urlReserveddealquery= httpPrefix+ "/banking/reserveddealquery";
		/**
		 * SA0032基金对私开户
		 */
		public static String urlFundprivateopen= httpPrefix+ "/banking/fundprivateopen";
		/**
		 * SA0033基金对私认购/申购交易
		 */
		public static String urlFundprivatedeal= httpPrefix+ "/banking/fundprivatedeal";
		/**
		 * SA0033基金对私赎回
		 */
		public static String urlFundprivateredeem= httpPrefix+ "/banking/fundprivateredeem";
		/**
		 * SA0034基金对私查询Bancs客户信息
		 */
		public static String urlFundpriquerybancs= httpPrefix+ "/banking/fundpriquerybancs";
		/**
		 * SA0035基金挂单9000
		 */
		public static String urlFund900= httpPrefix+ "/banking/fund900";
		/**
		 * HTML5注册
		 */
		public static String urlRegister = httpApsPrefix + "/wap/register.php?devicetype=1&client_key="+AppInfo.getAppKeyValue();
		/*dtp测试*/
		/*
		 * ASR 信用卡未出账单
		 * add by ZY
		 */
		public static final String urlUnissuedBill = httpPrefix + "/app/notbillquery";
		/*
		 * ASR 信用卡余额查询
		 */
		public static final String urlCreditBalance = httpPrefix + "/app/creditbalsearch";
		/*
		 * ASR 查询用户卡资料
		 */
		public static final String urlFindUsrInfo = httpPrefix + "/appfindusrinfo";
		/**
		 * 附近网点查询
		 */
		public static final String urlBranch = httpPrefix + "/unlogin/querygeoinfo";
		/**
		 * 优惠商户查询
		 */
		public static final String urlBranch1 = httpPrefix + "/unlogin/querybcspinfo";
		/**
		 * 增加卡资料
		 */
		public static final String urlAddCardInfo = httpApsPrefix + "/app/adduserinfo";
		
		public static final boolean debugOn = true;

	public static void setHttpPrefixPort(String httpIp, int httpport, boolean isShowRegister_, String httpApsPrefix_) {
		httpPrefix = httpIp;
		httpPort = httpport;
		httpsPort = 443;
		isShowRegister = isShowRegister_;
		httpApsPrefix = httpApsPrefix_;
		setURL(httpPrefix);
	}

	public static String getHttpPrefix() {
		return httpPrefix;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public static void setURL(String httpPrefix) {
		// 理财产品
		urlCftproductSearch = httpPrefix + "/cftproduct/search";
		urlCftproductPrebuy = httpPrefix + "/cftproduct/prebuy";
		urlCftproductBuy = httpPrefix + "/cftproduct/buy";
		urlCftproductBuyedinfo = httpPrefix + "/cftproduct/buyedinfo";
		urlCftproductCancel = httpPrefix + "/cftproduct/cancel";
		urlCftproductCusrisksearch = httpPrefix
				+ "/cftproduct/cusrisksearch";

		// 牌价
		urlRateSearch = httpPrefix + "/rate/search";

		// IC卡
		urlIcardBalinfo = httpPrefix + "/icard/balinfo";
		urlIcardTransferdetail = httpPrefix + "/icard/transferdetail";

		// 公积金
		urlAccfundBalinfo = httpPrefix + "/accfund/balinfo";
		urlAccfundDepositinfo = httpPrefix + "/accfund/depositinfo";

		// 登录验证
		urlOauthToken = httpPrefix + "/oauth/token";
		urlOauthGetrdnum = httpPrefix + "/oauth/getrdnum";
		urlOauthTokenAsr = httpPrefix + "/oauth/authorize";
				
		/**
		 * 删除用户授权信息
		 */
		urlDelOauth = httpPrefix + "/oauth/delauth";
		/**
		 * ASR 容器注销
		 */
		urlDelContainerReg = httpPrefix
				+ "/appserver/oauth/delauth";
		
		/**
		 * 修改用户授权信息
		 */
		urlRefreshOauth = httpPrefix + "/oauth/token";
		/**
		 * 获取随机数
		 */
		urlGetTrdNum = httpPrefix + "/oauth/getrdnum";

		// 基础
		urlBaseInsertsigncontract = httpPrefix
				+ "/base/insertsigncontract";
		urlBaseDelsigncontract = httpPrefix + "/base/delsigncontract";
		urlBaseResetpsw = httpPrefix + "/base/resetpsw";
		urlBaseInsertuserinfo = httpPrefix + "/base/insertuserinfo";
		urlBaseApsfindusrinfo = httpPrefix + "/base/apsfindusrinfo";
		urlBaseModifyusrinfo = httpPrefix + "/base/modifyusrinfo";
		urlBaseDelusrinfo = httpPrefix + "/base/delusrinfo";
		urlBaseUsrregist = httpPrefix + "/base/usrregist";
		urlBaseAppfindusrinfo = httpPrefix + "/appfindusrinfo";
		
		/**
		 * HTML5注册
		 */
		urlRegister = httpApsPrefix + "/wap/register.php?devicetype=1&client_key="+AppInfo.getAppKeyValue();
	}
}
