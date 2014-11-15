package com.boc.bocop.sdk.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

public class BOCOPEditTextView extends EditText {

	@Override
	public void setLongClickable(boolean longClickable) {
		super.setLongClickable(longClickable);
	}

	@Override
	public boolean onTextContextMenuItem(int id) {
		String strPaste = ResourceUtil.parseAssetsString(this.getContext(),
				"pasteAlert");
		Toast toast = Toast.makeText(this.getContext(), strPaste, Toast.LENGTH_SHORT); 
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show(); 
		
		return false;
	}

	public BOCOPEditTextView(Context context) {
		super(context);
	}

}
