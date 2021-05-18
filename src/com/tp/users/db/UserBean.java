package com.tp.users.db;

import java.sql.Timestamp;

public class UserBean {
	private String userID;
	private String userPass;
	private String userName;
	private String userEmail;
	private String userAddr;
	private String userPhone;
	private String userProfile;
	private String userNickName;
	private Timestamp reg_date;
	private int isAdmin;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "UserBean [userID=" + userID + ", userPass=" + userPass + ", userName=" + userName + ", userEmail="
				+ userEmail + ", userAddr=" + userAddr + ", userPhone=" + userPhone + ", userProfile=" + userProfile
				+ ", userNickName=" + userNickName + ", reg_date=" + reg_date + ", isAdmin=" + isAdmin + "]";
	}

}
