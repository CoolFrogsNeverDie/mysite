package com.javaex.vo;

public class UploadFileVO {

	private int no;
	private int userNum;
	private String userName;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	
	public UploadFileVO() {
		super();
	}
	
	public UploadFileVO(int userNum, String content, String filePath, String orgName, String saveName,
			long fileSize) {
		super();
		this.userNum = userNum;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "UploadFileVO [no=" + no + ", userNum=" + userNum + ", userName=" + userName + ", content=" + content
				+ ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize="
				+ fileSize + "]";
	}

	
	
}
