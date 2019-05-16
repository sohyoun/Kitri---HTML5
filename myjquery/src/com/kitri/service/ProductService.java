package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;

public class ProductService {
	
	public List<Product> findAll(){
		ProductDAO dao = new ProductDAO();
		List<Product> list = dao.selectAll();
		return list;
	}
	
	public Product findByNo(String prod_num) {
		ProductDAO dao = new ProductDAO();
		System.out.println("service까진 왔니..?");
		Product p = dao.selectbyno(prod_num);
		
		return p;
	}
}
