package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;

public class ProductDAO {
	public List<Product> selectAll() {
		List<Product> list = new ArrayList<Product>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1)JDBC드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2)DB연결
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			//3)SQL송신
			String selectAllSQL = "select cate_no, cate_name, prod_no, prod_name, prod_price, prod_detail " + 
					"from product p join product_category pc " + 
					"on p.prod_cate = pc.cate_no " + 
					"order by cate_no, prod_name";
			pstmt = con.prepareStatement(selectAllSQL);
			//4)결과수신
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				Product p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);
				
				list.add(p);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5)연결닫기
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public Product selectbyno(String prod_num) {
		System.out.println("dao까진 온거니?");
		Product p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1)JDBC드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2)DB연결
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			//3)SQL송신
			String selectAllSQL = "select prod_no, prod_name, p.prod_price, p.prod_detail, pc.cate_no, pc.cate_name\n" + 
					"from product p, product_category pc\n" + 
					"where p.prod_cate = pc.cate_no\n" + 
					"and prod_no = ?";
			pstmt = con.prepareStatement(selectAllSQL);
			pstmt.setString(1, prod_num);
			//4)결과수신
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);
				
			} 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5)연결닫기
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
}
