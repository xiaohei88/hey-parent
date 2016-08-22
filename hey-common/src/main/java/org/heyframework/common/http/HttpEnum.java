package org.heyframework.common.http;

public enum HttpEnum {

	CONTENT_TYPE("Content-type"),

	CONTENT_TYPE_JSON_UTF_8("application/json; charset=utf-8"),

	ACCEPT("Accept"),

	ACCEPT_JSON("application/json");

	public String name = "";

	HttpEnum(String name) {
		this.name = name;
	}
}
