package com.kitri.jdbctest;

import java.sql.*;

//내 아이디 탈퇴
public class DeleteTest {
	
	public DeleteTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//1
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private Connection makeConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		return conn;
	}
	
	
	public static void main(String[] args) {
		DeleteTest del = new DeleteTest();
		Connection conn = null;
		Statement stmt = null;
		int cnt = 0;
		
		String id = "KKIRUK";
		try {
			conn = del.makeConnection();//2
			
			String sql="";
			sql+= "delete jdbctest "
					+ "where id = '"+id+"'";//3-sql
			stmt = conn.createStatement();//3-statement
			
			cnt = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(cnt + "개 delete 완료!!!");
		
	}
	
}
