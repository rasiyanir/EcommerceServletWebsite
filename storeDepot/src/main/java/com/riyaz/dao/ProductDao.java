package com.riyaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.riyaz.bean.ProductBean;
import com.riyaz.dbconnect.connectDatabase;

public class ProductDao {

public List<ProductBean> getProduct() {
		
		List<ProductBean> productList = new ArrayList<ProductBean>();
		
		String query = "select * from item where itemCount > 0";
		connectDatabase dbase = new connectDatabase();
		dbase.connectingDB();
		try {
			dbase.stat = dbase.conn.prepareStatement(query);
			dbase.rs = dbase.stat.executeQuery();
			
			while(dbase.rs.next()) {
				productList.add(new ProductBean(dbase.rs.getInt(1), dbase.rs.getString(2), dbase.rs.getInt(3), dbase.rs.getInt(4)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	
}
