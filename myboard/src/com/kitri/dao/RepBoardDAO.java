package com.kitri.dao;

import java.sql.*;

import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;

public class RepBoardDAO {
	public void insert(RepBoard repBoard) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1)JDBC드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);

			String insertSQL = "insert into repboard("
					+ "BOARD_SEQ, PARENT_SEQ, BOARD_SUBJECT, BOARD_WRITER, BOARD_CONTENTS, BOARD_DATE, BOARD_PASSWORD, BOARD_VIEWCOUNT) "
					+ " values(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, systimestamp, ?, 0)";

			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, repBoard.getParent_seq());
			pstmt.setString(2, repBoard.getBoard_subject());
			pstmt.setString(3, repBoard.getBoard_writer());
			pstmt.setString(4, repBoard.getBoard_contents());
			pstmt.setString(5, repBoard.getBoare_password());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) { RepBoardDAO dao = new RepBoardDAO();
		RepBoard repBoard = new RepBoard(); repBoard.setBoard_subject("테스트제목");
		repBoard.setBoard_writer("test"); repBoard.setBoard_contents("테스트 내용");
		repBoard.setBoare_password("testp"); repBoard.setParent_seq(1);//답글쓰기용 테스트
		try { 
			dao.insert(repBoard); 
		} catch (AddException e) { 
			e.printStackTrace();
		}
	}//글쓰기용 테스트 }
	 
}
