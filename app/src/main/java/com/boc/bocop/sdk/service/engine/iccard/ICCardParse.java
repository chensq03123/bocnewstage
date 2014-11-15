package com.boc.bocop.sdk.service.engine.iccard;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.iccard.ICardBalInfo;
import com.boc.bocop.sdk.api.bean.iccard.ICardTransfer;
import com.boc.bocop.sdk.api.bean.iccard.ICardTransferDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class ICCardParse {
	public static ICardBalInfo parseICardBalInfo(String response)
			throws JSONException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create(); 
		ICardBalInfo data = gson.fromJson(response, ICardBalInfo.class);
		return data;
	}

	public static ICardTransferDetail parseICardTransferDetail(String response)
			throws JSONException {

		JSONObject jsonObject = new JSONObject(response);

		ICardTransferDetail data = new ICardTransferDetail();
		data.setPageno(Integer.parseInt(jsonObject.getString("pageno")));
		data.setRecunt(Integer.parseInt(jsonObject.getString("recunt")));

		if (Integer.parseInt(jsonObject.getString("recunt")) > 0) {
			
			Type listType = new TypeToken<LinkedList<ICardTransfer>>() {
			}.getType();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.create(); 

			ArrayList<ICardTransfer> cardList = new ArrayList<ICardTransfer>();

			LinkedList<ICardTransfer> jsonlists = gson.fromJson(
					jsonObject.getString("saplist"), listType);
			for (Iterator<ICardTransfer> iterator = jsonlists.iterator(); iterator.hasNext();) {
				ICardTransfer trans = (ICardTransfer) iterator.next();
				cardList.add(trans);
			}
			 
			data.setCardList(cardList);
		}
		return data;
	}
}
