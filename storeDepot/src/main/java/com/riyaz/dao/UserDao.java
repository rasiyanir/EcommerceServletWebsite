package com.riyaz.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.riyaz.dbconnect.connectDatabase;

public class UserDao {
	
	
	public boolean checkInDB(String user, String pass) {
		
		
		EntityManager em = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Query query = em.createQuery("select u from User u where u.username =?1 and u.password =?2");
			query.setParameter(1, user);
			query.setParameter(2, pass);
			List rs = query.getResultList();
			int size = rs.size();
			if(size!=0) {
				return true;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			em.close();
		}
		
		
		
		
		/*
		 * String query = "select * from user where username = ? and password = ?";
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB(); try {
		 * dbase.stat = dbase.conn.prepareStatement(query); dbase.stat.setString(1,
		 * user); dbase.stat.setString(2, pass); dbase.rs = dbase.stat.executeQuery();
		 * 
		 * if(dbase.rs.next()) { return true; }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
		return false;
	}
}
