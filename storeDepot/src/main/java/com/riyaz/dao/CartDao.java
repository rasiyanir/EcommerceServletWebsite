package com.riyaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.riyaz.bean.CartBean;
import com.riyaz.bean.ProductBean;
import com.riyaz.dbconnect.connectDatabase;

public class CartDao {

	
public String addToCart(String username, int itemID, int quantity, int price, String itemName) {
	
		
		int itemTotal = quantity*price;
		int stockCount = 0;
		int cartCount = 0;
		
		EntityManager em = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Query query = em.createQuery("select c from CartBean c where c.username =?1 and c.itemID =?2");
			query.setParameter(1, username);
			query.setParameter(2, itemID);
			List rs = query.getResultList();
			int size = rs.size();
			if(size!=0) {
				CartBean c = (CartBean) rs.get(0);
				cartCount = c.getItemQuantity();
					Query query2 = em.createQuery("select p from ProductBean p where p.itemID =?1");
					query2.setParameter(1, itemID);
					List rs1 = query2.getResultList();
					if(!(rs1.isEmpty())) {
						ProductBean p = (ProductBean) rs1.get(0);
						stockCount = p.getItemCount();
						if(quantity <= (stockCount - cartCount)) {
							int qty = c.getItemQuantity();
							int tot = c.getItemTotal();
							int totalQuantity = qty + quantity; 
							int finalTotal = tot + itemTotal;
							Query query3 = em.createQuery("update CartBean c set c.itemQuantity =?1, c.itemTotal =?2 where c.username =?3 and c.itemID =?4");
							query3.setParameter(1, totalQuantity);
							query3.setParameter(2, finalTotal);
							query3.setParameter(3, username);
							query3.setParameter(4, itemID);
							query3.executeUpdate();
							em.getTransaction().commit();
							return "add";
						}
						else {
							return "excessQuantity";
						}
						
					}
					
			}
			else {
				em.persist(new CartBean(username, itemID, itemName, price, quantity, itemTotal, "no"));
				em.getTransaction().commit();
				return "add";
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			em.close();
		}
		
		return "exception";
		
		/*
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB(); 
		 * try {
		 * String query1 = "select * from cart where username = '" + username +
		 * "' and itemID = '" + itemID + "'"; 
		 * dbase.stat = dbase.conn.prepareStatement(query1); dbase.rs = dbase.stat.executeQuery();
		 * if(dbase.rs.next()) { String query2 = "select c.username, c.itemID, c.itemQuantity, i.itemCount from cart c natural join item i  where c.itemID = '"
		 * + itemID +"' and c.username = '" + username + "' group by itemID";
		 * dbase.stat3 = dbase.conn.prepareStatement(query2); 
		 * dbase.rs1 = dbase.stat3.executeQuery(); 
		 * if(dbase.rs1.next()) 
		 * { 
		 * stockCount = dbase.rs1.getInt(4);
		 * cartCount = dbase.rs1.getInt(3); 
		 *  } 
		 *  if(quantity <= (stockCount - cartCount)) 
		 *  { int qty = dbase.rs.getInt(3); 
		 *  int tot = dbase.rs.getInt(4); 
		 *  int totalQuantity = qty + quantity; 
		 *  int finalTotal = tot + itemTotal; String query3 = "update cart set itemQuantity = '" +
		 * totalQuantity + "', itemTotal = '" + finalTotal + "' where username = '" +
		 * username + "' and itemID = '" + itemID + "'"; 
		 * dbase.stat1 = dbase.conn.prepareStatement(query3); 
		 * dbase.stat1.executeUpdate(); 
		 * return"add"; 
		 * } else 
		 * { return "excessQuantity"; } } 
		 * else { String query4 =
		 * "Insert into cart values ('" + username + "', '" + itemID + "', '" + quantity
		 * + "','" + itemTotal + "', 'no');"; dbase.stat2 =
		 * dbase.conn.prepareStatement(query4); dbase.stat2.executeUpdate(); return
		 * "add"; } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * return "exception";
		 */
	
	}


public String deleteFromCart(String username, int itemID, int quantity, int price) {
	
	
	int itemTotal = quantity*price;
	EntityManager em = null;
	
	try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select c from CartBean c where c.username =?1 and c.itemID =?2");
		query.setParameter(1, username);
		query.setParameter(2, itemID);
		List rs = query.getResultList();
		int size = rs.size();
		if(size!=0) {
			CartBean c = (CartBean) rs.get(0);
			int qty = c.getItemQuantity();
			int tot = c.getItemTotal();
			int totalQuantity = qty - quantity; 
			int finalTotal = tot - itemTotal;
			Query query3 = em.createQuery("update CartBean c set c.itemQuantity =?1, c.itemTotal =?2 where c.username =?3 and c.itemID =?4");
			query3.setParameter(1, totalQuantity);
			query3.setParameter(2, finalTotal);
			query3.setParameter(3, username);
			query3.setParameter(4, itemID);
			query3.executeUpdate();
			em.getTransaction().commit();
			return "delete";
		}
		else {
			return "no record";
		}
		
	}catch(Exception e) {
		System.out.println(e);
	}
	finally {
		em.close();
	}
	
	return "exception";
	
	
	
	
	
	
	
	
	
	
	
