package com.boc.bocop.sdk.service.engine.oauth;

import java.util.LinkedHashMap;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import cfca.mobile.sip.SipResult;
import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.bean.oauth.ContainerInfo;
import com.boc.bocop.sdk.api.bean.oauth.OAuthResponseInfo;
import com.boc.bocop.sdk.api.bean.oauth.RandomResponse;
import com.boc.bocop.sdk.api.bean.oauth.RegisterResponse;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.impl.OAuthService;
import com.boc.bocop.sdk.service.impl.OauthContainerRequest;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.BOCOPEditPwdView;
import com.boc.bocop.sdk.util.BOCOPEditView;
import com.boc.bocop.sdk.util.BOCOPParameters;
import com.boc.bocop.sdk.util.DimensionPixelUtil;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.ResourceUtil;
import com.boc.bocop.sdk.util.StringUtil;
import com.boc.bocop.sdk.util.Utility;
import com.google.gson.Gson;

/**
 * 用来显示用户认证界面的dialog，
 * 
 * @author CindyLiu atcindyliu@hotmail.com
 * 2013/08/12 feiweiwei 增加CFCA安全登录控件
 * 2014/5/15,liuweina,页面增加注册按钮
 */

public class BOCOPLoginDialog extends Dialog implements
		View.OnClickListener, OnTouchListener {

	private String mUrl;
	private ResponseListener mListener;
	private ProgressDialog mSpinner;
	private ProgressBar mProgressBar;
	private WebView mWebView;
	
	private RegisterResponse mRegisterResponse;
	private int mRegisterState;
	private String mToken;
	
	protected static String mUserId = "";
	private boolean asrFlag = false;

	private static int theme = android.R.style.Theme_Translucent_NoTitleBar;
	private LinearLayout viewContainer;
	private RelativeLayout mContentView;
	private LinearLayout topContainer;
	private LinearLayout centerContainer;
	private ScrollView centerScrollView;

	private Button btnLogin;
	private Button btnRegister;
	private BOCOPEditView userView = null;
	private BOCOPEditPwdView pwdView = null;
	private BOCOPEditView inputImageView = null;
	private ImageView cancelImg;
	private ImageView radomImg;
	
	private static Context mContext;

	private static final int BUTTON_LOGIN = 0;
	private static final int BUTTON_CANCEL = 1;
	private static final int BUTTON_REGISTER = 2;
		
	private static final int NUM_FOUR = 4;
	private static final int NUM_FIVE = 5;
	private static final int NUM_TEN = 10;
	private static final int NUM_FOURTEEN = 14;
	private static final int NUM_TWENTY = 20;
	private static final int NUM_ZERO = 0;
	private static final int NUM_ONE = 1;
	private static final double NUM_POINT_THREE = 0.3;
	private static final double NUM_POINT_SEVEN = 0.7;

	private String random_S;
	private String randomId;
	
	private final int RANDOM_SUCCESS = 1;
	private final int OAUTH_ERROR = 2;
	private final int OAUTH_EXCEPTION = 3;
	private OauthHandler oauthHandle = null;
	private static int dialogWidth;
	private static int dialogHeight;
	
	public BOCOPLoginDialog(Context activity,String appID, String appSecret,
			String url, ResponseListener listener, boolean isAsrLogin) {
		super(activity, theme);
		mUrl = url;
		mListener = listener;
		mContext = activity;
		asrFlag = isAsrLogin;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL,
				0);
		WindowManager.LayoutParams attr = getWindow().getAttributes();
		dialogWidth = attr.width;
		dialogHeight = attr.height;
		mContentView = new RelativeLayout(getContext());
		mContentView.setGravity(Gravity.TOP);
		mProgressBar = new ProgressBar(mContext);
		mWebView = new WebView(mContext);
		mWebView.setBackgroundColor(Color.TRANSPARENT);
		loadWebViewConfig();
		initLoginView();
		addContentView(mContentView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		oauthHandle = new OauthHandler();
	}

	protected void onBack() {
		try {
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
		} catch (Exception e) {
		}
		dismiss();
	}
	
	private void initLoginView() {
		if (null != mContentView) {
			mContentView.removeAllViews();
		}
		WindowManager.LayoutParams wlp = getWindow().getAttributes();
		wlp.width = dialogWidth;
		wlp.height = dialogHeight;
		getWindow().setAttributes(wlp);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER;

		LinearLayout.LayoutParams lp0 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(
				(int) (LayoutParams.FILL_PARENT), LayoutParams.FILL_PARENT);
		lpView.gravity = Gravity.CENTER;
		mContentView.setBackgroundColor(Color.TRANSPARENT);

		viewContainer = new LinearLayout(getContext());
		viewContainer.setOrientation(LinearLayout.VERTICAL);

		topContainer = new LinearLayout(getContext());
		centerContainer = new LinearLayout(getContext());
		centerScrollView = new ScrollView(getContext());

		DisplayMetrics dm = this.getContext().getResources()
				.getDisplayMetrics();
		float density = dm.density;
		lp0.leftMargin = (int) (NUM_FIVE * density);
		lp0.topMargin = (int) (NUM_FIVE * density);
		lp0.rightMargin = (int) (NUM_FIVE * density);
		
		lp1.leftMargin = (int) (NUM_FIVE * density);
		lp1.rightMargin = (int) (NUM_FIVE * density);
		lp1.bottomMargin = (int) (NUM_FIVE * density);

		// 得到背景图片
		try {
			viewContainer.setBackgroundDrawable(ResourceUtil
					.decodeDrawableFromAsset(this.getContext(),
							"bocop_bg.9.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		topContainer.setOrientation(LinearLayout.HORIZONTAL);

		centerContainer.setOrientation(LinearLayout.VERTICAL);

		createTopLayout(topContainer);
		createCenterLayout(centerContainer);

		centerScrollView.addView(centerContainer, lp1);
		viewContainer.addView(topContainer, lp0);
		viewContainer.addView(centerScrollView, lp1);

		viewContainer.setGravity(Gravity.CENTER);
		mContentView.addView(viewContainer, lp);
	}

	// 登录导航界面设置
	private void createTopLayout(LinearLayout view) {
		LinearLayout container = new LinearLayout(getContext());
		container.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout leftView = new LinearLayout(getContext());
		LinearLayout rightView = new LinearLayout(getContext());

		LinearLayout.LayoutParams paramCenter = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);

		LinearLayout.LayoutParams paramLeft = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 35, this.getContext()));
		paramLeft.gravity = Gravity.CENTER;

		LinearLayout.LayoutParams paramRight = new LinearLayout.LayoutParams(
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 30, this.getContext()),
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 30, this.getContext()));
		paramRight.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

		paramLeft.setMargins(NUM_TEN, NUM_TEN, NUM_TEN, NUM_TEN);
		paramRight.setMargins(NUM_TEN, NUM_TEN, NUM_TEN, NUM_TEN);

		ImageView login_logo = new ImageView(getContext());
		Bitmap bitmap_logo = ResourceUtil.getAssertBitmap(this.getContext(),
				"login_logo.png");
		login_logo.setImageBitmap(bitmap_logo);
		leftView.addView(login_logo, paramLeft);

		cancelImg = new ImageView(getContext());
		Bitmap bitmap = ResourceUtil.getAssertBitmap(this.getContext(),
				"cancel_up.png");
		cancelImg.setImageBitmap(bitmap);
		cancelImg.setOnClickListener(this);
		cancelImg.setOnTouchListener(this);
		cancelImg.setId(BUTTON_CANCEL);
		rightView.addView(cancelImg, paramRight);

		TableLayout contentlayout = new TableLayout(this.getContext());
		contentlayout.setColumnStretchable(NUM_ZERO, true);

		TableRow row = new TableRow(this.getContext());
		row.addView(leftView, NUM_ZERO);
		row.addView(rightView, NUM_ONE);
		contentlayout.addView(row);

		container.addView(contentlayout, paramCenter);
		view.addView(container, paramCenter);
	}

	private void createCenterLayout(LinearLayout view) {
		LinearLayout centerView = new LinearLayout(getContext());
		centerView.setOrientation(LinearLayout.VERTICAL);

		LinearLayout.LayoutParams paramView = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		paramView.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
		paramView.setMargins(NUM_TEN, NUM_TWENTY, NUM_TEN, NUM_TEN);

		LinearLayout.LayoutParams paramTopView = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		paramTopView.gravity = Gravity.CENTER_HORIZONTAL
				| Gravity.CENTER_VERTICAL;
		paramTopView.setMargins(NUM_TEN, NUM_TWENTY, NUM_TEN, NUM_TEN);

		LinearLayout.LayoutParams paramLp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		paramLp.setMargins(10, 0, 0, 0);
		paramLp.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				1);

		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		String userHint = ResourceUtil.parseAssetsString(this.getContext(),
				"userHint");
		String pwdHint = ResourceUtil.parseAssetsString(this.getContext(),
				"pwdHint");

		Bitmap userLogo = ResourceUtil.getAssertBitmap(this.getContext(),
				"user_name.png");
		userView = new BOCOPEditView(this.getContext(), userLogo, userHint, 
				(int) (width * NUM_POINT_SEVEN));
		Bitmap passLogo = ResourceUtil.getAssertBitmap(this.getContext(),
				"pass_word.png");
		pwdView = new BOCOPEditPwdView(mContext, passLogo, pwdHint, 6, 15);
		int height = wm.getDefaultDisplay().getHeight();
		pwdView.setWindowHeight(height);
		pwdView.setLayoutLogin(viewContainer);
		ImageView img = new ImageView(this.getContext());
		img.setBackgroundColor(Color.argb(255, 239, 239, 239));

		LinearLayout inputUserPwdView = new LinearLayout(getContext());
		inputUserPwdView.setOrientation(LinearLayout.VERTICAL);
		try {
			inputUserPwdView.setBackgroundDrawable(ResourceUtil
					.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
							"bocoprect.9.png"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		inputUserPwdView.addView(userView, paramLp);
		inputUserPwdView.addView(img, param);
		inputUserPwdView.addView(pwdView, paramLp);


		//按钮部分
		LinearLayout loginView = new LinearLayout(getContext());
		loginView.setOrientation(LinearLayout.HORIZONTAL);
		//左边按钮布局
		LinearLayout.LayoutParams paramLeftButton = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 
				LinearLayout.LayoutParams.FILL_PARENT);
		paramLeftButton.weight = 1;
		paramLeftButton.setMargins(0, 24, 20, 0);
		paramLeftButton.gravity = Gravity.LEFT;
		//右边按钮布局
		LinearLayout.LayoutParams paramRightButton = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 
				LinearLayout.LayoutParams.FILL_PARENT);
		paramRightButton.weight = 1;
		paramRightButton.setMargins(0, 24, 0, 0);
		paramRightButton.gravity = Gravity.RIGHT;
		//注册按钮
		btnRegister = new Button(this.getContext());
		String strRegister = ResourceUtil.parseAssetsString(this.getContext(),
				"register");
		btnRegister.setText(strRegister);
		btnRegister.setTextColor(Color.WHITE);
		btnRegister.setTextSize(NUM_TWENTY);
		btnRegister.setId(BUTTON_REGISTER);
		btnRegister.setHeight((int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 40, this.getContext()));
		try {
			btnRegister.setBackgroundDrawable(ResourceUtil
					.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
							"bocop_button_register_default.9.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnRegister.setOnClickListener(this);
		btnRegister.setOnTouchListener(this);
		//  隐藏注册按钮
		if(Constants.isShowRegister){
			btnRegister.setVisibility(View.VISIBLE);
		}else{
			btnRegister.setVisibility(View.GONE);
		}
		loginView.addView(btnRegister, paramLeftButton);
		//登陆按钮
		btnLogin = new Button(this.getContext());
		String strLogin = ResourceUtil.parseAssetsString(this.getContext(),
				"login");
		btnLogin.setText(strLogin);
		btnLogin.setTextColor(Color.WHITE);
		btnLogin.setTextSize(NUM_TWENTY);
		btnLogin.setId(BUTTON_LOGIN);
		btnLogin.setHeight((int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 40, this.getContext()));
		try {
			btnLogin.setBackgroundDrawable(ResourceUtil
					.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
							"tothetop.png"));//"bocop_button_login_default.9.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnLogin.setOnClickListener(this);
		btnLogin.setOnTouchListener(this);
		loginView.addView(btnLogin, paramRightButton);
		centerView.addView(inputUserPwdView, paramTopView);
		centerView.addView(loginView, paramView);

		view.addView(centerView, paramView);
	}

	private void validation() 
	{
		String name = userView.getEditView().getText().toString();
		String password = pwdView.getEditView().getText().toString();
		mUserId = name;
		String errorMsg = "";
		if (StringUtil.isNullOrEmpty(name)) {
			String errorMsgUser = ResourceUtil.parseAssetsString(
					this.getContext(), "errorMsgUser");
			errorMsg = errorMsgUser;
		}else if (StringUtil.isNullOrEmpty(password)) {
			String errorMsgPwd = ResourceUtil.parseAssetsString(
					this.getContext(), "errorMsgPwd");
			errorMsg = errorMsgPwd;
		}else if (name.trim().length() < 6) {
			String errorMsgMinUserText = ResourceUtil.parseAssetsString(
					this.getContext(), "errorMinUserText");
			errorMsg = errorMsgMinUserText;
		}else if (password.trim().length() < 6) {
			String errorMsgMinPwdText = ResourceUtil.parseAssetsString(
					this.getContext(), "errorMinPwdText");
			errorMsg = errorMsgMinPwdText;
		}
		if (!StringUtil.isNullOrEmpty(errorMsg)) {
			Toast toast = Toast.makeText(getContext(), errorMsg,
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			return;
		} else {
			ContainerInfo.setUser(name);
			ContainerInfo.setPassword(password);
			getRandomNum();
		}
	}

	public void getRandomNum(){
		//RandomCriteria randomCriteria = new RandomCriteria();
		//final RandomRequest requestTask = OAuthService.getRandomResult(mContext, randomCriteria, new RandomListener());
		//OAuthService.getRandomResult(mContext, randomCriteria, new RandomListener());
		LinkedHashMap<String, String> head = BaseService.genSapHeader();
		//LinkedHashMap<String, String> body = new LinkedHashMap();
		LinkedHashMap<String, String> body = null;

		AsyncPara para = new AsyncPara(Constants.urlGetTrdNum,
				ParaType.HTTPMETHOD_POST, head, body, ParaType.GET_ROMDAM,
				new RandomListener());
		Log.i("RandomService", para.toString());
		final OauthContainerRequest requestTask = new OauthContainerRequest();
		requestTask.execute(para);
		
		//final RandomRequest requestTask = RandomService.getRandomResult(mContext, randomCriteria, new RandomListener());
		
		mSpinner = new ProgressDialog(getContext());
		mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mSpinner.setCanceledOnTouchOutside(false);
		String oauthing = ResourceUtil.parseAssetsString(this.getContext(),
				"oauthing");
		mSpinner.setMessage(oauthing);
		mSpinner.setOnCancelListener(new OnCancelListener(){
					@Override
					public void onCancel(DialogInterface dialog) {
						// TODO Auto-generated method stub
						if (requestTask != null && requestTask.getStatus() != AsyncTask.Status.FINISHED)
						{
							Logger.d("Request on cancel------------->");
							requestTask.cancel(true);
						}
					}
                 });
		mSpinner.show();
		
	}

	public void startBocopOauth(String user, String pwd, String random,String randomId,String appID,
			String appSecret, String URL) //throws JSONException 
	{
		BOCOPParameters parameters = new BOCOPParameters();
		
		parameters.add(ParaType.KEY_APPID, appID);
		parameters.add(ParaType.KEY_APPSECRET, appSecret);
		parameters.add(ParaType.KEY_USER, user);
		parameters.add(ParaType.KEY_PWD, pwd);
		//add by cindy 增加ASR登录参数设置
		if(asrFlag){
			parameters.add(ParaType.KEY_RESPONSE_TYPE, "code");
		}
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
		if(asrFlag){
			para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head,
				body, ParaType.OAUTH_CONTAINER, new ResultBOCOPAuthListener());}
		else{
			para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head,
					body, ParaType.OAUTH_APP, new ResultBOCOPAuthListener());
		}
		
		final OauthContainerRequest requestTask = new OauthContainerRequest();
		requestTask.execute(para);
	}

	class ResultBOCOPAuthListener implements ResponseListener {

		private OAuthResponseInfo data;
		private RegisterResponse registerResponse;

		@Override
		public void onComplete(ResponseBean response) {
			// TODO Auto-generated method stub
			if (mSpinner != null && mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
					
			if (response != null && response instanceof OAuthResponseInfo) {
				data = (OAuthResponseInfo) response;

				if (null == OAuthService.getOAuthToken()) {
					OAuthService.setOAuthToken();
				}

				OAuthService.getOAuthToken().setToken(data.getAccess_token());
				OAuthService.getOAuthToken().setExpiresIn(
						String.valueOf(data.getExpires_in()));
				OAuthService.getOAuthToken().setRefreshToken(
						data.getRefresh_token());
				OAuthService.getOAuthToken().setUserId(data.getUser_id());
				
				AccessTokenKeeper.keepAccessToken(BOCOPLoginDialog.mContext,
						OAuthService.getOAuthToken());
				BOCOPOAuthInfo info = new BOCOPOAuthInfo();
				info.setAccess_token(data.getAccess_token());
				info.setRefresh_token(data.getRefresh_token());
				info.setUserId(data.getUser_id());
				mListener.onComplete(info);
			}
				
			if (response != null && response instanceof RegisterResponse) {
				registerResponse = (RegisterResponse) response;

				if (null == OAuthService.getOAuthToken()) {
					OAuthService.setOAuthToken();
				}

				OAuthService.getOAuthToken().setUserId(registerResponse.getUserid());
				AccessTokenKeeper.keepAccessToken(BOCOPLoginDialog.mContext,
						OAuthService.getOAuthToken());
				mListener.onComplete(registerResponse);
			}

			if (BOCOPLoginDialog.this.isShowing()) {
				BOCOPLoginDialog.this.dismiss();
			}
		}

		@Override
		public void onException(Exception e) {
			if (mSpinner != null && mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			Message msg = Message.obtain();
			msg.obj = ResourceUtil.parseAssetsString(
					BOCOPPayApi.getContext(), "ExceptionString");;
			msg.what = OAUTH_EXCEPTION;
			oauthHandle.sendMessage(msg);
			mListener.onException(e);
		}

		@Override
		public void onError(Error e) {
			if (mSpinner != null && mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			if(e instanceof ResponseError)
			{
			Message msg = Message.obtain();
			msg.obj = ((ResponseError) e).getRtnmsg();
			msg.what = OAUTH_ERROR;
			oauthHandle.sendMessage(msg);}
			mListener.onError(e);
		}

		@Override
		public void onCancel() {
			mListener.onCancel();
		}
	}
	
	class RandomListener implements ResponseListener{

		@Override
		public void onComplete(ResponseBean response) {
			RandomResponse randomResponse = null;
			if( (null != response) && (response instanceof RandomResponse)){
				randomResponse = (RandomResponse) response; 
				Message msg = Message.obtain();
				msg.obj = randomResponse;
				msg.what = RANDOM_SUCCESS;
				oauthHandle.sendMessage(msg);
			}		
		}

		@Override
		public void onException(Exception e) {
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			
			Message msg = Message.obtain();
			msg.obj = ResourceUtil.parseAssetsString(
					BOCOPPayApi.getContext(), "ExceptionString");;
			msg.what = OAUTH_EXCEPTION;
			oauthHandle.sendMessage(msg);
			
			mListener.onException(e);
		}

		@Override
		public void onError(Error e) {
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			
			if(e instanceof ResponseError)
			{
			Message msg = Message.obtain();
			msg.obj = ((ResponseError) e).getRtnmsg();
			msg.what = OAUTH_ERROR;
			oauthHandle.sendMessage(msg);}
			
			mListener.onError(e);
		}

		@Override
		public void onCancel() {
			if (mSpinner.isShowing()) {
				mSpinner.dismiss();
			}
			mListener.onCancel();
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case BUTTON_LOGIN:
			try {
				btnLogin.setBackgroundDrawable(ResourceUtil
						.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
								"bocop_button_login_down.9.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			validation();
			break;
		case BUTTON_CANCEL:
			BOCOPLoginDialog.this.dismiss();
			mListener.onCancel();
			break;
		case BUTTON_REGISTER:
			//TODO 注册
			showRegisterWebView();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean isShowing() {
		return super.isShowing();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int eventaction = event.getAction();
		if (v.isClickable()) {
			switch (v.getId()) {
			case BUTTON_LOGIN:
				if (eventaction == MotionEvent.ACTION_DOWN) {
					try {
						v.setBackgroundDrawable(ResourceUtil
								.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
										"bocop_button_login_down.9.png"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (MotionEvent.ACTION_UP == eventaction) {
					try {
						v.setBackgroundDrawable(ResourceUtil
								.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
										"bocop_button_login_default.9.png"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case BUTTON_REGISTER:
				if (eventaction == MotionEvent.ACTION_DOWN) {
					try {
						v.setBackgroundDrawable(ResourceUtil
								.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
										"bocop_button_register_down.9.png"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (MotionEvent.ACTION_UP == eventaction) {
					try {
						v.setBackgroundDrawable(ResourceUtil
								.decodeDrawableFromAsset(BOCOPPayApi.getContext(),
										"bocop_button_register_default.9.png"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case BUTTON_CANCEL:
				if (eventaction == MotionEvent.ACTION_DOWN) {
					ImageView iv=(ImageView)v;
					iv.setImageBitmap(ResourceUtil.getAssertBitmap(this.getContext(),
							"cancel_down.png"));
				} else if (MotionEvent.ACTION_UP == eventaction) {
					ImageView iv=(ImageView)v;
					iv.setImageBitmap(ResourceUtil.getAssertBitmap(this.getContext(),
							"cancel_up.png"));
				}
				break;
			default:
				break;
			}
		}
		return false;
	}
	
	/**
	 * Handler更新主线程UI
	 */
	@SuppressLint("NewApi")
private class OauthHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case RANDOM_SUCCESS:
				RandomResponse randomResponse = null;
				randomResponse = (RandomResponse) msg.obj;   
				
				random_S = Base64.encodeToString(randomResponse.getRandom().getBytes(),Base64.DEFAULT);

				randomId = randomResponse.getRandomid();
				SipResult sipResult = pwdView.getSipResult(random_S);	
				if(null != sipResult){
				startBocopOauth(ContainerInfo.getUser(), sipResult.getEncryptPassword(), 
						sipResult.getEncryptRandomNum(),
						randomId,AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue(), mUrl);
				Logger.d("enpassword-----"+sipResult.getEncryptPassword());
				Logger.d("enrandom-----"+sipResult.getEncryptRandomNum());	}
				else{
					if (mSpinner != null && mSpinner.isShowing()) {
						mSpinner.dismiss();
					}
					
					String strInputError =  ResourceUtil.parseAssetsString(mContext,
							"sipinputerror");
					Toast.makeText(mContext, strInputError, Toast.LENGTH_LONG).show();
				}
				break;
			
			default:
				if (mSpinner != null && mSpinner.isShowing()) {
					mSpinner.dismiss();
				}
				if(null != msg.obj){
				String strError = msg.obj.toString();
				Toast.makeText(mContext, strError, Toast.LENGTH_LONG).show();
				}
				break;
			}
			
		}
	};

	private void showRegisterWebView() {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
		imm.hideSoftInputFromWindow(mContentView.getApplicationWindowToken(), 0);
		}
		if (null != mContentView) {
			mContentView.removeAllViews();
		}
		WindowManager.LayoutParams wlp = getWindow().getAttributes();
		wlp.width = WindowManager.LayoutParams.FILL_PARENT;
		wlp.height = WindowManager.LayoutParams.FILL_PARENT;
		getWindow().setAttributes(wlp);
		RelativeLayout.LayoutParams rpMatch = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		rpMatch.addRule(RelativeLayout.CENTER_IN_PARENT);
		RelativeLayout.LayoutParams rpWrap = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rpWrap.addRule(RelativeLayout.CENTER_IN_PARENT);
		mContentView.addView(mWebView, rpMatch);
		mContentView.addView(mProgressBar, rpWrap);
		mProgressBar.setVisibility(View.VISIBLE);
		Log.i("BOCOPLoginDialog", "loadUrl = " + Constants.urlRegister);
		mWebView.loadUrl(Constants.urlRegister);
	}

	protected void loadWebViewConfig() {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setSaveFormData(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.addJavascriptInterface(new RegisterInterface(), "android");
	}

	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Logger.d("MyWebViewClient", "url: " + url);
			mWebView.loadUrl(url);
			return true;
		}
		@Override
		public void onPageFinished(WebView view, String url) {
			mProgressBar.setVisibility(View.GONE);
			super.onPageFinished(view, url);
		}
	}
	public class RegisterInterface {
		//{"access_token":"?","token_type":"?","refresh_token":"?","expires_in":"?","welcome":"?"}
		//注册成功点击确定
		public void determine() {
			Log.i("RegisterInterface", "determine - state = " + mRegisterState);
			if (mRegisterState == 1) {
				//成功
				if (TextUtils.isEmpty(mToken)) {
					mListener.onException(new Exception("注册成功，但token获取为空"));
					BOCOPLoginDialog.this.dismiss();
					return;
				}
				Gson g = new Gson();
				mRegisterResponse = g.fromJson(mToken, RegisterResponse.class);
				mListener.onComplete(mRegisterResponse);
				BOCOPLoginDialog.this.dismiss();
			} else if (mRegisterState == 3) {
				//注册成功,但是获取token失败
				Logger.d("RegisterInterface getTokenError");
				mListener.onException(new Exception("注册陈功，但token获取失败"));
				BOCOPLoginDialog.this.dismiss();
			} else {
				mListener.onError(new ResponseError());
				BOCOPLoginDialog.this.dismiss();
			}
		}
		/**
		 * 获取注册的数据
		 * @param state 1->success ,2->fail, 3->注册成功 但是获取token失败
		 * @param token 
		 */
//		@JavascriptInterface
		public void getdata(String state,String token) {
			Log.i("RegisterInterface", "getdata - state = " + state + ", token = " + token);
			mRegisterState = Integer.valueOf(state.trim());
			mToken = token;
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mProgressBar.isShown()) {
				mWebView.stopLoading();
			}
			if (!mWebView.isShown()) {
				return super.onKeyUp(keyCode, event);
			}
			if (mRegisterState == 1) {
				dismiss();
			}
			if (mWebView.canGoBack()) {
				mWebView.goBack();
			} else {
				mWebView.stopLoading();
				initLoginView();
			}
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
