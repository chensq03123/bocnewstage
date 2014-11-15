package com.boc.bocop.sdk.service.impl;

import java.util.LinkedHashMap;

import org.json.JSONException;

import android.content.Context;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.iccard.ICardBalInfo;
import com.boc.bocop.sdk.api.bean.iccard.ICardCriteria;
import com.boc.bocop.sdk.api.bean.iccard.ICardTransferDetail;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.BOCOPException;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.iccard.ICCardBuilder;
import com.boc.bocop.sdk.service.engine.iccard.ICCardParse;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.HttpManager;
import com.boc.bocop.sdk.util.JSONParse;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.RequestAsyncTask;
import com.boc.bocop.sdk.util.StringUtil;

public class ICCardService extends BaseService {
	private  static final int REQUEST_URL_ICARD_BALINFO = 1;
	private  static final int REQUEST_URL_ICARD_TRANSFERDETAIL = 2;

	public static void queryICCardBalInfo(Context context,
			ICardCriteria searchCriteria, ResponseListener listener) {
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = ICCardBuilder
				.queryICCardBalInfo(searchCriteria);

		AsyncPara para = new AsyncPara(Constants.urlIcardBalinfo,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_ICARD_BALINFO, listener);
		ICCardService service = new ICCardService();
		ICCardSyncRequest requestTask = service.new ICCardSyncRequest();
		requestTask.execute(para);
	}

	public static void queryICCardTransferDetail(Context context,
			ICardCriteria searchCriteria, ResponseListener listener) {
		// 构造请求数据
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		LinkedHashMap<String, String> body = ICCardBuilder
				.queryICCardTransferDetail(searchCriteria);

		AsyncPara para = new AsyncPara(Constants.urlIcardTransferdetail,
				ParaType.HTTPMETHOD_POST, head, body,
				REQUEST_URL_ICARD_TRANSFERDETAIL, listener);
		ICCardService service = new ICCardService();
		ICCardSyncRequest requestTask = service.new ICCardSyncRequest();
		requestTask.execute(para);
	}

	class ICCardSyncRequest extends RequestAsyncTask {

		@Override
		protected String doInBackground(AsyncPara... params) {
			String resp = null;
			String result = "成功";
			try {
				resp = HttpManager.openUrlSap(params[0].getUrl(),
						params[0].getHttpMethod(), params[0].getParamsHead(),
						params[0].getParamsBody());
			} catch (BOCOPException e) {
				params[0].getListener().onException(e);
				 result = e.getMessage();
				 return result;
			} catch (JSONException e) {
				params[0].getListener().onException(e);
				 result = e.getMessage();
				 return result;
			}
			Logger.d("resp ------------->" + resp);

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
					case REQUEST_URL_ICARD_BALINFO:
						ICardBalInfo info = null;
						try {
							info = ICCardParse.parseICardBalInfo(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(info);
						break;
					case REQUEST_URL_ICARD_TRANSFERDETAIL:
						ICardTransferDetail icardTransinfo = null;
						try {
							icardTransinfo = ICCardParse
									.parseICardTransferDetail(resp);
						} catch (JSONException e) {
							params[0].getListener().onException(e);
						}
						params[0].getListener().onComplete(icardTransinfo);
						break;
					default:
						break;
					}
				}
			}
			return result;
		}
	}

	public static boolean checkICCardBalInfoOauthToken(final Context context,
			final ICardCriteria searchCriteria, final ResponseListener listener) {
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
					ICCardService.queryICCardBalInfo(context, searchCriteria,
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

	public static boolean checkICCardTransferOauthToken(final Context context,
			final ICardCriteria searchCriteria, final ResponseListener listener) {
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
					ICCardService.queryICCardTransferDetail(context,
							searchCriteria, listener);
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
