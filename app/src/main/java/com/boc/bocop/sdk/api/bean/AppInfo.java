package com.boc.bocop.sdk.api.bean;

public class AppInfo {

	private  static String appKeyValue;
	private  static String appSecretValue;

	public AppInfo(String appKeyValue, String appSecretValue) {
		this.appKeyValue = appKeyValue;
		this.appSecretValue = appSecretValue;
	}

	public AppInfo() {
		super();
	}

	public static String getAppKeyValue() {
		return appKeyValue;
	}

	public static void setAppKeyValue(String appKeyValue) {
		AppInfo.appKeyValue = appKeyValue;
	}

	public static String getAppSecretValue() {
		return appSecretValue;
	}

	public static void setAppSecretValue(String appSecretValue) {
		AppInfo.appSecretValue = appSecretValue;
	}

}
