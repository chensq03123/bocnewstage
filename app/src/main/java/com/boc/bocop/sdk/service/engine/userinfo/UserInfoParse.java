package com.boc.bocop.sdk.service.engine.userinfo;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.boc.bocop.sdk.api.bean.useinfo.UserInfo;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * @author fww5205 用户资料解析类
 */
public class UserInfoParse {

	public static UserInfoSearch parseUserInfoSearch(String response)
			throws JSONException {

		// 解析Json数据
		JSONObject jsonObject = new JSONObject(response);

		// 生成返回数据
		UserInfoSearch data = new UserInfoSearch();

		data.setPageno(Long.parseLong(jsonObject.getString("pageno")));
		data.setRecord_count(Integer.parseInt(jsonObject.getString("rcdcnt")));

		if (Integer.parseInt(jsonObject.getString("rcdcnt")) > 0) {
			Type listType = new TypeToken<LinkedList<UserInfo>>(){}.getType();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.create(); 
			List<UserInfo> users = new ArrayList<UserInfo>();
			LinkedList<UserInfo> jsonlists = gson.fromJson(jsonObject.getString("saplist"), listType);
			//LinkedList<UserInfo> jsonlists = gson.fromJson(jsonObject.getString("saplist"), LinkedList<UserInfo>);
			for (Iterator<UserInfo> iterator = jsonlists.iterator(); iterator.hasNext();) {
				UserInfo user = (UserInfo) iterator.next();
				users.add(user);
			}

			data.setUsers(users);
		}
		return data;
	}
}
