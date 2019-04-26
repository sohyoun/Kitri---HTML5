package com.kitri.jdbctest;

import java.sql.*;

public class ConnectionTest {
	
	public ConnectionTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void dbConnect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri", "kitri");
			System.out.println("DB Connection Success!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		ConnectionTest ct = new ConnectionTest();
		ct.dbConnect();
	}

}
