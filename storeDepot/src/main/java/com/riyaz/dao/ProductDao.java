package com.riyaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.riyaz.bean.ProductBean;
import com.riyaz.dbconnect.connectDatabase;

public class ProductDao {

public List<ProductBean> getProduct() {
		
	EntityManager em = null;
	List<ProductBean> productList = null;
	
	try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select p from ProductBean p where p.itemCount > 0");
		productList = query.getResultList();
		
	}catch(Exception e) {
		System.out.println(e);
	}
	finally {
		em.close();
	}
	
	return productList;
		
	
		/*
		 * List<ProductBean> productList = new ArrayList<ProductBean>();
		 * 
		 * String query = "select * from item where itemCount > 0"; connectDatabase
		 * dbase = new connectDatabase(); dbase.connectingDB(); try { dbase.stat =
		 * dbase.conn.prepareStatement(query); dbase.rs = dbase.stat.executeQuery();
		 * 
		 * while(dbase.rs.next()) { productList.add(new ProductBean(dbase.rs.getInt(1),
		 * dbase.rs.getString(2), dbase.rs.getInt(3), dbase.rs.getInt(4))); }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return productList;
		 */
	}
	
}
