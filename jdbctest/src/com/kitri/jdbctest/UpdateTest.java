package com.kitri.jdbctest;

import java.sql.*;

public class UpdateTest {
	//내 아이디 가입일을 현재시간으로 바꾸기
	public UpdateTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//1
			System.out.println("Driver Loading success!!!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection dbConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		return conn;
	}
	
	public static void main(String[] args) {
		UpdateTest upt = new UpdateTest();
		Connection conn = null;
		Statement stmt = null;
		int cnt=0;
		
		String id = "kkiruk";
		try {
			conn = upt.dbConnection();//2
			
			String sql="";//3 - sql
			sql += "update jdbctest set joindate = sysdate "
					+ "where id='"+id+"'";
			stmt = conn.createStatement();//3 - statement
			
			cnt = stmt.executeUpdate(sql);//4
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(cnt +"개 업데이트 완료!!!!!!");
	}

	
}
