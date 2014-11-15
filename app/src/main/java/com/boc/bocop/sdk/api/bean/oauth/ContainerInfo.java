package com.boc.bocop.sdk.api.bean.oauth;

public class ContainerInfo {
	private static String containerKeyValue;
	private static String containerSecretValue;
	private static String user;
	private static String password;
	private static String sessionCookie;

	public ContainerInfo(String containerKeyValue, String containerSecretValue) {
		ContainerInfo.containerKeyValue = containerKeyValue;
		ContainerInfo.containerSecretValue = containerSecretValue;
	}

	public ContainerInfo() {
		super();
	}

	public static String getContainerKeyValue() {
		return ContainerInfo.containerKeyValue;
	}

	public static void setContainerKeyValue(String containerKeyValue) {
		ContainerInfo.containerKeyValue = containerKeyValue;
	}

	public static String getContainerSecretValue() {
		return ContainerInfo.containerSecretValue;
	}

	public static void setContainerSecretValue(String containerSecretValue) {
		ContainerInfo.containerSecretValue = containerSecretValue;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		ContainerInfo.user = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ContainerInfo.password = password;
	}

	public static String getSessionCookie() {
		return sessionCookie;
	}

	public static void setSessionCookie(String sessionCookie) {
		ContainerInfo.sessionCookie = sessionCookie;
	}
	
	
}
