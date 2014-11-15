package com.boc.bocop.sdk.service.impl;

import java.util.LinkedHashMap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import cfca.mobile.exception.CodeException;
import cfca.mobile.sip.SipBox;
import cfca.mobile.sip.SipResult;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.bean.oauth.ContainerInfo;
import com.boc.bocop.sdk.api.bean.oauth.OAuthResponseInfo;
import com.boc.bocop.sdk.api.bean.oauth.RandomResponse;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.engine.oauth.BOCOPLoginDialog;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.BOCOPParameters;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.ResourceUtil;
import com.boc.bocop.sdk.util.StringUtil;
import com.boc.bocop.sdk.util.Utility;

/**
 * Oauth登录认证服务类
 * @author cindy
 *
 */
public class OAuthService extends BaseService {
	// AccessToken实例
	private static Oauth2AccessToken accessToken = null;

	private static Context mContext = null;
	private static ProgressDialog mSpinner;
	private static OauthHandler oauthHandle = null;
	private static ResponseListener mListener;
	private static SipBox bocopPwdView = null;

	private final int RANDOM_SUCCESS = 1;
	private final int OAUTH_ERROR = 2;
	private final int OAUTH_EXCEPTION = 3;

	private static String mUserId = "";
	private static boolean isAsrFlag = false;

	/**
	 * 
	 * 进行BOCOPPay登录认证,会弹出登录dialog
	 * 
	 * @param context
	 *            调用认证功能的Context实例
	 * @param listener
	 *            OAuthListener 认证的回调接口
	 */
	public static void authorize(Context context, ResponseListener listener) {
		startDialog(context,listener, false);
	}
	
	/**
	 * 
	 * 进行BOCOPPay登录认证,会弹出登录dialog
	 * 
	 * @param context
	 *            调用认证功能的Context实例
	 * @param isNeedToRegister
	 *            是否显示注册按钮
	 * @param listener
	 *            OAuthListener 认证的回调接口
	 */
	public static void authorizeAsr(Context context,boolean isNeedToRegister, ResponseListener listener) {
		startDialog(context,listener, true);
	}

