package com.tp.bbs.db;

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

public class BbsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/tpDB");
		con = ds.getConnection();

		System.out.println("DAO : 디비연결 ::::::: " + con);

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
					fileID = rs.getInt(1) + 1;
				}

				sql = "insert into tp_bbs(bbsID, bbs_ref, bbs_seq, bbs_lev, bbs_available, bbs_category, bbs_title, bbs_content, bbs_readcount, bbs_date, userID, userNickName, fileID, best, isAdmin, likeID, ip)"
						+ "values(" + "?,?,?,?,?,?,?,?,?,now(),?," + "?,?,?,?,?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, bbsID);
				pstmt.setInt(2, bbsID); // 번호
				pstmt.setInt(3, 0); // 순서
				pstmt.setInt(4, 0); // 답글 단계
				pstmt.setInt(5, 1); // 사용 : 1 / 안쓰면 0
				pstmt.setInt(6, bbs.getBbs_category()); // 카테고리 - 일단 0
				pstmt.setString(7, bbs.getBbs_title());
				pstmt.setString(8, bbs.getBbs_content());
				pstmt.setInt(9, 0); // 조회수
				pstmt.setString(10, bbs.getUserID()); // user.getUserID()
				pstmt.setString(11, bbs.getUserNickName()); // user.getUserNickName()
				pstmt.setInt(12, fileID); // file.getFileID() // bbs.getFileID()
				pstmt.setInt(13, 0); // 베스트 : 1 / 일반 : 0
				pstmt.setInt(14, 0); // user.getIsAdmin()
				pstmt.setInt(15, 0); // lk.getLikeID()
				pstmt.setString(16, bbs.getIp());

				pstmt.executeUpdate();

				System.out.println("DAO : 게시글 등록 완료");
			} catch (Exception e) {
				System.out.println("insertBbs 메소드 내부에서 오류 : " + e);
			} finally {
				closeDB();
			}
		}

	// 게시판 리스트 출력 / getBbsList
	public List getBbsList(int startRow, int rowSize) {
		List bbsList = new ArrayList();
		try {
			con = getCon();
			sql = "select * from tp_bbs order by bbsID desc limit ?,?";
			// limit offset(시작점), row_count(출력수)
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rowSize);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				BbsBean bbs = new BbsBean();

				bbs.setBbsID(rs.getInt("bbsID"));
				bbs.setBbs_category(rs.getInt("bbs_category"));
				bbs.setBbs_content(rs.getString("bbs_content"));
				bbs.setBbs_title(rs.getString("bbs_title"));
				bbs.setBbs_date(rs.getTimestamp("bbs_date"));
				bbs.setUserID(rs.getString("userID"));
				bbs.setBbs_available(rs.getInt("bbs_available"));
				bbs.setFileID(rs.getInt("fileID"));
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
				 * 
				 * bbs.setBest(rs.getInt("best"));
				 * bbs.setIsAdmin(rs.getInt("isAdmin"));
				 * bbs.setLikeID(rs.getInt("likeID"));
				 * bbs.setIp(rs.getString("ip"));
				 */

				bbsList.add(bbs);
			}
			System.out.println(bbsList);
			System.out.println("DAO : 게시판리스트 학인");
		} catch (Exception e) {
			System.out.println("getBbsList 메소드 내부에서 오류 : " + e);
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
			sql = "select count(BbsID) from tp_bbs";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("DAO:" + count);
		} catch (Exception e) {
			System.out.println("getBbsCount()메소드에서 예외 발생 : " + e);
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
			System.out.println("DAO : bbsID에 따른 내용 담기 완료");
		} catch (Exception e) {
			System.out.println("getBbs()메소드에서 예외 발생 : " + e);
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
					+ "bbs_category=?, bbs_title=?, bbs_content=? where bbsID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bb.getBbs_category());
			pstmt.setString(2, bb.getBbs_title());
			pstmt.setString(3, bb.getBbs_content());
			pstmt.setInt(4, bb.getBbsID());

			pstmt.executeUpdate();
			System.out.println("updateBbs가 성공했습니다. " );
		} catch (Exception e) {
			System.out.println("updateBbs 실패 : " + e);
		} finally {
			closeDB();
		}
	}

	// 게시판 글 삭제 / deleteBbs(bbsID)
	public int deleteBbs(BbsBean bbs, String userID) {
		int result = -1;
		try {
			con = getCon();
			/*sql = "select userID from tp_bbs where bbsID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bbs.getBbsID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (userID.equals(rs.getString("userID"))) {*/
			sql = "update tp_bbs set bbs_available = 0 WHERE bbsID = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bbs.getBbsID());

			pstmt.executeUpdate();
				/*}
			}*/
		} catch (Exception e) {
			System.out.println("deleteBbs()메소드에서 예외 발생 : " + e);
		} finally {
			closeDB();
		}
		return result;
	}


	/////////////////////////////////////

	/*
	 * public List getBoardKeywordList(String keyword, int startNum, int endNum)
	 * { BbsBean bbs = null; String allKeyword = "%" + keyword + "%"; List
	 * bbsList = new ArrayList();
	 * 
	 * sql = "";
	 * 
	 * try { con = getCon(); pstmt = con.prepareStatement(sql);
	 * pstmt.setString(1, allKeyword); pstmt.setInt(2, startNum);
	 * pstmt.setInt(3, endNum);
	 * 
	 * rs = pstmt.executeQuery(); while (rs.next()) {
	 * bbs.setBbsID(rs.getInt("bbsID"));
	 * bbs.setBbs_title(rs.getString("bbs_title"));
	 * bbs.setBbs_content(rs.getString("bbs_content"));
	 * bbs.setUserID(rs.getString("userID")); bbsList.add(bbs); } } catch
	 * (Exception e) { System.out.println("오류 발생: " + e); } finally { closeDB();
	 * } return bbsList; }
	 * 
	 * public int getTotalKeywordCount(String keyword) { BbsBean bbs = null;
	 * String allKeyword = "%" + keyword + "%"; int cnt = 0; sql =
	 * "select count(*) from tp_bbs where tp_title like ?"; try { con =
	 * getCon(); pstmt = con.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, allKeyword);
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) { cnt = rs.getInt(1); } } catch
	 * (Exception e) { System.out.println("오류 발생: " + e); } finally { closeDB();
	 * } return cnt; }
	 */
}
