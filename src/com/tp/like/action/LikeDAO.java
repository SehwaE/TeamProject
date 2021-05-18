package com.tp.like.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tp.bbs.bean.LikeBean;

public class LikeDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	//////////////////////////////////////////////////////////
	// 디비연결
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/tpDB");
		con = ds.getConnection();

		System.out.println("DAO : 디비연결 ::::::: " + con);

		return con;
	}

	//////////////////////////////////////////////////////////
	// 자원해제
	public void closeDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//////////////////////////////////////////////////////////
	// CreatlikeID(lb)
	public int CreatlikeID(LikeBean lb) {
		int likeCheck = 0; // 0 : NO 좋아요 /// 1: 좋아요 상태
		int likeID = 0;

		try {
			con = getCon();

			sql = "select * from tp_like where userID=? && bbsID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, lb.getUserID());
			pstmt.setInt(2, lb.getBbsID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// likeID가 있는데 좋아요 했다가 다시 취소했다가 다시 좋아요 누를때의 경우
				int Check = rs.getInt("likeCount");
				System.out.println("DAO에서 Check값 확인 :" + Check);
				// Check는 likeID가 있는지 확인하는것
				if (Check == 1) {

					sql = "update tp_like set likeCount=0 where userID=? && bbsID=?";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, rs.getString("userID"));
					pstmt.setInt(2, rs.getInt("bbsID"));

					pstmt.executeUpdate();

					likeCheck = 0;

					System.out.println("LikeDAO: likeCount 0으로 수정완료 ");
				} else {// likeID는 있는데 그 값이 1이 아닌경우
					sql = "update tp_like set likeCount=1 where userID=? && bbsID=?";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, rs.getString("userID"));
					pstmt.setInt(2, rs.getInt("bbsID"));

					pstmt.executeUpdate();

					likeCheck = 1;

					System.out.println("LikeDAO: likeCount 1으로 수정완료 ");
				}

			} else {
				// likeID가 없는 상태

				sql = "select max(likeID) from tp_like";

				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					likeID = rs.getInt(1) + 1;
				} else {
					likeID = 1;
				}

				sql = "insert into tp_like values(?,?,?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, likeID);
				pstmt.setString(2, lb.getUserID());
				pstmt.setInt(3, lb.getBbsID());
				pstmt.setInt(4, 1);

				pstmt.executeUpdate();

				likeCheck = 1;
				System.out.println("LikeDAO : createLikeID 생성완료 ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return likeCheck;
	}
	// CreatlikeID(lb)
	//////////////////////////////////////////////////////////
	// totalLike(lb) 좋아요버튼 누를시 lb에따른 좋아요수
	public int totalLike(LikeBean lb) {
		int result = 0;

		try {
			con = getCon();
			sql = "select sum(likeCount) from tp_like where bbsID=?;";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, lb.getBbsID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println("DAO: totalLike 계산완료" + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// likeResult(bbsID)
	//////////////////////////////////////////////////////////
	// likeCheck(userID,bbsID) 자신이 좋아요누른 게시글 들어갈때 하트유지를 위한
	public int likeCheck(String userID, int bbsID) {
		int likeCheck = 0;
		System.out.println("LikeDAO-likeCheck ::::::::::::likeCheck 1: 좋아요 누른 상태 , 0: 좋아요 안된상태 /// 결과값 :" + likeCheck);

		try {
			con = getCon();
			sql = "select likeCount from tp_like where userID=? && bbsID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userID);
			pstmt.setInt(2, bbsID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				likeCheck = rs.getInt(1);
			}
			System.out.println("DAO에서 likeCheck 확인" + likeCheck);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return likeCheck;
	}
	// likeCheck(userID)
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////

}
