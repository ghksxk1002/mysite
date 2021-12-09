package com.douzone.security.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	private int no;
	
	@NotEmpty
	private String userid;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	@Length(min=2 , max=10)
	private String password;
	
	@NotEmpty
	@Email
	private String useremail;
	
	private String key;
	private String regDate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", userid=" + userid + ", username=" + username + ", password=" + password
				+ ", useremail=" + useremail + ", key=" + key + ", regDate=" + regDate + "]";
	}
	
	
	
}
