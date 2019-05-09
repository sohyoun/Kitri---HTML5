package com.kitri.util;

import java.sql.*;


public class DBConnection {
	
	static {//'static 블록 초기화'를 쓰는 이유(생성자 대신)
		try {
			Class.forName(SiteConstance.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection makeConnection() throws SQLException {
		return DriverManager.getConnection(SiteConstance.DB_URL, SiteConstance.DB_USERNAME, SiteConstance.DB_PASSWORD);
	}
}
