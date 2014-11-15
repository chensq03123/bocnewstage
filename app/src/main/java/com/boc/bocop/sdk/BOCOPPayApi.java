/********************************************************************
 * @File：BOCOPPayApi.java 
 * @Copyright：Copyright (C) 2013 Bocsoft. All Rights Reserved. 
 * @Author：Alan Shen 
 * @Created：2013-1-25 16:00 ShangHai  
 * @Modify:
 *      2013-1-25 16:00   create
 *      2013-1-25 19:00   add method getAppKey()
 *******************************************************************/

package com.boc.bocop.sdk;

import android.content.Context;
import android.widget.FrameLayout;
import cfca.mobile.sip.SipBox;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.accfund.AccFundBalInfo;
import com.boc.bocop.sdk.api.bean.accfund.AccFundDepositInfo;
import com.boc.bocop.sdk.api.bean.accfund.DepositInfo;
import com.boc.bocop.sdk.api.bean.bill.BalanceCriteria;
import com.boc.bocop.sdk.api.bean.bill.BalanceCriteria1;
import com.boc.bocop.sdk.api.bean.bill.BranchCriteria;
import com.boc.bocop.sdk.api.bean.bill.CardListInfoCriteria;
import com.boc.bocop.sdk.api.bean.bill.ContactTransCriteria;
import com.boc.bocop.sdk.api.bean.bill.FavorableCriteria;
import com.boc.bocop.sdk.api.bean.bill.IssuedBillQueryCriteria;
import com.boc.bocop.sdk.api.bean.bill.Rate;
import com.boc.bocop.sdk.api.bean.bill.UnissuedBillQueryCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.BuyEdInfo;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProduct;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuy;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuyEdInfo;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuyEdInfoCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductCancel;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductPrebuy;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductPrebuyCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductSearch;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductSearchCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CustomRisk;
import com.boc.bocop.sdk.api.bean.fund.Fund900Criteria;
import com.boc.bocop.sdk.api.bean.fund.Fund900Response;
import com.boc.bocop.sdk.api.bean.fund.FundPrivateDealCriteria;
import com.boc.bocop.sdk.api.bean.fund.FundPrivateDealResponse;
import com.boc.bocop.sdk.api.bean.fund.FundPrivateOpenCriteria;
import com.boc.bocop.sdk.api.bean.fund.FundPrivateOpenResponse;
import com.boc.bocop.sdk.api.bean.fund.FundPrivateRedeemCriteria;
import com.boc.bocop.sdk.api.bean.fund.FundPrivateRedeemResponse;
import com.boc.bocop.sdk.api.bean.fund.FundprivateQueryBancsCirteria;
import com.boc.bocop.sdk.api.bean.fund.FundprivateQueryBancsResponse;
import com.boc.bocop.sdk.api.bean.iccard.ICardBalInfo;
import com.boc.bocop.sdk.api.bean.iccard.ICardCriteria;
import com.boc.bocop.sdk.api.bean.iccard.ICardTransfer;
import com.boc.bocop.sdk.api.bean.iccard.ICardTransferDetail;
import com.boc.bocop.sdk.api.bean.medicaltreatment.MTCardDealQueryCriteria;
import com.boc.bocop.sdk.api.bean.medicaltreatment.MTDealDetailListInfo;
import com.boc.bocop.sdk.api.bean.oauth.ContainerInfo;
import com.boc.bocop.sdk.api.bean.rate.CurrencyRateSearch;
import com.boc.bocop.sdk.api.bean.reserved.ReservedBalanceCriteria;
import com.boc.bocop.sdk.api.bean.reserved.ReservedBalanceResponse;
import com.boc.bocop.sdk.api.bean.reserved.ReservedDealCriteria;
import com.boc.bocop.sdk.api.bean.reserved.ReservedDealDetailListInfo;
import com.boc.bocop.sdk.api.bean.socialsecurity.SSCardQueryCriteria;
import com.boc.bocop.sdk.api.bean.socialsecurity.SSCardQueryList;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfo;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoCriteria;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoSearch;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.http.AsyncHttpClient;
import com.boc.bocop.sdk.http.JsonResponseListenerAdapterHandler;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.impl.AccFundService;
import com.boc.bocop.sdk.service.impl.CFTProductService;
import com.boc.bocop.sdk.service.impl.ICCardService;
import com.boc.bocop.sdk.service.impl.OAuthService;
import com.boc.bocop.sdk.service.impl.RateService;
import com.boc.bocop.sdk.service.impl.UserInfoSerice;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.IniReadTool;
import com.boc.bocop.sdk.util.Oauth2AccessToken;

/**
 * 中银开放平台支付Android SDK主接口
 * 
 * @author CindyLiu
 * @version V1.0, 2013-1-10
 */
public class BOCOPPayApi {

	/** BOCOPPayApi实例 */
	private static BOCOPPayApi instance = null;

	/** 当前Activity Context实例 */
	private static Context context;

	/** 保存第三方应用的Appkey和AppSecret */
	private static AppInfo appInfo;

	/**
	 * BOCOPPayApi初始化
	 * 
	 * @param context
	 *            当前activity对象，用于获取当前网络状态，弹出对话框等,扩展性增强。
	 * @param appKey
	 *            分配给第三方应用的app key
	 * @param appSecret
	 *            分配给第三方应用的app Secret
	 */
	private BOCOPPayApi(Context context, String appKey, String appSecret) {
		BOCOPPayApi.context = context;
		BOCOPPayApi.appInfo = new AppInfo(appKey, appSecret);
		IniReadTool.readHTTPSharedPreferences(context);
	}

