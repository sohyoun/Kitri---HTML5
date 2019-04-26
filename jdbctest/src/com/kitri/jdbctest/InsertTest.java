package com.kitri.jdbctest;

import java.sql.*;

public class InsertTest {
	//아이디 생성
	public InsertTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection makeConnection() throws SQLException {
		Connection conn = null;

		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		return conn;
	}
	
	public static void main(String[] args) {
		InsertTest it = new InsertTest();//driver loading
		String name ="안효인";
		String id="홍콩반점시렁";
		Connection conn = null;
		Statement stmt = null;
		int cnt =0;
		try {
//			2
			conn = it.makeConnection();//connection
			
//			3
			String sql = "";//쿼리문 길어지면 string버퍼쓰기
			sql += "insert into jdbctest (no, name, id, joindate) \n";
			sql += "values (jdbctest_no_seq.nextval, '" +name+"','"+ id+"', sysdate )"; // 쿼리문 만듦
			stmt = conn.createStatement();//statement 실행
			System.out.println(sql);
//			4
			cnt = stmt.executeUpdate(sql);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(cnt!=0)
			System.out.println("data insert success");
		else
			System.out.println("data insert fail");
		
	}
}
