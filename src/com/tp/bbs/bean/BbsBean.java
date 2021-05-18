package com.tp.bbs.bean;

import java.sql.Timestamp;

public class BbsBean {
	private int bbsID;
	private int bbs_ref;
	private int bbs_seq;
	private int bbs_lev;
	private int bbs_available;
	private int bbs_category;
	private String bbs_title;
	private String bbs_content;
	private int rating;
	private String start_month;
	private String end_month;
	private int bbs_readcount;
	private Timestamp bbs_date;
	private String userID;
	private String userNickName;
	private int fileID;
	private int best;
	private int isAdmin;
	private int likeID;
	private String ip;

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public int getBbs_ref() {
		return bbs_ref;
	}

	public void setBbs_ref(int bbs_ref) {
		this.bbs_ref = bbs_ref;
	}

	public int getBbs_seq() {
		return bbs_seq;
	}

	public void setBbs_seq(int bbs_seq) {
		this.bbs_seq = bbs_seq;
	}

	public int getBbs_lev() {
		return bbs_lev;
	}

	public void setBbs_lev(int bbs_lev) {
		this.bbs_lev = bbs_lev;
	}

	public int getBbs_available() {
		return bbs_available;
	}

	public void setBbs_available(int bbs_available) {
		this.bbs_available = bbs_available;
	}

	public int getBbs_category() {
		return bbs_category;
	}

	public void setBbs_category(int bbs_category) {
		this.bbs_category = bbs_category;
	}

	public String getBbs_title() {
		return bbs_title;
	}

	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}

	public String getBbs_content() {
		return bbs_content;
	}

	public void setBbs_content(String bbs_content) {
		this.bbs_content = bbs_content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getStart_month() {
		return start_month;
	}

	public void setStart_month(String start_month) {
		this.start_month = start_month;
	}

	public String getEnd_month() {
		return end_month;
	}

	public void setEnd_month(String end_month) {
		this.end_month = end_month;
	}

	public int getBbs_readcount() {
		return bbs_readcount;
	}

	public void setBbs_readcount(int bbs_readcount) {
		this.bbs_readcount = bbs_readcount;
	}

	public Timestamp getBbs_date() {
		return bbs_date;
	}

	public void setBbs_date(Timestamp bbs_date) {
		this.bbs_date = bbs_date;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public int getBest() {
		return best;
	}

	public void setBest(int best) {
		this.best = best;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "BbsBean [bbsID=" + bbsID + ", bbs_ref=" + bbs_ref + ", bbs_seq=" + bbs_seq + ", bbs_lev=" + bbs_lev
				+ ", bbs_available=" + bbs_available + ", bbs_category=" + bbs_category + ", bbs_title=" + bbs_title
				+ ", bbs_content=" + bbs_content + ", rating=" + rating + ", start_month=" + start_month
				+ ", end_month=" + end_month + ", bbs_readcount=" + bbs_readcount + ", bbs_date=" + bbs_date
				+ ", userID=" + userID + ", userNickName=" + userNickName + ", fileID=" + fileID + ", best=" + best
				+ ", isAdmin=" + isAdmin + ", likeID=" + likeID + ", ip=" + ip + "]";
	}

}