	/**
	 * 返回第三方应用的Appkey和AppSecret
	 * 
	 * @return AppInfo
	 */
	public AppInfo getAppInfo() {
		return appInfo;
	}

	/**
	 * 返回BOCOPPayApi
	 * 
	 * @return Activity Context实例
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * 生成 BOCOPPayApi 实例
	 * 
	 * @param context
	 *            当前activity对象，用于获取当前网络状态，弹出对话框等,扩展性增强。
	 * @param appKey
	 *            分配给第三方应用的app key
	 * @param appSecret
	 *            分配给第三方应用的app Secret
	 * @return BOCOPPayApi 实例
	 */
	public synchronized static BOCOPPayApi getInstance(Context context,
			String appKey, String appSecret) {
		if (instance == null) {
			instance = new BOCOPPayApi(context, appKey, appSecret);
		}
		return instance;
	}

	/**
	 * 设置 IP和端口号
	 * 
	 * @param context
	 *            当前activity对象，用于获取当前网络状态，弹出对话框等,扩展性增强。
	 * @param ip
	 *            域名或URL IP 地址
	 * @param port
	 *            端口号
	 * @param httpApsPrefix 注册地址
	 */
	public void initURLIPPort(Context context, String ip, int port, boolean isShowRegister, String httpApsPrefix){
		IniReadTool.clearHTTPSharedPreferences(context);
		IniReadTool.writeHTTPSharedPreferences(context, ip, port, isShowRegister, httpApsPrefix);
		IniReadTool.readHTTPSharedPreferences(context);
		Constants.setHttpPrefixPort(ip, port, isShowRegister, httpApsPrefix);
	}

	// 授权验证-----------------------------------------------
	/**
	 * 授权验证，弹出登录对话框
	 * 
	 * @param context
	 *            当前activity对象，用于获取当前网络状态，弹出对话框等,扩展性增强。
	 * @param listener
	 *            第三方应用实现ResponseListener接口，response 返回BOCOPOAuthInfo实体类
	 * @see com.boc.bocop.sdk.api.event.ResponseListener
	 * @see BOCOPOAuthInfo
	 */
	public void authorize(Context context, ResponseListener listener) {
		OAuthService.authorize(context, listener);
	}

	/**
	 * 授权验证,不会弹出登录对话框
	 * 
	 * @param context
	 *            当前activity对象，用于获取当前网络状态，弹出对话框等,扩展性增强。
	 * @param name
	 *            输入的用户名
	 * @param password
	 *            输入的密码
	 * @param sip
	 *            sipbox加密控件
	 * @param listener
	 *            第三方应用实现ResponseListener接口，response 返回BOCOPOAuthInfo实体类
	 */
	public void authorizeNoDialog(Context context, String name,
			String password, SipBox sip, ResponseListener listener) {
		OAuthService.authorizeNoDialog(context, name, password, sip, listener);
	}

	/**
	 * 授权验证, token保存在ASR服务器
	 * @param context
	 * @param isNeedToRegister 是否显示注册按钮
	 * @param listener
	 */
	public void authorizeAsr(Context context, boolean isNeedToRegister,ResponseListener listener){
		OAuthService.authorizeAsr(context,isNeedToRegister, listener);
	}

	/**
	 * 授权验证,不会弹出登录对话框
	 * 
	 * @param context
	 *            当前activity对象，用于获取当前网络状态，弹出对话框等,扩展性增强。
	 * @param name
	 *            输入的用户名
	 * @param password
	 *            输入的密码
	 * @param sip
	 *            sipbox加密控件
	 * @param listener
	 *            第三方应用实现ResponseListener接口，response 返回BOCOPOAuthInfo实体类
	 */
	public void authorizeNoDialogAsr(Context context, String name,
			String password, SipBox sip, ResponseListener listener) {
		OAuthService.authorizeNoDialogAsr(context, name, password, sip, listener);
	}

	/**
	 * 获取到登录后保存的cookie
	 * @return
	 */
	public String getSessionCookie(){
		return ContainerInfo.getSessionCookie();
	}

	/**
	 * 删除授权
	 * 
	 * @param context
	 *            进行登录授权认证的activity
	 */
	public void delOAuthorize(Context context) {
		OAuthService.delAuthorize(context);
	}

	/**
	 * 判断OAuth认证是否过期
	 * @param context
	 * @return 未过期为true, 过期为false
	 */
	public boolean isOAuthSessionValid(Context context)
	{
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			return false;
		}
	}

	public void logoutOAuth(Context context, ResponseListener listener){
		OAuthService.logoutAuthorize(context, listener);
	}	

	/**
	 * 信用卡余额查询
	 */
	public static void creditbalsearch(Context context,BalanceCriteria criteria, ResponseListener listener) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(Constants.urlCreditbalsearch, BaseService.genPublicAsrHeader(context), criteria, new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, listener));
	}

	/**
	 *卡卡转账
	 */
	public static void cardtransfer(Context context,ContactTransCriteria criteria, ResponseListener listener) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.put(Constants.urlCardtransfer, BaseService.genPublicAsrHeader(context), criteria, new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, listener));
	}
	/**
	 * 普通卡卡转账
	 * @param context
	 * @param criteria
	 * @param listener
	 */
	public static void commoncardtransfer(Context context,ContactTransCriteria criteria, ResponseListener listener) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(Constants.urlCommonCardtransfer, BaseService.genPublicAsrHeader(context), criteria, new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, listener));
	}

}
