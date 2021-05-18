package com.tp.bbs.bean;

import java.sql.Timestamp;

public class CmtBean {
	private int cmtID;
	private String userID;
	private String cmt_content;
	private int bbsID;
	private int cmt_ref;
	private int cmt_seq;
	private int cmt_lev;
	private int cmt_available;
	private int bbs_category;
	private Timestamp cmt_date;
	private String ip;

	public int getCmtID() {
		return cmtID;
	}

	public void setCmtID(int cmtID) {
		this.cmtID = cmtID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public int getCmt_ref() {
		return cmt_ref;
	}

	public void setCmt_ref(int cmt_ref) {
		this.cmt_ref = cmt_ref;
	}

	public int getCmt_seq() {
		return cmt_seq;
	}

	public void setCmt_seq(int cmt_seq) {
		this.cmt_seq = cmt_seq;
	}

	public int getCmt_lev() {
		return cmt_lev;
	}

	public void setCmt_lev(int cmt_lev) {
		this.cmt_lev = cmt_lev;
	}

	public int getCmt_available() {
		return cmt_available;
	}

	public void setCmt_available(int cmt_available) {
		this.cmt_available = cmt_available;
	}

	public int getBbs_category() {
		return bbs_category;
	}

	public void setBbs_category(int bbs_category) {
		this.bbs_category = bbs_category;
	}

	public Timestamp getCmt_date() {
		return cmt_date;
	}

	public void setCmt_date(Timestamp cmt_date) {
		this.cmt_date = cmt_date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "CmtBean [cmtID=" + cmtID + ", userID=" + userID + ", cmt_content=" + cmt_content + ", bbsID=" + bbsID
				+ ", cmt_ref=" + cmt_ref + ", cmt_seq=" + cmt_seq + ", cmt_lev=" + cmt_lev + ", cmt_available="
				+ cmt_available + ", bbs_category=" + bbs_category + ", cmt_date=" + cmt_date + ", ip=" + ip + "]";
	}

}
