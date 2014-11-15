package com.boc.bocop.sdk.http;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import com.google.gson.Gson;



/**
 * 提供类一些bean操作的方法
 * @author tongyapeng
 *
 */
public final class BeanUtils  {
	private static final Gson JSON = new Gson();	
	/**
	 * 把json字符串，转为指定的对象，转换失败则抛出com.google.gson.JsonSyntaxException异常
	 * @param response json字符串
	 * @param clazz 转换的类型
	 * @return
	 */
	public final static <T> T jsonToObject(String response,Class<T> clazz) {		
		return (T)JSON.fromJson(response, clazz);
	}
	public final static <T> T xmlToObject(String response,Class<T> clazz) {		
		return null;
	}
	/**
	 * 把封装了上传参数的obj，转化为HashMap
	 * key为obj字段的名字，value为obj字段的值，
	 * obj中应该全部使用String类型转化中会忽略掉所有非String类型的字段
	 * @param obj  封装了上传参数
	 * @return 转化后的HashMap
	 */
	public static LinkedHashMap<String, String> criteriaToHashMap(Object obj) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		try {
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.getType() == String.class) 
					dataMap.put(field.getName(), (String)field.get(obj));
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return dataMap;
	}	
}
