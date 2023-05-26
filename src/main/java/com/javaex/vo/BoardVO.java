package com.javaex.vo;

import java.sql.Date;

public class BoardVO {

	private int no; //board no
	private int hit; //조회수
	private int userNo; //user_no
	private Date regDate;
	private String title;
	private String content;
	private String name;
	public BoardVO() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", hit=" + hit + ", userNo=" + userNo + ", regDate=" + regDate + ", title=" + title
				+ ", content=" + content + ", name=" + name + "]";
	} 
}
