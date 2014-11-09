package com.hustunique.bocp.Utils;

/**
 * 功能：json数据的解析工具类
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class JsonUtils {
	
	public JsonUtils() {
	}
	
	/**
	 * 
	 * @param map 要封装的json数据
	 * @return 
	 */
	public static String convertToJson(Map<String,Object> map){
		JSONObject params = new JSONObject(map);
		return params.toString();
	}
	
	/**
	 * 使用JSON工具把数据转换成json对象
	 * @param value 是对解析的集合的类型
	 */
	public static String createJsonString(Object value) {
		String str = JSON.toJSONString(value);
		return str;
	}

	/**
	 * 对单个javabean进行解析
	 * @param <T>
	 * @param json 要解析的json字符�?
	 * @param cls 
	 * @return
	 */
	public static <T>T getObject(String json,Class<T> cls){
		T t = null;
		try {
			t = JSON.parseObject(json,cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 对list类型进行解析
	 * @param <T>
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> List<T> getListObject(String json,Class<T> cls){
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * 对MapString类型数据进行解析
	 * @param json
	 * @return
	 */
	public static Map<String, String> getMapStr(String json){
		Map<String, String> mapStr = new HashMap<String, String>();
		try {
			mapStr = JSON.parseObject(json, new TypeReference<Map<String, String>>(){});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mapStr;
	}
	
	/**
	 * 对MapObject类型数据进行解析
	 * @param json
	 * @return
	 */
	public static Map<String, Object> getMapObj(String json){
		Map<String, Object> mapStr = new HashMap<String, Object>();
		try {
			mapStr = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mapStr;
	}
	
	/**
	 * 对listmap类型进行解析
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> getListMap(String json){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = JSON.parseObject(json,new TypeReference<List<Map<String, Object>>>(){});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
