package com.douzone.mysite.vo;

public class GuestbookVo {
	private Long no;
	private String name;
	private String password;
	private String message;
	private String regDate;
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", text=" + message + ", regDate="
				+ regDate + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getText() {
		return message;
	}
	public void setText(String text) {
		this.message = text;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String now) {
		this.regDate = now;
	}
}