		/*
		 * String query1 = "select * from cart where username = '" + username +
		 * "' and itemID = '" + itemID + "'"; int itemTotal = quantity*price;
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB(); try {
		 * dbase.stat = dbase.conn.prepareStatement(query1); dbase.rs =
		 * dbase.stat.executeQuery(); if(dbase.rs.next()) { int qty =
		 * dbase.rs.getInt(3); int tot = dbase.rs.getInt(4); int totalQuantity = qty -
		 * quantity; int finalTotal = tot - itemTotal; if(totalQuantity <= 0) { String
		 * query3 = "delete from cart where username = '" + username +
		 * "' and itemID = '" + itemID + "'"; dbase.stat1 =
		 * dbase.conn.prepareStatement(query3); dbase.stat1.executeUpdate(); return
		 * "delete"; } else { String query2 = "update cart set itemQuantity = '" +
		 * totalQuantity + "', itemTotal = '" + finalTotal + "' where username = '" +
		 * username + "' and itemID = '" + itemID + "'"; dbase.stat1 =
		 * dbase.conn.prepareStatement(query2); dbase.stat1.executeUpdate(); return
		 * "delete"; } } else { return "norecord"; }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return "exception";
		 */

}


public String checkMessage(String username) {
	
	
		EntityManager em = null;
		EntityManager em2 = null;
		String changeMessage = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Query query = em.createQuery("select c from CartBean c where c.updateMessage = 'zero' and c.username =?1");
			query.setParameter(1, username);
			List rs = query.getResultList();
			int size = rs.size();
			if(size!=0) {
				changeMessage = " Some items have been updated according to availability!!!!!";
				Query query2 = em.createQuery("delete from CartBean c where c.updateMessage = 'zero' and c.username =?1");
				query2.setParameter(1, username);
				query2.executeUpdate();
				em.getTransaction().commit();
			}
			
			em2 = emf.createEntityManager();
			em2.getTransaction().begin();
			Query query3 = em2.createQuery("select c from CartBean c where c.updateMessage = 'yes' and c.username =?1");
			query3.setParameter(1, username);
			List rs2 = query3.getResultList();
			int size2 = rs2.size();
			if(size2!=0) {
				changeMessage = " Some items have been updated according to availability!!!!!";
				Query query4 = em2.createQuery("update CartBean c set c.updateMessage = 'no' where c.updateMessage = 'yes' and c.username =?1");
				query4.setParameter(1, username);
				query4.executeUpdate();
				em2.getTransaction().commit();
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			em.close();
			em2.close();
		}
	
	
	return changeMessage;
	
	
	
		/*
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB(); String
		 * changeMessage = null; try {
		 * 
		 * String query =
		 * "select * from cart where updateMessage = 'zero' and username = '" + username
		 * + "'"; dbase.stat = dbase.conn.prepareStatement(query); dbase.rs =
		 * dbase.stat.executeQuery();
		 *  while(dbase.rs.next()) { 
		 *  changeMessage = " Some items have been updated according to availability!!!!!" ; 
		 *  String query2 = "delete from cart where updateMessage = 'zero' and username ='" +
		 * username +"'"; 
		 * dbase.stat1 = dbase.conn.prepareStatement(query2);
		 * dbase.stat1.executeUpdate(); 
		 * }
		 *  String query3 =
		 * "select * from cart where updateMessage = 'yes' and username = '" + username
		 * + "'"; 
		 * dbase.stat = dbase.conn.prepareStatement(query3); 
		 * dbase.rs = dbase.stat.executeQuery(); 
		 * while(dbase.rs.next()) { 
		 * changeMessage = " Some items have been updated according to availability!!!!!" ; String
		 * query2 = " update cart set updateMessage = 'no' where updateMessage = 'yes' and username ='"
		 * + username +"'"; 
		 * dbase.stat2 = dbase.conn.prepareStatement(query2);
		 * dbase.stat2.executeUpdate(); } } catch (Exception e) { // TODO: handle
		 * exception e.printStackTrace(); }
		 * 
		 * return changeMessage;
		 */
	
}

public List<CartBean> getCart(String username) {
	
	
	EntityManager em = null;
	List<CartBean> cartList = null;
	
	try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select c from CartBean c where c.username =?1");
		query.setParameter(1, username);
		cartList = query.getResultList();
		
	}catch(Exception e) {
		System.out.println(e);
	}
	finally {
		em.close();
	}
	
	return cartList;
	
	
	
	
		/*
		 * List<CartBean> cartList = new ArrayList<CartBean>();
		 * 
		 * String query =
		 * "select c.itemid, i.itemName, i.itemPrice, c.itemQuantity, c.itemTotal " +
		 * "from cart c natural join item i " + "where username = '" + username + "'";
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB(); try {
		 * dbase.stat = dbase.conn.prepareStatement(query); dbase.rs =
		 * dbase.stat.executeQuery();
		 * 
		 * while(dbase.rs.next()) { //cartList.add(new CartBean(dbase.rs.getInt(1),
		 * dbase.rs.getString(2), dbase.rs.getInt(3), dbase.rs.getInt(4),
		 * dbase.rs.getInt(5))); }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return cartList;
		 */
}

public int getTotal(String username) {
	
	Integer total = 0;	
	EntityManager em = null;
	
	try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select SUM(c.itemTotal) from CartBean c where c.username =?1");
		query.setParameter(1, username);
		total = ((Long) query.getSingleResult()).intValue();
		System.out.println(total);
	}catch(Exception e) {
		System.out.println(e);
	}
	finally {
		em.close();
	}
	
	return total;
	
	
	
	
		/*
		 * String query = "select SUM(itemTotal) from cart where username = '" +
		 * username + "'"; connectDatabase dbase = new connectDatabase();
		 * dbase.connectingDB(); try { dbase.stat = dbase.conn.prepareStatement(query);
		 * dbase.rs = dbase.stat.executeQuery();
		 * 
		 * if(dbase.rs.next()) { total = dbase.rs.getInt(1); }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return total;
		 */
}

}
	

