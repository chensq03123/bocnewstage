package com.boc.bocop.sdk.util;

import java.net.URLEncoder;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.util.Log;

public class Utility {
	
	public static String encodeUrl(BOCOPParameters parameters) {
		if (parameters == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (int loc = 0; loc < parameters.size(); loc++) {
			if (first){
			    first = false;
			}
			else{
			    sb.append("&");
			}
			String keyParam=parameters.getKey(loc);
			String valueParam=parameters.getValue(keyParam);
			if(valueParam==null){
			    Log.i("encodeUrl", "key:"+keyParam+" 's value is null");
			}
			else{
			    sb.append(URLEncoder.encode(parameters.getKey(loc)) + "="
                        + URLEncoder.encode(parameters.getValue(loc)));
			}
			
		}
		return sb.toString();
	}
	
	public static void showAlert(Context context, String title, String text) {
		Builder alertBuilder = new Builder(context);
		alertBuilder.setTitle(title);
		alertBuilder.setMessage(text);
		alertBuilder.create().show();
	}
}
