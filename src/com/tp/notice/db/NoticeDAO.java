package com.tp.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tp.bbs.bean.BbsBean;

public class NoticeDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/tpDB");
		con = ds.getConnection();

		System.out.println("BbsDAO : 디비연결 -> " + con);

		return con;
	}

	// 자원해제
	public void closeDB() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 게시판 글 입력 / insertBbs
	public void insertBbs(BbsBean bbs) {
		int bbsID = 0;
		int fileID = 0;
		try {
			con = getCon();
			sql = "select max(bbsID) from tp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 있으면 + 1, 없으면 1
			if (rs.next()) {
				bbsID = rs.getInt(1) + 1;
			} else {
				bbsID = 1;
			}

			String sql2 = "select max(fileID) from tp_files";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileID = rs.getInt(1);
			}
			sql = "insert into tp_bbs " 
					+ "values(?,?,?,?,?,?,?,?,?,?, " 
					+ "?,?,now(),?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bbsID);
			pstmt.setInt(2, bbsID); // bbs_ref
			pstmt.setInt(3, 0); // bbs_seq
			pstmt.setInt(4, 0); // bbs_lev
			pstmt.setInt(5, 1); // bbs_available
			pstmt.setInt(6, bbs.getBbs_category());
			pstmt.setString(7, bbs.getBbs_title());
			pstmt.setString(8, bbs.getBbs_content());
			pstmt.setInt(9, bbs.getRating());
			pstmt.setString(10, bbs.getStart_month());
			pstmt.setString(11, bbs.getEnd_month());
			pstmt.setInt(12, 0); // readcount
			pstmt.setString(13, bbs.getUserID());
			pstmt.setString(14, bbs.getUserNickName());
			pstmt.setInt(15, fileID);
			pstmt.setInt(16, bbs.getBest()); // best
			pstmt.setInt(17, 1); // isAdmin
			pstmt.setInt(18, 0); // likeID
			pstmt.setString(19, bbs.getIp());

			pstmt.executeUpdate();

			System.out.println("BbsDAO : 게시글 등록 완료");
		} catch (Exception e) {
			System.out.println("BbsDAO : insertBbs 메소드 내부에서 오류 : " + e);
		} finally {
			closeDB();
		}
	}

	// 게시판 리스트 출력 / getBbsList
	public List getBbsList(int startRow, int rowSize) {
		List bbsList = new ArrayList();
		try {
			con = getCon();
			sql = "select * from tp_bbs where bbs_available=1 and bbs_category=1 order by bbsID desc limit ?,?";
			// limit offset(시작점), row_count(출력수)
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rowSize);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				BbsBean bbs = new BbsBean();

				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setBbs_title(rs.getString("bbs_title"));
				bbs.setBbs_content(rs.getString("bbs_content"));
				bbs.setBbs_category(rs.getInt("bbs_category"));
				bbs.setBbs_date(rs.getTimestamp("bbs_date"));
				bbs.setUserID(rs.getString("userID"));
				bbs.setBbs_available(rs.getInt("bbs_available"));
				bbs.setBbs_readcount(rs.getInt("bbs_readcount"));
				bbs.setUserNickName(rs.getString("userNickName"));
				bbs.setFileID(rs.getInt("fileID"));
				bbs.setLikeID(rs.getInt("likeID"));

				/*
				 * bbs.setBbsID(rs.getInt("bbsID"));
				 * bbs.setBbs_ref(rs.getInt("bbs_ref"));
				 * bbs.setBbs_seq(rs.getInt("bbs_seq"));
				 * bbs.setBbs_lev(rs.getInt("bbs_lev"));
				 * bbs.setBbs_available(rs.getInt("bbs_available"));
				 * bbs.setBbs_category(rs.getInt("bbs_category"));
				 * bbs.setBbs_title(rs.getString("bbs_title"));
				 * bbs.setBbs_content(rs.getString("bbs_content"));
				 * bbs.setBbs_readcount(rs.getInt("bbs_readcount"));
				 * bbs.setBbs_date(rs.getTimestamp("bbs_date"));
				 * bbs.setUserID(rs.getString("userID"));
				 * bbs.setUserNickName(rs.getString("userNickName"));
				 * bbs.setBest(rs.getInt("best"));
				 * bbs.setIsAdmin(rs.getInt("isAdmin"));
				 * bbs.setIp(rs.getString("ip"));
				 */

				bbsList.add(bbs);
			}
			System.out.println(bbsList);
			System.out.println("BbsDAO : 게시판리스트 확인");
		} catch (Exception e) {
			System.out.println("BbsDAO : getBbsList 메소드 내부에서 오류 : " + e);
		} finally {
			closeDB();
		}
		return bbsList;
	}

	// 모든 글 개수 가져오기 / getBbsCount
	public int getBbsCount() {
		int count = 0;
		try {
			con = getCon();
			sql = "select count(bbsID) from tp_bbs where bbs_available=1 and bbs_category = 1";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("BbsDAO : " + count);
		} catch (Exception e) {
			System.out.println("BbsDAO : getBbsCount()메소드에서 예외 발생 : " + e);
		} finally {
			closeDB();
		}
		return count;
	}

	// 해당 글 가져오기 / getBbs
	public BbsBean getBbs(int bbsID) {
		BbsBean bbsb = null;

		try {
			con = getCon();
			sql = "select * from tp_bbs where bbsID=?";
			pstmt = con.prepareStatement(sql);
			// ?
			pstmt.setInt(1, bbsID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				bbsb = new BbsBean();

				bbsb.setBbsID(rs.getInt("bbsID"));
				bbsb.setBbs_ref(rs.getInt("bbs_ref"));
				bbsb.setBbs_seq(rs.getInt("bbs_seq"));
				bbsb.setBbs_lev(rs.getInt("bbs_lev"));
				bbsb.setBbs_available(rs.getInt("bbs_available"));
				bbsb.setBbs_category(rs.getInt("bbs_category"));
				bbsb.setBbs_title(rs.getString("bbs_title"));
				bbsb.setBbs_content(rs.getString("bbs_content"));
				bbsb.setRating(rs.getInt("rating"));
				bbsb.setStart_month(rs.getString("start_month"));
				bbsb.setEnd_month(rs.getString("end_month"));
				bbsb.setBbs_readcount(rs.getInt("bbs_readcount"));
				bbsb.setBbs_date(rs.getTimestamp("bbs_date"));
				bbsb.setUserID(rs.getString("userID"));
				bbsb.setUserNickName(rs.getString("userNickName"));
				bbsb.setFileID(rs.getInt("fileID"));
				bbsb.setBest(rs.getInt("best"));
				bbsb.setIsAdmin(rs.getInt("isAdmin"));
				bbsb.setLikeID(rs.getInt("likeID"));
				bbsb.setIp(rs.getString("ip"));
			}
			System.out.println("BbsDAO : bbsID에 따른 내용 담기 완료");
		} catch (Exception e) {
			System.out.println("BbsDAO : getBbs()메소드에서 예외 발생 : " + e);
		} finally {
			closeDB();
		}
		return bbsb;
	}
	
	// 해당 글 가져오기 / getBbs
	public BbsBean getBbsModi(int bbsID) {
		BbsBean bbsb = null;
		
		try {
			con = getCon();
			sql = "select * from tp_bbs where bbsID=?";
			pstmt = con.prepareStatement(sql);
			// ?
			pstmt.setInt(1, bbsID);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bbsb = new BbsBean();
				
				bbsb.setBbsID(rs.getInt("bbsID"));
				bbsb.setBbs_ref(rs.getInt("bbs_ref"));
				bbsb.setBbs_seq(rs.getInt("bbs_seq"));
				bbsb.setBbs_lev(rs.getInt("bbs_lev"));
				bbsb.setBbs_available(rs.getInt("bbs_available"));
				bbsb.setBbs_category(rs.getInt("bbs_category"));
				bbsb.setBbs_title(rs.getString("bbs_title").replaceAll("<br>", "\n").replaceAll("&nbsp", " ").replaceAll("&lt", "<").replaceAll("&gt", ">"));
				bbsb.setBbs_content(rs.getString("bbs_content").replaceAll("<br>", "\n").replaceAll("&nbsp", " ").replaceAll("&lt", "<").replaceAll("&gt", ">"));
				bbsb.setRating(rs.getInt("rating"));
				bbsb.setStart_month(rs.getString("start_month"));
				bbsb.setEnd_month(rs.getString("end_month"));
				bbsb.setBbs_readcount(rs.getInt("bbs_readcount"));
				bbsb.setBbs_date(rs.getTimestamp("bbs_date"));
				bbsb.setUserID(rs.getString("userID"));
				bbsb.setUserNickName(rs.getString("userNickName"));
				bbsb.setFileID(rs.getInt("fileID"));
				bbsb.setBest(rs.getInt("best"));
				bbsb.setIsAdmin(rs.getInt("isAdmin"));
				bbsb.setLikeID(rs.getInt("likeID"));
				bbsb.setIp(rs.getString("ip"));
			}
			System.out.println("BbsDAO : bbsID에 따른 내용 담기 완료");
		} catch (Exception e) {
			System.out.println("BbsDAO : getBbs()메소드에서 예외 발생 : " + e);
		} finally {
			closeDB();
		}
		return bbsb;
	}

	// 해당 글 업데이트 / updateBbs(bbsID)
	public void updateBbs(BbsBean bb) {
		try {
			con = getCon();
			sql = "update tp_bbs set " 
					+ "bbs_title=?, bbs_content=? where bbsID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bb.getBbs_title());
			pstmt.setString(2, bb.getBbs_content());
			pstmt.setInt(3, bb.getBbsID());

			pstmt.executeUpdate();

			System.out.println("BbsDAO : updateBbs 성공!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BbsDAO : updateBbs 실패!" + e);
		} finally {
			closeDB();
		}
	}

	// 게시판 글 삭제 / deleteBbs(bbsID)
	public int deleteBbs(BbsBean bbs, String userID) {
		int result = -1;
		try {
			con = getCon();
			sql = "select userID from tp_bbs where bbsID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bbs.getBbsID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (userID.equals(rs.getString("userID"))) {

					sql = "update tp_bbs set bbs_available=0 where bbsID=?";
					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, bbs.getBbsID());

					pstmt.executeUpdate();

					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
			System.out.println("BbsDAO : deleteBbs결과 -> " + result);
		} catch (Exception e) {
			System.out.println("BbsDAO : deleteBbs()메소드에서 예외 발생 : " + e);
		} finally {
			closeDB();
		}
		return result;
	}

	// 게시판 조회수 증가 / updateReadCount(bbsID)
	public void updateReadCount(int bbsID) {
		try {
			con = getCon();
			sql = "update tp_bbs set bbs_readcount=bbs_readcount+1 where bbsID=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bbsID);
			
			pstmt.executeUpdate();
			
			System.out.println("BbsDAO : 조회수 증가!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}// updateReadCount(bbsID)

}
