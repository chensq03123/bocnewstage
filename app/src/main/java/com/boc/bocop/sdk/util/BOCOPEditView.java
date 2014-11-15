package com.boc.bocop.sdk.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.method.NumberKeyListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BOCOPEditView extends LinearLayout {

	private int marginsLogo = 10;
	private Bitmap mBitmap = null;
	private String edit = "";
	private int length = 0;

	private ImageView userLogo = null;
	private View userLine = null;
	private BOCOPEditTextView editView = null;

	public BOCOPEditView(Context context) {
		super(context);
		InitBocopEditView();
	}

	public BOCOPEditView(Context context, Bitmap bimap, String edit, int length) {
		super(context);
		this.mBitmap = bimap;
		this.edit = edit;
		this.length = length;
		InitBocopEditView();
	}

	private void InitBocopEditView() {
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		LayoutParams paramsLogo=new LayoutParams((int) 
				DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 24, this.getContext())
				, (int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 24, this.getContext()));
		paramsLogo.setMargins(marginsLogo, marginsLogo, marginsLogo, marginsLogo);
		paramsLogo.gravity=Gravity.LEFT|Gravity.CENTER_VERTICAL;
		userLogo = new ImageView(getContext());
		userLogo.setImageBitmap(mBitmap);
		
		LayoutParams paramsLine=new LayoutParams(
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 1, this.getContext()), 
				(int) DimensionPixelUtil.getDimensionPixelSize(
						TypedValue.COMPLEX_UNIT_DIP, 17, this.getContext()));
		paramsLine.gravity=Gravity.CENTER_VERTICAL;
		paramsLine.setMargins(marginsLogo, marginsLogo, 0, marginsLogo);
		userLine=new View(getContext());
		userLine.setBackgroundColor(Color.argb(255, 200, 200, 200));

		editView = new BOCOPEditTextView(this.getContext());
		editView.setTextSize(14);
		if (0 == length) {
			editView.setWidth(200);
		} else {
			editView.setWidth(length);
		}

		editView.setLongClickable(false);
		editView.setSingleLine();

		// 用户名6-20位 2013/06/18 feiweiwei
		InputFilter[] filters = { new InputFilter.LengthFilter(100) };
		editView.setFilters(filters);

		editView.setKeyListener(new NumberKeyListener() {
			@Override
			protected char[] getAcceptedChars() {
				return new char[] { '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
						'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
						'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
						'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', 
						'@', '.', '-'};
			}

			@Override
			public int getInputType() {
				// TODO Auto-generated method stub
				return android.text.InputType.TYPE_CLASS_TEXT;
			}
		});

		editView.setHintTextColor(Color.GRAY);
		editView.setHint(this.edit);
		editView.setBackgroundDrawable(null);

		editView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				return false;
			}
		});

		LayoutParams lp = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		lp.gravity=Gravity.CENTER_VERTICAL;
		lp.setMargins(marginsLogo, marginsLogo, marginsLogo, marginsLogo);
		editView.setLayoutParams(lp);
		this.addView(userLogo, paramsLogo);
		this.addView(userLine, paramsLine);
		this.addView(editView);
	}

	public EditText getEditView() {
		return editView;
	}

	public void setHintTextSize(float userTextSize) {
		editView.setTextSize(TypedValue.COMPLEX_UNIT_PX, userTextSize);
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
