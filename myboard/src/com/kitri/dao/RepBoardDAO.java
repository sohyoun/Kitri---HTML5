package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<RepBoard> selectByRows(int startRow, int endRow) {
		List<RepBoard> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			String selectByRowsSQL = "SELECT *"
					+ "FROM (SELECT rownum r, repboard.*"
					+ "		 FROM repboard"
					+ "		 START WITH parent_seq = 0"
					+ "		 CONNECT BY PRIOR board_seq = parent_seq"
					+ "		 ORDER SIBLINGS BY board_seq DESC)"
					+ "WHERE r BETWEEN ? AND ?";
			
			pstmt = con.prepareStatement(selectByRowsSQL);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RepBoard repBoard = new RepBoard();
				repBoard.setBoard_seq(rs.getInt("board_seq"));
				repBoard.setParent_seq(rs.getInt("parent_seq"));
				repBoard.setBoard_subject(rs.getString("board_subject"));
				repBoard.setBoard_writer(rs.getString("board_writer"));
				repBoard.setBoard_contents(rs.getString("board_contents"));
				repBoard.setBoard_date(rs.getTimestamp("board_date"));
				repBoard.setBoare_password(rs.getString("board_password"));
				repBoard.setBoard_viewcount(rs.getInt("board_viewcount"));
				list.add(repBoard);		
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	public int selectTotalCnt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCnt = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			String selectTotalCntSQL = "SELECT count(*) FROM repboard";
			pstmt = con.prepareStatement(selectTotalCntSQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return totalCnt;
	}

	public static void main(String[] args) { 
		RepBoardDAO dao = new RepBoardDAO();
		/*
		 * RepBoard repBoard = new RepBoard(); repBoard.setBoard_subject("테스트제목");
		 * repBoard.setBoard_writer("test"); repBoard.setBoard_contents("테스트 내용");
		 * repBoard.setBoare_password("testp"); repBoard.setParent_seq(1);//답글쓰기용 테스트
		 * try { dao.insert(repBoard); } catch (AddException e) { e.printStackTrace(); }
		 */
		
		for(RepBoard repBoard:dao.selectByRows(11, 20)) {
			System.out.println(repBoard);
		}
	}//글쓰기용 테스트 

	
	
	 
}
