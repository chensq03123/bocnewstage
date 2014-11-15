package com.boc.bocop.sdk.util;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cfca.mobile.exception.CodeException;
import cfca.mobile.sip.CFCASipDelegator;
import cfca.mobile.sip.SipBox;
import cfca.mobile.sip.SipResult;

/**
 * @author feiweiwei
 * 继承CFCA安全登录控件，对安全控件做了再封装
 */
public class BOCOPEditPwdView extends LinearLayout implements CFCASipDelegator{

	private int margins=10;
	private Bitmap mBitmap = null;
	private String edit = "";
	private int minLength = 0;
	private int maxLength = 16;

	private ViewGroup layoutLogin = null;
	private int windowHeight;

	// CFCA sipbox
	private String passwordRegularExpression = "[a-zA-Z0-9]*";
	private String randomKey_S = "qGIPr75Kz7KUL73ZyKQNlw==";

	private static SipBox editView = null;
	private Context mContext = null;
	public BOCOPEditPwdView(Context activity) {
		super(activity.getApplicationContext());
		mContext = activity;
		InitBocopEditPwdView(activity);
	}

	public BOCOPEditPwdView(Context activity, Bitmap bitmap, String edit,
			int minLength, int maxLength) {
		super(activity.getApplicationContext());
		mContext = activity;
		this.mBitmap = bitmap;
		this.edit = edit;
		this.minLength = minLength;
		this.maxLength = maxLength;
		InitBocopEditPwdView(activity);
	}

	private void InitBocopEditPwdView(Context activity) {
		this.setOrientation(LinearLayout.HORIZONTAL);

		LayoutParams paramsPass=new LayoutParams(
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 24, this.getContext()), 
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 24, this.getContext()));
		paramsPass.gravity=Gravity.LEFT|Gravity.CENTER_VERTICAL;
		paramsPass.setMargins(margins, margins, margins, margins);
		ImageView passLogo=new ImageView(getContext());
		passLogo.setImageBitmap(mBitmap);
		
		LayoutParams paramsLine=new LayoutParams(
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 1, this.getContext()), 
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 17, this.getContext()));
		paramsLine.setMargins(margins, margins, 0, margins);
		paramsLine.gravity=Gravity.CENTER_VERTICAL;
		View passLine=new View(getContext());
		passLine.setBackgroundColor(Color.argb(255, 200, 200, 200));

		editView = new SipBox((Activity) activity);
		editView.setTextSize(14);
		editView.setLongClickable(false);
		editView.setSingleLine();

		// CFCA sipbox初始化
		editView.setKeyBoardType(0);
		editView.setSipDelegator(this);
		// editView.setRandomKey_S(randomKey_S);
		// sipBox.setBackgroundColor(Color.GREEN);
		editView.setTextColor(getResources().getColor(android.R.color.black));
		editView.setPasswordMinLength(minLength);
		editView.setPasswordMaxLength(maxLength);
		editView.setOutputValueType(2);
		editView.setPasswordRegularExpression(passwordRegularExpression);
		editView.hideSecurityKeyBoard();
		editView.setHint(edit);
		editView.setBackgroundColor(0);

		LayoutParams lp = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		lp.gravity=Gravity.CENTER_VERTICAL;
		lp.setMargins(margins, margins, margins, margins);
//		editView.setLayoutParams(lp);

		this.addView(passLogo, paramsPass);
		this.addView(passLine, paramsLine);
		this.addView(editView, lp);
	}

	public EditText getEditView() {
		return editView;
	}

	@Override
	public void afterClickDown(SipBox arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterKeyboardHidden(SipBox arg0, int arg1) {
		// TODO Auto-generated method stub

	}

//		@Override
//		public void beforeKeyboardShow(SipBox arg0, int arg1) {
//			// TODO Auto-generated method stub
//			
//		}
		
//		private int getWindowHeight() {
//			DisplayMetrics metrics = new DisplayMetrics();
//			Display display = activity.getWindowManager().getDefaultDisplay();
//			display.getMetrics(metrics);
//			return activity.getWindowManager().getDefaultDisplay().getHeight();
//		}
//		
	@Override
	public void beforeKeyboardShow(SipBox sip, int keyboardHeight) {

		int[] location = new int[2];
		sip.getLocationOnScreen(location);
		int y = location[1];

		int bottom = windowHeight - y - sip.getHeight();
		if (bottom < keyboardHeight) {
			layoutLogin.scrollTo(0, keyboardHeight - bottom);
		}
	}
//
//		@Override
//		public void afterKeyboardHidden(SipBox sip, int keyboardHeight) {
//			relative.scrollTo(0, 0);
//		}
//		
//		public void afterClickDown(SipBox sip){
//			
//			editView.requestFocus();
//		}

	public String getRandomKey_S() {
		return randomKey_S;
	}

	public void setRandomKey_S(String randomKey_S) {
		this.randomKey_S = randomKey_S;
	}

	public SipBox getSipBox() {
		return editView;
	}

	public static SipResult getSipResult(String randomKey_S) {
		SipResult sip = null;
		editView.setRandomKey_S(randomKey_S);
		try {
			sip = editView.getValue();
		} catch (CodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return sip;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public void setLayoutLogin(ViewGroup layout) {
		this.layoutLogin = layout;
	}

	public void setHintTextSize(float userTextSize) {
		/*Logger.d("userTextSize pwd---->" + userTextSize);*/
		editView.setTextSize(TypedValue.COMPLEX_UNIT_PX,userTextSize);
	}

	public void setHint(String userHintText) {
		editView.setHint(userHintText);
	}

	public void setHintTextColor(int userHintTextColor) {
		editView.setHintTextColor(userHintTextColor);
	}

	public void setEditWidth(int width) {
		editView.setWidth(width);
	}
}
