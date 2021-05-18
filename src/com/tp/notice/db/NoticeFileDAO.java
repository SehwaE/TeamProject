package com.tp.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tp.bbs.bean.FileBean;

public class NoticeFileDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/tpDB");
		con = ds.getConnection();

		System.out.println("FileDAO : 디비연결 -> " + con);

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

	// 파일 업로드 / upload
	public int upload(String fileName,String fileRealName,String userID) {
		int fileID = 0;
		int bbsID = 0;
		try {
			con = getCon();
			sql = "select max(fileID) from tp_files";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				fileID = rs.getInt(1) + 1;
			}
			String sql2 = "select max(bbsID) from tp_bbs";
				pstmt = con.prepareStatement(sql2);
	
				rs = pstmt.executeQuery();
				// 있으면 + 1, 없으면 1
				if(rs.next()){
					bbsID = rs.getInt(1)+1;
				}else{
					bbsID = 1;
				}
			sql = "insert into tp_files(fileID,fileName,fileRealName,bbsID,userID,file_date)"
					+ " values(?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, fileID);
			pstmt.setString(2, fileName);
			pstmt.setString(3, fileRealName);
			pstmt.setInt(4, bbsID);
			pstmt.setString(5, userID);

			System.out.println("FileDAO : 파일업로드 완료");
			return pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("FileDAO : upload()메소드에서 예외 발생 : " + e);
		}finally {
			closeDB();
		}
		return -1;
	}

	// 파일 불러오기 / getFile - 삭제를 위해 폴더에 있는 파일 이름을 가져온다.
	public String getFile(int bbsID) {
		String fileRealName = null;
		try {
			con = getCon();
			sql = "select fileRealName from tp_files where bbsID=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bbsID);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileRealName = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("FileDAO : getFile()메소드에서 예외 발생 : "+e);
		} finally {
			closeDB();
		}
		return fileRealName;
	}

	// 파일 수정하기 / updateFile
	public int updateFile(String fileName,String fileRealName, int bbsID) {
		try {
			con = getCon();
			sql = "update tp_files set fileName=?, fileRealName=? where bbsID=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			pstmt.setInt(3, bbsID);
			
			System.out.println("FileDAO : updateFile - fileName -> "+fileName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("FileDAO : updateFile()메소드에서 예외 발생 : "+ e);
		} finally {
			closeDB();
		}
		return -1;
	}
	
	// 확인용 - 파일 내용 / 추후 삭제 예정
	public ArrayList getFileList() {
		sql = "select * from tp_files";
		ArrayList list = new ArrayList();

		try {
			con = getCon();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				FileBean file = new FileBean();
				
				file.setFileID(rs.getInt("fileID"));
				file.setFileName(rs.getString("fileName"));
				file.setFileRealName(rs.getString("fileRealName"));
				file.setBbsID(rs.getInt("bbsID"));
				file.setUserID(rs.getString("userID"));

				list.add(file);
			}
		} catch (Exception e) {
			System.out.println("FileDAO : getFileList() - 예외 발생 : "+e);
		}finally {
			closeDB();
		}
		return list;
	}

	// 파일 삭제
	public int deleteFile(FileBean file, String userID) {
		int result = -1;
		try {
			con = getCon();
			sql = "select userID from tp_files where fileID=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, file.getFileID());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (userID.equals(rs.getString("userID"))) {
					
					sql = "delete from tp_files where fileID=?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, file.getFileID());
					
					pstmt.executeUpdate();
					
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch (Exception e) {
			System.out.println("FileDAO : deleteFile() - 예외 발생 : "+e);
		}finally {
			closeDB();
		}
		return result;
	}

}
