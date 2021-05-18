package com.tp.location.action;

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
import com.tp.location.bean.LocationBean;

public class LocationDAO {
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

	public void insertLocation(LocationBean lb) {
		int LocationID = 0;

		try {
			con = getCon();
			sql = "select max(LocationID) from tp_locations";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			// 있으면 + 1, 없으면 1
			if (rs.next()) {
				LocationID = rs.getInt(1) + 1;
			} else {
				LocationID = 1;
			}

			sql = "insert into tp_locations(locationID, location_name, location_spot, userID, location_date)" 
					+ "values(?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, LocationID);
			pstmt.setString(2, lb.getLocation_name());
			pstmt.setString(3, lb.getLocation_spot()); // user.getUserID()
			pstmt.setString(4, lb.getUserID()); // user.getUserID()

			pstmt.executeUpdate();
			System.out.println("userID : "+lb.getUserID());
			System.out.println("DAO : 장소 등록 완료");
		} catch (Exception e) {
			System.out.println("insertLocation 메소드 내부에서 오류 : " + e);
		} finally {
			closeDB();
		}
	}

	// 장소 리스트 출력(아이디) / getBbsList
	public List getLocationList(String userID) {
		List lbList = new ArrayList();
		try {
			con = getCon();
			sql = "select * from tp_locations where userID = ? order by locationID desc";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				LocationBean lb = new LocationBean();
				
				lb.setLocation_name(rs.getString("location_name"));
				lb.setLocation_spot(rs.getString("location_spot"));
				lb.setUserID(rs.getString("userID"));
				
				lbList.add(lb);
			}
			System.out.println(lbList);
			System.out.println("DAO : 게시판리스트 확인");
		} catch (Exception e) {
			System.out.println("getLocationList 메소드 내부에서 오류 : " + e);
		} finally {
			closeDB();
		}
		return lbList;
	}

	// userID가 가지고 있는 장소 정보 가져오기 / getLocationCount
	public int getLocationCount(String userID) {
		int count = 0;
		try {
			con = getCon();
			sql = "select count(locationID) from tp_locations where userID = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("DAO:" + count);
		} catch (Exception e) {
			System.out.println("getLocationCount()메소드에서 예외 발생 : " + e);
		} finally {
			closeDB();
		}
		return count;
	}

}
