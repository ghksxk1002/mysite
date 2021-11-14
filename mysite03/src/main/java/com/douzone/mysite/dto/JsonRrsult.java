package com.douzone.mysite.dto;

public class JsonRrsult {
	private String result;/*"success"or "fail"*/
	private Object data;/*if success,set*/
	private String message;
	
	// 기본 생성자를 프라이빗으로 만들어서 참조를 막는다
	private JsonRrsult() {}
	
	private JsonRrsult(Object data) {
		result = "success";
		this.data = data;
		message = null;
	}
	

	private JsonRrsult(String message) {
		result = "fail";
		data = null;
		this.message = message;
	}
	
	public static JsonRrsult success(Object data) {
		return new JsonRrsult(data);
	}
	public static JsonRrsult fail(String message) {
		return new JsonRrsult(message);
	}
	
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
}
