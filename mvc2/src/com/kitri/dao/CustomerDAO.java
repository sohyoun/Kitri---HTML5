package com.kitri.dao;

import java.sql.*;
import java.util.List;

import javax.naming.NameNotFoundException;

import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;

public class CustomerDAO {
	public Customer selectById(String id) throws com.kitri.exception.NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			try {
				//1) JDBC드라이버 로드
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//2) DB연결
				con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
				//3) SQL구문 DB서버로 송신 : executeQuery()
				//4) 결과수신 : rs
				String selectByIdSQL = "select * from customer where id=?";
				pstmt = con.prepareStatement(selectByIdSQL);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) { //id에 해당하는 고객O
					Customer c = new Customer();
					c.setId(id);
					c.setPass(rs.getString("pass"));
					c.setName(rs.getString("name"));
					return c;
				} else { //id에 해당하는 고객X
					throw new NotFoundException("아이디에 해당하는 고객이 없습니다.");
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new NotFoundException(e.getMessage());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NotFoundException(e.getMessage());
			} finally {
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
	}
	public List<Customer> selectByName(String name){
		return null;
	}
	public List<Customer> selectAll(){
		return null;
	}
	public void insert(Customer c) {
		
	}
}
