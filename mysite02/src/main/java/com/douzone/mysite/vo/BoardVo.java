package com.douzone.mysite.vo;

public class BoardVo {
	private Long no;
	private Long userNu;
	private String userName;
	private String title;
	private String content;
	private Long hit;
	private String regDate;
	private Long groupNu;
	private Long orderNu;
	private Long depthNu;

	public Long getUserNu() {
		return userNu;
	}
	public void setUserNu(Long userNu) {
		this.userNu = userNu;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getGroupNu() {
		return groupNu;
	}
	public void setGroupNu(Long groupNu) {
		this.groupNu = groupNu;
	}
	public Long getOrderNu() {
		return orderNu;
	}
	public void setOrderNu(Long orderNu) {
		this.orderNu = orderNu;
	}
	public Long getDepthNu() {
		return depthNu;
	}
	public void setDepthNu(Long depthNu) {
		this.depthNu = depthNu;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", userNu=" + userNu + ", userName=" + userName + ", title=" + title
				+ ", content=" + content + ", hit=" + hit + ", regDate=" + regDate + ", groupNu=" + groupNu
				+ ", orderNu=" + orderNu + ", depthNu=" + depthNu + "]";
	}

	
}
