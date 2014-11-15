package com.boc.bocop.sdk.service.impl;

import java.util.LinkedHashMap;

import org.json.JSONException;

import android.content.Context;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuy;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuyEdInfo;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductBuyEdInfoCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductCancel;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductPrebuy;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductPrebuyCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductSearch;
import com.boc.bocop.sdk.api.bean.cftproduct.CFTProductSearchCriteria;
import com.boc.bocop.sdk.api.bean.cftproduct.CustomRisk;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.cftproduct.CFTProductBuilder;
import com.boc.bocop.sdk.service.engine.cftproduct.CFTProductParse;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

public class CFTProductService extends BaseService {
	private  static final int REQUEST_URL_CFTPRODUCT_SEARCH = 1;
	private  static final int REQUEST_URL_CFTPRODUCT_PREBUY = 2;
	private  static final int REQUEST_URL_CFTPRODUCT_BUY = 3;
	private  static final int REQUEST_URL_CFTPRODUCT_BUYEDINFO = 4;
	private  static final int REQUEST_URL_CFTPRODUCT_CANCEL = 5;
	private  static final int REQUEST_URL_CFTPRODUCT_CUSRISKSEARCH = 6;

	public static void queryCFTProduct(CFTProductSearchCriteria searchCriteria,
			ResponseListener listener) {
		Logger.d("BOCSDK:===>queryCFTProduct:url:"
				+ Constants.urlCftproductSearch);
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = CFTProductBuilder
				.queryCFTProduct(searchCriteria);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlCftproductSearch,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_CFTPRODUCT_SEARCH, listener);
		CFTProductService service = new CFTProductService();
		CFTProductSyncRequest requestTask = service.new CFTProductSyncRequest();
		requestTask.execute(para);

	}

	/**
	 * 预买入
	 * 
	 * @param alias
	 * @param prod
	 * @param listener
	 */
	public static void prebuyCFTProduct(
			CFTProductPrebuyCriteria preBuyCriteria, ResponseListener listener) {
		Logger.d("BOCSDK:===>prebuyCFTProduct:url"
				+ Constants.urlCftproductPrebuy);
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = CFTProductBuilder
				.prebuyCFTProduct(preBuyCriteria);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlCftproductPrebuy,
				ParaType.HTTPMETHOD_PUT, head, body,
				REQUEST_URL_CFTPRODUCT_PREBUY, listener);
		CFTProductService service = new CFTProductService();
		CFTProductSyncRequest requestTask = service.new CFTProductSyncRequest();
		requestTask.execute(para);

	}

	/**
	 * 买入
	 * 
	 * @param alias
	 * @param prod
	 * @param listener
	 */
	public static void buyCFTProduct(String alias, String trsseq,
			String etoken, ResponseListener listener) {
		Logger.d("BOCSDK:===>buyCFTProduct:url" + Constants.urlCftproductBuy);

		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = CFTProductBuilder.buyCFTProduct(
				alias, trsseq, etoken);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlCftproductBuy,
				ParaType.HTTPMETHOD_PUT, head, body,
				REQUEST_URL_CFTPRODUCT_BUY, listener);
		CFTProductService service = new CFTProductService();
		CFTProductSyncRequest requestTask = service.new CFTProductSyncRequest();
		requestTask.execute(para);

	}

	/**
	 * 查询买入情况
	 * 
	 * @param alias
	 * @param prod
	 * @param listener
	 */
	public static void queryCFTProductBuyEdInfo(
			CFTProductBuyEdInfoCriteria searchCriteria,
			ResponseListener listener) {
		Logger.d("BOCSDK:===>queryCFTProductBuyEdInfo:url"
				+ Constants.urlCftproductBuyedinfo);
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = CFTProductBuilder
				.queryCFTProductBuyEdInfo(searchCriteria);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlCftproductBuyedinfo,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_CFTPRODUCT_BUYEDINFO, listener);
		CFTProductService service = new CFTProductService();
		CFTProductSyncRequest requestTask = service.new CFTProductSyncRequest();
		requestTask.execute(para);

	}

	/**
	 * 理财产品撤销挂单
	 * 
	 * @param alias
	 * @param prod
	 * @param listener
	 */
	public static void cancelCFTProduct(String alias, String trsseq,
			String isorder, ResponseListener listener) {
		Logger.d("BOCSDK:===>cancelCFTProduct:url"
				+ Constants.urlCftproductCancel);
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = CFTProductBuilder
				.cancelCFTProduct(alias, trsseq, isorder);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlCftproductCancel,
				ParaType.HTTPMETHOD_PUT, head, body,
				REQUEST_URL_CFTPRODUCT_CANCEL, listener);
		CFTProductService service = new CFTProductService();
		CFTProductSyncRequest requestTask = service.new CFTProductSyncRequest();
		requestTask.execute(para);

	}

	/**
	 * 理财产品 查询客户风险等级
	 * 
	 * @param alias
	 * @param prod
	 * @param listener
	 */
	public static void searchCustomRisk(ResponseListener listener) {
		Logger.d("BOCSDK:===>searchCustomRisk:url"
				+ Constants.urlCftproductCusrisksearch);
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = CFTProductBuilder
				.searchCustomRisk(null);

		// 发送请求数据
		AsyncPara para = new AsyncPara(Constants.urlCftproductCusrisksearch,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_CFTPRODUCT_CUSRISKSEARCH, listener);
		CFTProductService service = new CFTProductService();
		CFTProductSyncRequest requestTask = service.new CFTProductSyncRequest();
		requestTask.execute(para);

	}

	 class CFTProductSyncRequest extends RequestAsyncTask {

		@Override
		protected String doInBackground(AsyncPara... params) {
			// TODO Auto-generated method stub
			String resp = null;
			String result = "成功";
			try {
				resp = HttpManager.openUrlSap(params[0].getUrl(),
						params[0].getHttpMethod(), params[0].getParamsHead(),
						params[0].getParamsBody());
			} catch (BOCOPException e) {
				// TODO Auto-generated catch block
				params[0].getListener().onException(e);
				result = e.getMessage();
				return result;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				params[0].getListener().onException(e);
				result = e.getMessage();
				return result;
			}
			Logger.d("resp 222222------------->" + resp);

			if (StringUtil.isNullOrEmpty(resp)) {
				params[0].getListener().onException(
						new BOCOPException("服务器返回异常", -1));
				result = "服务器返回异常";
				return result;
			} else {
				if (resp.contains("msgcde") && resp.contains("rtnmsg")) {
					try {
						params[0].getListener().onError(
								JSONParse.parseResponseError(resp));
						result = JSONParse.parseResponseError(resp)
								.getRtnmsg();
						return result;
					} catch (JSONException e) {
						params[0].getListener().onException(e);
					}
					
				} else {
					switch (params[0].getType()) {
					case REQUEST_URL_CFTPRODUCT_SEARCH:
						CFTProductSearch info = null;
						try {
							info = CFTProductParse.parseQueryResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(info);
						break;
					case REQUEST_URL_CFTPRODUCT_PREBUY:
						CFTProductPrebuy response = null;
						try {
							response = CFTProductParse
									.parsePreBuyResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(response);
						break;
					case REQUEST_URL_CFTPRODUCT_BUY:
						CFTProductBuy buyInfo = null;
						try {
							buyInfo = CFTProductParse.parseBuyResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(buyInfo);
						break;
					case REQUEST_URL_CFTPRODUCT_BUYEDINFO:
						CFTProductBuyEdInfo buyEdInfo = null;
						try {
							buyEdInfo = CFTProductParse
									.parseQueryBuyEdInfoResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(buyEdInfo);
						break;
					case REQUEST_URL_CFTPRODUCT_CANCEL:
						CFTProductCancel cancelInfo = null;
						try {
							cancelInfo = CFTProductParse
									.parseCancelResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(cancelInfo);
						break;
					case REQUEST_URL_CFTPRODUCT_CUSRISKSEARCH:
						CustomRisk riskInfo = null;
						try {
							riskInfo = CFTProductParse
									.parseCustomRiskResponse(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(riskInfo);
						break;
					default:
						break;
					}
				}
			}
			return result;
		}
	}

	public static boolean checkCFTProductOauthToken(Context context,
			final CFTProductSearchCriteria searchCriteria,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					CFTProductService.queryCFTProduct(searchCriteria, listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}

	public static boolean checkPrebuyCFTProductOauthToken(Context context,
			final CFTProductPrebuyCriteria preBuyCriteria,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					CFTProductService
							.prebuyCFTProduct(preBuyCriteria, listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}

	public static boolean checkBuyCFTProductOauthToken(Context context,
			final String alias, final String trsseq, final String etoken,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					CFTProductService.buyCFTProduct(alias, trsseq, etoken,
							listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}

	public static boolean checkQueryCFTProductBuyEdInfoOauthToken(
			Context context, final CFTProductBuyEdInfoCriteria searchCriteria,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					CFTProductService.queryCFTProductBuyEdInfo(searchCriteria,
							listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}

	public static boolean checkSearchCustomRisk(Context context,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					CFTProductService.searchCustomRisk(listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}

	public static boolean checkCancelCFTProductOauthToken(Context context,
			final String alias, final String trsseq, final String isorder,
			final ResponseListener listener) {
		Oauth2AccessToken accessToken = AccessTokenKeeper
				.readAccessToken(BOCOPPayApi.getContext());

		if (accessToken != null && accessToken.isSessionValid()) {
			return true;
		} else {
			BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
					AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue());
			bocopSDKApi.authorize(context, new ResponseListener() {

				@Override
				public void onComplete(ResponseBean response) {
					CFTProductService.cancelCFTProduct(alias, trsseq, isorder,
							listener);
				}

				@Override
				public void onException(Exception e) {
				}

				@Override
				public void onError(Error e) {
				}

				@Override
				public void onCancel() {
					listener.onCancel();
				}

			});
		}

		return false;
	}
}
