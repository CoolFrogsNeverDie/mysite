package com.javaex.vo;

import java.sql.Date;

public class GuestbookVO {
	
	private int num;
	private String author;
	private Date regDate;
	private String password;
	private String content;
	public GuestbookVO() {
		super();
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "GuestbookVO [num=" + num + ", author=" + author + ", regDate=" + regDate + ", password=" + password
				+ ", content=" + content + "]";
	}
	
	
	
	
}
