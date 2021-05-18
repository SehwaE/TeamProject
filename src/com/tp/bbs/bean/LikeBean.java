package com.tp.bbs.bean;

public class LikeBean {
	private int likeID;
	private String userID;
	private int bbsID;
	private int bbs_category;
	private int cmtID;
	private int likeCount;

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public int getBbs_category() {
		return bbs_category;
	}

	public void setBbs_category(int bbs_category) {
		this.bbs_category = bbs_category;
	}

	public int getCmtID() {
		return cmtID;
	}

	public void setCmtID(int cmtID) {
		this.cmtID = cmtID;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int like) {
		this.likeCount = like;
	}

	@Override
	public String toString() {
		return "LikeBean [likeID=" + likeID + ", userID=" + userID + ", bbsID=" + bbsID + ", bbs_category="
				+ bbs_category + ", cmtID=" + cmtID + ", likeCount=" + likeCount + "]";
	}

}
