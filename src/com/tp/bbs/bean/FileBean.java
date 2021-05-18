package com.tp.bbs.bean;

import java.sql.Timestamp;

public class FileBean {
	private int fileID;
	private String fileName;
	private String fileRealName;
	private int bbsID;
	private String userID;
	private Timestamp file_date;

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Timestamp getFile_date() {
		return file_date;
	}

	public void setFile_date(Timestamp file_date) {
		this.file_date = file_date;
	}

	@Override
	public String toString() {
		return "FileBean [fileID=" + fileID + ", fileName=" + fileName + ", fileRealName=" + fileRealName + ", bbsID="
				+ bbsID + ", userID=" + userID + ", file_date=" + file_date + "]";
	}

	
}
