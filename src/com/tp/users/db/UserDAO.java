package com.tp.users.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결- 커넥션풀
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/tpDB");
		con = ds.getConnection();

		System.out.println("DAO : 디비 연결 성공! -> " + con);

		return con;
	}

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

	// 회원가입 처리 / insertUser(ub)
	public void insertUser(UserBean ub) {
		try {
			con = getCon();
			sql = "insert into tp_users(userID,userPass,userName,userEmail,userAddr,"
					+ "userPhone,userProfile,userNickName,reg_date,isAdmin) " + "values(?,?,?,?,?,?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ub.getUserID());
			pstmt.setString(2, ub.getUserPass());
			pstmt.setString(3, ub.getUserName());
			pstmt.setString(4, ub.getUserEmail());
			pstmt.setString(5, ub.getUserAddr());
			pstmt.setString(6, ub.getUserPhone());
			pstmt.setString(7, ub.getUserProfile());
			pstmt.setString(8, ub.getUserNickName());
			pstmt.setInt(9, ub.getIsAdmin());

			pstmt.executeUpdate();

			System.out.println("DAO : 회원가입 완료!");

		} catch (Exception e) {
			System.out.println("DAO : 회원가입 실패!");
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}

	// 아이디 중복 체크 / duplicateIdCheck(id)
	public int duplicateIdCheck(String id) {
		int result = -1;
		try {
			con = getCon();

			sql = "select userID from tp_users where userID=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 0; // 사용불가
			} else {
				result = 1; // 사용가능
			}
			System.out.println("아이디 중복체크 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// 이메일 중복 체크 / duplicateEmailCheck(userEmail)
	public int duplicateEmailCheck(String userEmail) {
		int result = -1;
		try {
			con = getCon();

			sql = "select userEmail from tp_users where userEmail=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userEmail);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 0; // 사용불가
			} else {
				result = 1; // 사용가능
			}
			System.out.println("이메일 중복체크 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// 닉네임 중복 체크 / duplicateNickCheck(userNickName)
	public int duplicateNickCheck(String userNickName) {
		int result = -1;
		try {
			con = getCon();

			sql = "select userNickName from tp_users where userNickName=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userNickName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 0; // 사용불가
			} else {
				result = 1; // 사용가능
			}
			System.out.println("닉네임 중복체크 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// 로그인 처리 /loginCheck(ub)
	public int loginCheck(UserBean ub) {
		int result = -1;
		try {
			con = getCon();

			sql = "select userPass from tp_users where userID=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ub.getUserID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (ub.getUserPass().equals(rs.getString("userPass"))) {
					result = 1; // 로그인
				} else {
					result = 0; // 비밀번호 오류
				}
			} else {
				result = -1; // 비회원
			}
			System.out.println("DAO : 로그인 체크 " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// 유저 정보 가져오기 / getUser(id)
	public UserBean getUser(String userID) {

		UserBean ub = null;

		try {
			con = getCon();

			sql = "select * from tp_users where userID=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				ub = new UserBean();

				ub.setUserID(rs.getString("userID"));
				ub.setUserPass(rs.getString("userPass"));
				ub.setUserEmail(rs.getString("userEmail"));
				ub.setUserAddr(rs.getString("userAddr"));
				ub.setUserName(rs.getString("userName"));
				ub.setUserNickName(rs.getString("userNickName"));
				ub.setUserPhone(rs.getString("userPhone"));
				ub.setUserProfile(rs.getString("userProfile"));
				ub.setIsAdmin(rs.getInt("isAdmin"));
				ub.setReg_date(rs.getTimestamp("reg_date"));
			}

			System.out.println("DAO : 회원 정보 저장 완료");

			System.out.println(ub);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return ub;

	} // getUSer(id)

	// 유저 정보 업데이트 / getUpdateUser(ub)
	public int getUpdateUser(UserBean ub) {
		int check = -1;

		try {
			con = getCon();

			sql = "select userPass from tp_users where userID=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ub.getUserID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (ub.getUserPass().equals(rs.getString("userPass"))) {
					sql = "update tp_users " + "set userPass=?, userName=?, userEmail=?, userAddr=?, "
							+ "userPhone=?, userProfile=?, userNickName=? " + "where UserID=?";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, ub.getUserPass());
					pstmt.setString(2, ub.getUserName());
					pstmt.setString(3, ub.getUserEmail());
					pstmt.setString(4, ub.getUserAddr());
					pstmt.setString(5, ub.getUserPhone());
					pstmt.setString(6, ub.getUserProfile());
					pstmt.setString(7, ub.getUserNickName());
					pstmt.setString(8, ub.getUserID());

					check = pstmt.executeUpdate();

				}
			} else {
				check = -1;
			}

			System.out.println("DAO : 회원정보 수정 완료 >> " + check);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return check;
	}

	// 유저 정보 삭제 / deleteUser(ub)
	public int deleteUser(UserBean ub) {
		int check = 1;

		try {
			con = getCon();

			sql = "select userPass from tp_users where userID=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ub.getUserID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (ub.getUserPass().equals(rs.getString("userPass"))) {
					sql = "delete from tp_users where userID=?";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, ub.getUserID());

					pstmt.executeUpdate();

					check = 1;

				} else {
					check = 0;
				}
			} else {
				check = -1;
			}

			System.out.println("DAO : 회원 탈퇴 완료 >> " + check);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return check;

	}

	// 닉네임 중복 체크 / duplicateNickNameCheck(ub)
	public boolean duplicateNickNameCheck(UserBean ub) {
		boolean x = false;

		try {
			con = getCon();

			sql = "select userNickName from tp_users where userNickName=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ub.getUserNickName());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return x;
	}

	// 이메일 중복 체크 / duplicateEmailCheck(ub)
	public boolean duplicateEmailCheck(UserBean ub) {
		boolean x = false;

		try {
			con = getCon();

			sql = "select userEmail from tp_users where userEmail=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ub.getUserEmail());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return x;
	}

	public int updatePassword(UserBean ub, String userPass2) {
		int result = -1;

		try {
			con = getCon();

			sql = "select userPass from tp_users where userID = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ub.getUserID());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (ub.getUserPass().equals(rs.getString("userPass"))) {
					sql = "update tp_users set userPass =? where userID = ? ";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, userPass2);
					pstmt.setString(2, ub.getUserID());

					pstmt.executeUpdate();
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch (Exception e) {
			System.out.println("updatePassword 에서 오류 : " + e);
			;
		} finally {
			closeDB();
		}
		return result;
	}

}