	/**
	 * 调用BOCOPPay登录认证,不会弹出登录dialog
	 * 
	 * @param context
	 *            调用登录认证的context实例
	 * @param name
	 *            用户输入的用户名
	 * @param password
	 *            用户输入的密码
	 * @param pwdView
	 *            加密控件对象实例
	 * @param listener
	 *            认证的回调接口
	 */
	public static void authorizeNoDialog(Context context, String name, String password, SipBox pwdView,
			ResponseListener listener) {
		isAsrFlag = false;
		mContext = context;
		OAuthService oauth = new OAuthService();
		oauthHandle = oauth.new OauthHandler();
		mListener = listener;
		mUserId = name;
		bocopPwdView = pwdView;
		
		String errorMsg = "";
		if (StringUtil.isNullOrEmpty(name)) {
			String errorMsgUser = ResourceUtil.parseAssetsString(context, "errorMsgUser");
			errorMsg = errorMsgUser;
		} else if (StringUtil.isNullOrEmpty(password)) {
			String errorMsgPwd = ResourceUtil.parseAssetsString(context, "errorMsgPwd");
			errorMsg = errorMsgPwd;
		} else if (name.trim().length() < 6) {
			String errorMsgMinUserText = ResourceUtil.parseAssetsString(context, "errorMinUserText");
			errorMsg = errorMsgMinUserText;
		} else if (password.trim().length() < 6) {
			String errorMsgMinPwdText = ResourceUtil.parseAssetsString(context, "errorMinPwdText");
			errorMsg = errorMsgMinPwdText;
		}
		if (!StringUtil.isNullOrEmpty(errorMsg)) {
			Toast toast = Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		else{
			getRandomResult(context, name, password, oauth.new RandomListener());
		}
	}
	
	/**
	 * 调用BOCOPPay登录认证,不会弹出登录dialog,保存登录COOKIE
	 * 
	 * @param context
	 *            调用登录认证的context实例
	 * @param name
	 *            用户输入的用户名
	 * @param password
	 *            用户输入的密码
	 * @param pwdView
	 *            加密控件对象实例
	 * @param listener
	 *            认证的回调接口
	 */
	public static void authorizeNoDialogAsr(Context context, String name, String password, SipBox pwdView,
			ResponseListener listener) {
		mContext = context;
		OAuthService oauth = new OAuthService();
		oauthHandle = oauth.new OauthHandler();
		mListener = listener;
		mUserId = name;
		bocopPwdView = pwdView;
		isAsrFlag = true;
		
		String errorMsg = "";
		if (StringUtil.isNullOrEmpty(name)) {
			String errorMsgUser = ResourceUtil.parseAssetsString(context, "errorMsgUser");
			errorMsg = errorMsgUser;
		} else if (StringUtil.isNullOrEmpty(password)) {
			String errorMsgPwd = ResourceUtil.parseAssetsString(context, "errorMsgPwd");
			errorMsg = errorMsgPwd;
		} else if (name.trim().length() < 6) {
			String errorMsgMinUserText = ResourceUtil.parseAssetsString(context, "errorMinUserText");
			errorMsg = errorMsgMinUserText;
		} else if (password.trim().length() < 6) {
			String errorMsgMinPwdText = ResourceUtil.parseAssetsString(context, "errorMinPwdText");
			errorMsg = errorMsgMinPwdText;
		}
		if (!StringUtil.isNullOrEmpty(errorMsg)) {
			Toast toast = Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		else{
			getRandomResult(context, name, password, oauth.new RandomListener());
		}
	}
	
	/**
	 * 取消登录认证
	 * 
	 * @param context
	 *            调用登录认证的context实例
	 */
	public static void cancelOauthNoDialog(Context context) {
		if (null != mListener) {
			if ((null != mSpinner) && (mSpinner.isShowing())) {
				mListener.onCancel();
			}
		}
	}
	
	/**
	 * 刷新令牌
	 * 
	 * @param context
	 * @param listener
	 */
	public static void refreshOAuthorize(Context context, ResponseListener listener) {
		BOCOPParameters parameters = new BOCOPParameters();
		parameters.add(ParaType.KEY_APPID, AppInfo.getAppKeyValue());
		parameters.add(ParaType.KEY_APPSECRET, AppInfo.getAppSecretValue());

		Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(BOCOPPayApi.getContext());
		if (null != accessToken) {
			String token = accessToken.getToken();
			parameters.add(ParaType.KEY_REFRESH_TOKEN, token);
		}
		parameters.add(ParaType.KEY_GRANTP, "refresh_token");

		String url = Constants.urlRefreshOauth + "?" + Utility.encodeUrl(parameters);

		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();

		AsyncPara para = new AsyncPara(url, ParaType.HTTPMETHOD_GET, head, body, ParaType.OAUTH_REFRESH, listener);

		OauthContainerRequest requestTask = new OauthContainerRequest();
		requestTask.execute(para);
	}
	
	public static Oauth2AccessToken getOAuthToken() {
		if (null == accessToken) {
			OAuthService.setOAuthToken();
		}
		return accessToken;
	}

	public static void setOAuthToken() {
		accessToken = new Oauth2AccessToken();
	}

	/**
	 * 
	 * 删除用户授权信息
	 * 
	 * @param context
	 *            调用认证功能的Context实例
	 */
	public static void delAuthorize(Context context) {
		AccessTokenKeeper.clear(context);
		ContainerInfo.setSessionCookie("");
	}
	
	public static void logoutAuthorize(Context context, ResponseListener listener){
		LinkedHashMap<String, String> head = BaseService
				.genPublicAsrHeader(context);
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();

		/*AsyncPara para = new AsyncPara(Constants.urlDelContainerReg,
				ParaType.HTTPMETHOD_DELETE, head, body, ParaType.OAUTH_DEL,
				listener);*/
		AsyncPara para = new AsyncPara(Constants.urlDelOauth,
				ParaType.HTTPMETHOD_DELETE, head, body, ParaType.OAUTH_DEL,
				listener);
		OauthContainerRequest requestTask = new OauthContainerRequest();
		requestTask.execute(para);
	}
	/**
	 * 
	 * 容器注销
	 * 
	 * @param context
	 *            调用认证功能的Context实例
	 */
	public static void delContainerReg(Context context,ResponseListener listener) {
		LinkedHashMap<String, String> head = BaseService
				.genPublicAsrHeader(context);
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();

		AsyncPara para = new AsyncPara(Constants.urlDelContainerReg,
				ParaType.HTTPMETHOD_DELETE, head, body, ParaType.OAUTH_DEL,
				listener);
		OauthContainerRequest requestTask = new OauthContainerRequest();
		requestTask.execute(para);
	}

	private static void getRandomResult(Context context, String name, String password, ResponseListener listener) {
			ContainerInfo.setUser(name);
			ContainerInfo.setPassword(password);

			LinkedHashMap<String, String> head = BaseService.genSapHeader();
			LinkedHashMap<String, String> body = null;

			AsyncPara para = new AsyncPara(Constants.urlGetTrdNum, ParaType.HTTPMETHOD_POST, head, body,
					ParaType.GET_ROMDAM, listener);
			final OauthContainerRequest requestTask = new OauthContainerRequest();
			requestTask.execute(para);

			mSpinner = new ProgressDialog(context);
			mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
			mSpinner.setCanceledOnTouchOutside(false);
			String oauthing = ResourceUtil.parseAssetsString(context, "oauthing");
			mSpinner.setMessage(oauthing);
			mSpinner.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					if (requestTask != null && requestTask.getStatus() != AsyncTask.Status.FINISHED) {
						Logger.d("Request on cancel------------->");
						requestTask.cancel(true);
					}
				}
			});
			mSpinner.show();
		
	}

	/*
	 * 开启登录界面
	 * 
	 * @param context
	 * @param listener
	 */
	private static void startDialog(final Context context, final ResponseListener listener, boolean isAsrFlag) {
		accessToken = AccessTokenKeeper.readAccessToken(context);

		if (accessToken != null && accessToken.isSessionValid()) {
			BOCOPOAuthInfo data = new BOCOPOAuthInfo();
			data.setAccess_token(accessToken.getToken());
			data.setRefresh_token(accessToken.getRefreshToken());
			data.setUserId(accessToken.getUserId());
			listener.onComplete(data);
		} else {
			if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
				String error = ResourceUtil.parseAssetsString(context, "error");
				String iNTERNET = ResourceUtil.parseAssetsString(context, "INTERNET");
				Utility.showAlert(context, error, iNTERNET);
			} else {
				String strUrl = "";
			
				if(!isAsrFlag){
					strUrl = Constants.urlOauthToken;
				}
				else{
					strUrl = Constants.urlOauthTokenAsr;
				}
				Logger.d("strUrl--->" + strUrl);
				
				Dialog dialog = new BOCOPLoginDialog(context, AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue(),
							strUrl, listener, isAsrFlag);
				
				 Window win = dialog.getWindow();//获取所在window
					LayoutParams params = win.getAttributes();//获取LayoutParams
					//win.setGravity(Gravity.LEFT | Gravity.TOP);
					//win.setGravity(Gravity.TOP);
					win.setGravity(Gravity.CENTER);
					params.x = 0;// 新位置X坐标
					params.y = 0;//新位置Y坐标						
					
					
					DisplayMetrics metrics = new DisplayMetrics();
					WindowManager wm = (WindowManager) context
							.getSystemService(Context.WINDOW_SERVICE);
					int width = (int)(wm.getDefaultDisplay().getWidth() * 0.9);
					int height = (int)(wm.getDefaultDisplay().getHeight() * 0.6);
					//int height = (int) DimensionPixelUtil.getDimensionPixelSize(DimensionPixelUtil.DIP, 800, context);
					//int width = context.
					params.width = width;
					params.height = height;		
					win.setAttributes(params);//设置生效

				dialog.show();
			}
		}
	}

	class RandomListener implements ResponseListener {

		@Override
		public void onComplete(ResponseBean response) {
			// TODO Auto-generated method stub
			RandomResponse randomResponse = null;
			if ((null != response) && (response instanceof RandomResponse)) {
				randomResponse = (RandomResponse) response;
				Message msg = Message.obtain();
				msg.obj = randomResponse;
				msg.what = RANDOM_SUCCESS;
				oauthHandle.sendMessage(msg);
			}
		}

		@Override
		public void onException(Exception e) {
			// TODO Auto-generated method stub
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}

			Message msg = Message.obtain();
			msg.obj = ResourceUtil.parseAssetsString(BOCOPPayApi.getContext(), "ExceptionString");
			msg.what = OAUTH_EXCEPTION;
			oauthHandle.sendMessage(msg);

			mListener.onException(e);
		}

		@Override
		public void onError(Error e) {
			// TODO Auto-generated method stub
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}

			if (e instanceof ResponseError) {
				Message msg = Message.obtain();
				msg.obj = ((ResponseError) e).getRtnmsg();
				msg.what = OAUTH_ERROR;
				oauthHandle.sendMessage(msg);
			}

			mListener.onError(e);
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			mListener.onCancel();
		}
	}

	private class OauthHandler extends Handler {
		@SuppressLint("NewApi")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case RANDOM_SUCCESS:
				RandomResponse randomResponse = null;
				randomResponse = (RandomResponse) msg.obj;

				String random_S = Base64.encodeToString(randomResponse.getRandom().getBytes(),
						Base64.DEFAULT);
				String randomId = randomResponse.getRandomid();
				//SipResult sipResult = bocopPwdView.getSipResult(randomResponse.getRandom());
				//bocopPwdView.setRandomKey_S(randomResponse.getRandom());
				bocopPwdView.setRandomKey_S(random_S);
				SipResult sipResult = null;
				try {
					sipResult = bocopPwdView.getValue();
				} catch (CodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String strUrl = "";
				if(isAsrFlag){
					strUrl = Constants.urlOauthTokenAsr;
				}else{
					strUrl = Constants.urlOauthToken;
				}
				
				if (null != sipResult) {
					startBocopOauth(ContainerInfo.getUser(), sipResult.getEncryptPassword(),
							sipResult.getEncryptRandomNum(), randomId, AppInfo.getAppKeyValue(),
							AppInfo.getAppSecretValue(), strUrl);
					Logger.d("enpassword-----" + sipResult.getEncryptPassword());
					Logger.d("enrandom-----" + sipResult.getEncryptRandomNum());
				} else {
					if (mSpinner.isShowing()) {
						mSpinner.dismiss();
					}

					String strInputError = ResourceUtil.parseAssetsString(mContext, "sipinputerror");
					Toast.makeText(mContext, strInputError, Toast.LENGTH_LONG).show();
				}
				break;
			default:
				if (mSpinner.isShowing()) {
					mSpinner.dismiss();
				}
				if (null != msg.obj) {
					String strError = msg.obj.toString();
					Toast.makeText(mContext, strError, Toast.LENGTH_LONG).show();
				}
				break;
			}
		}
	};

	private void startBocopOauth(String user, String pwd, String random, String randomId, String appID,
			String appSecret, String URL)
	{
		BOCOPParameters parameters = new BOCOPParameters();

		parameters.add(ParaType.KEY_APPID, appID);
		parameters.add(ParaType.KEY_APPSECRET, appSecret);
		parameters.add(ParaType.KEY_USER, user);
		parameters.add(ParaType.KEY_PWD, pwd);
		if(isAsrFlag){
			parameters.add(ParaType.KEY_RESPONSE_TYPE, "code");
		}
		// parameters.add(ParaType.KEY_RESPONSE_TYPE, "code");
		parameters.add(ParaType.KEY_ENCTYP, "0");
		parameters.add(ParaType.KEY_GRANTP, "implicit");

		String url = URL + "?" + Utility.encodeUrl(parameters);

		LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put(ParaType.KEY_ENRANDOM, random);
		body.put(ParaType.KEY_RANDOMID, randomId);
		Logger.d(url);
		Logger.d(random);
		
		AsyncPara para = null;
		if(isAsrFlag){
			para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head, body, ParaType.OAUTH_CONTAINER,
					new ResultBOCOPAuthListener());
		}else{
			para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head, body, ParaType.OAUTH_APP,
					new ResultBOCOPAuthListener());
		}
		/*AsyncPara para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head, body, ParaType.OAUTH_CONTAINER,
				new ResultBOCOPAuthListener());*/

		final OauthContainerRequest requestTask = new OauthContainerRequest();
		requestTask.execute(para);
	}

	class ResultBOCOPAuthListener implements ResponseListener {

		private OAuthResponseInfo data;
		@Override
		public void onComplete(ResponseBean response) {
			// TODO Auto-generated method stub
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}

			if (response != null && response instanceof OAuthResponseInfo) {
				data = (OAuthResponseInfo) response;

				if (null == OAuthService.getOAuthToken()) {
					OAuthService.setOAuthToken();
				}

				OAuthService.getOAuthToken().setToken(data.getAccess_token());
				OAuthService.getOAuthToken().setExpiresIn(String.valueOf(data.getExpires_in()));
				OAuthService.getOAuthToken().setRefreshToken(data.getRefresh_token());
				OAuthService.getOAuthToken().setUserId(data.getUser_id());

				AccessTokenKeeper.keepAccessToken(mContext, OAuthService.getOAuthToken());
				BOCOPOAuthInfo info = new BOCOPOAuthInfo();
				info.setAccess_token(data.getAccess_token());
				info.setRefresh_token(data.getRefresh_token());
				/* info.setIsmsgfull(data.getIsmsgfull()); */
				info.setUserId(data.getUser_id());

				mListener.onComplete(info);
			}
		}

		@Override
		public void onException(Exception e) {
			// TODO Auto-generated method stub
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			Message msg = Message.obtain();
			msg.obj = ResourceUtil.parseAssetsString(BOCOPPayApi.getContext(), "ExceptionString");
			msg.what = OAUTH_EXCEPTION;
			oauthHandle.sendMessage(msg);
			mListener.onException(e);
		}

		@Override
		public void onError(Error e) {
			// TODO Auto-generated method stub
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			if (e instanceof ResponseError) {
				Message msg = Message.obtain();
				msg.obj = ((ResponseError) e).getRtnmsg();
				msg.what = OAUTH_ERROR;
				oauthHandle.sendMessage(msg);
			}
			mListener.onError(e);
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			mListener.onCancel();
		}
	}
}
