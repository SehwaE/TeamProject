package com.tp.event.db;

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

public class EventDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/tpDB");
		con = ds.getConnection();

		System.out.println("DAO : 디비 연결 성공!");

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

	// 게시판 글 입력 / insertEvent(bbs);
	public void insertEvent(BbsBean bbs) {
		int bbsID = 0;
		int fileID = 0;
		try {
			con = getCon();
			sql = "select max(bbsID) from tp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
			sql = "insert into tp_bbs " + "values(?,?,?,?,?,?,?,?,?,?, " + "?,?,now(),?,?,?,?,?,?,?)";

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

			System.out.println("rating : " + bbs.getRating());
			System.out.println("DAO : 게시글 등록 완료 ");

		} catch (Exception e) {
			System.out.println("DAO : 게시글 등록 오류");
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// insertEvent(bbs);

	// 모든 글 개수 가져오기 / getBbsCount();
	public int getBbsCount() {
		int count = 0;
		try {
			con = getCon();
			sql = "select count(bbsID) from tp_bbs where bbs_available=1 and bbs_category = 2";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("DAO : " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return count;
	}
	// getBbsCount();

	// getBbsList();
	public List getBbsList(int startRow, int rowsize) {
		ArrayList bbsList = new ArrayList();

		try {
			con = getCon();
			sql = "select * from tp_bbs where bbs_available=1 and bbs_category=2"
					+ " order by bbsID desc " + "limit ?,?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rowsize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BbsBean bbs = new BbsBean();

				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setBbs_category(rs.getInt("bbs_category"));
				bbs.setBbs_title(rs.getString("bbs_title"));
				bbs.setBbs_content(rs.getString("bbs_content"));
				bbs.setBbs_readcount(rs.getInt("bbs_readcount"));
				bbs.setRating(rs.getInt("rating"));
				bbs.setStart_month(rs.getString("start_month"));
				bbs.setEnd_month(rs.getString("end_month"));
				bbs.setUserID(rs.getString("userID"));
				bbs.setUserNickName(rs.getString("userNickName"));
				bbs.setBbs_date(rs.getTimestamp("bbs_date"));
				bbs.setFileID(rs.getInt("fileID"));
				bbs.setBest(rs.getInt("best"));
				bbs.setLikeID(rs.getInt("likeID"));
				bbs.setIsAdmin(rs.getInt("isAdmin"));

				bbsList.add(bbs);
			}
			System.out.println("DAO : 게시판 리스트 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return bbsList;
	}
	// getBbsList();

	// getBbs(bbs_id)
	public BbsBean getBbs(int bbsID) {
		BbsBean bbs = null;

		try {
			con = getCon();
			sql = "select * from tp_bbs where bbsID=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bbsID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				bbs = new BbsBean();

				bbs.setBbs_title(rs.getString("bbs_title"));
				bbs.setBbs_category(rs.getInt("bbs_category"));
				bbs.setBest(rs.getInt("best"));
				bbs.setBbs_content(rs.getString("bbs_content"));
				bbs.setRating(rs.getInt("rating"));
				bbs.setStart_month(rs.getString("start_month"));
				bbs.setEnd_month(rs.getString("end_month"));
				bbs.setIsAdmin(rs.getInt("isAdmin"));
				bbs.setFileID(rs.getInt("fileID"));
				bbs.setBbs_date(rs.getTimestamp("bbs_date"));
				bbs.setBbs_available(rs.getInt("bbs_available"));
				bbs.setBbs_lev(rs.getInt("bbs_lev"));
				bbs.setBbs_ref(rs.getInt("bbs_ref"));
				bbs.setBbs_seq(rs.getInt("bbs_seq"));
				bbs.setBbs_readcount(rs.getInt("bbs_readcount"));
				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setFileID(rs.getInt("fileID"));
				bbs.setUserNickName(rs.getString("userNickName"));
				bbs.setLikeID(rs.getInt("likeID"));
				bbs.setIp(rs.getString("ip"));

			}

			System.out.println("DAO : 게시글 정보 저장 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return bbs;
	}
	// getBbs(bbs_id)
	// getBbs(bbs_id)
	public BbsBean getBbsModi(int bbsID) {
		BbsBean bbs = null;
		
		try {
			con = getCon();
			sql = "select * from tp_bbs where bbsID=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bbsID);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bbs = new BbsBean();
				
				bbs.setBbs_category(rs.getInt("bbs_category"));
				bbs.setBest(rs.getInt("best"));
				bbs.setBbs_title(rs.getString("bbs_title").replaceAll("<br>", "\n").replaceAll("&nbsp", " ").replaceAll("&lt", "<").replaceAll("&gt", ">"));
				bbs.setBbs_content(rs.getString("bbs_content").replaceAll("<br>", "\n").replaceAll("&nbsp", " ").replaceAll("&lt", "<").replaceAll("&gt", ">"));
				bbs.setRating(rs.getInt("rating"));
				bbs.setStart_month(rs.getString("start_month"));
				bbs.setEnd_month(rs.getString("end_month"));
				bbs.setIsAdmin(rs.getInt("isAdmin"));
				bbs.setFileID(rs.getInt("fileID"));
				bbs.setBbs_date(rs.getTimestamp("bbs_date"));
				bbs.setBbs_available(rs.getInt("bbs_available"));
				bbs.setBbs_lev(rs.getInt("bbs_lev"));
				bbs.setBbs_ref(rs.getInt("bbs_ref"));
				bbs.setBbs_seq(rs.getInt("bbs_seq"));
				bbs.setBbs_readcount(rs.getInt("bbs_readcount"));
				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setFileID(rs.getInt("fileID"));
				bbs.setUserNickName(rs.getString("userNickName"));
				bbs.setLikeID(rs.getInt("likeID"));
				bbs.setIp(rs.getString("ip"));
				
			}
			
			System.out.println("DAO : 게시글 정보 저장 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return bbs;
	}
	// getBbs(bbs_id)

	// eventUpdate(BbsBean bbs)
	public void eventUpdate(BbsBean bbs, String userID) {

		try {
			con = getCon();
			sql = "select UserID from tp_bbs where bbsID=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbs.getBbsID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (userID.equals(rs.getString("userID"))) {

					sql = "update tp_bbs set " + "bbs_title=?, bbs_content=?, " + "start_month=?, end_month=? "
							+ "where bbsID=? ";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, bbs.getBbs_title());
					pstmt.setString(2, bbs.getBbs_content());
					/*pstmt.setInt(3, bbs.getRating());*/
					pstmt.setString(3, bbs.getStart_month());
					pstmt.setString(4, bbs.getEnd_month());
					pstmt.setInt(5, bbs.getBbsID());

					pstmt.executeUpdate();
				}
				System.out.println("DAO : 이벤트 게시글 수정 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// eventUpdate(BbsBean bbs)

	public int deleteEvent(BbsBean bbs, String userID) {
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
