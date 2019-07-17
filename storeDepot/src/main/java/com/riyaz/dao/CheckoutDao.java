package com.riyaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.riyaz.bean.CartBean;
import com.riyaz.bean.CheckoutBean;
import com.riyaz.bean.ProductBean;
import com.riyaz.dbconnect.connectDatabase;

public class CheckoutDao {
	
	
	public List<CheckoutBean> getCheckout(String username) {
		
		
		
		EntityManager em = null;
		EntityManager em2 = null;
		List<CheckoutBean> checkoutList = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("select c from CartBean c where c.username =?1");
			query.setParameter(1, username);
			List<CartBean> rs = query.getResultList();
			for(int i = 0; i < rs.size();i++) {
				CartBean c = rs.get(i);
				em.persist(new CheckoutBean(username, c.getItemID(), c.getItemName(), c.getItemPrice(), c.getItemQuantity(), c.getItemTotal()));
			}
			em.getTransaction().commit();
			em2 = emf.createEntityManager();
			em2.getTransaction().begin();
			Query query2 = em2.createQuery("select ch from CheckoutBean ch where ch.username =?1");
			query2.setParameter(1, username);
			checkoutList = query2.getResultList();
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			em.close();
		}
		
		return checkoutList;
			
		
		
		
		
		
		
		/*
		 * List<CheckoutBean> checkoutList = new ArrayList<CheckoutBean>();
		 * 
		 * String query =
		 * "select c.itemid, i.itemName, i.itemPrice, c.itemQuantity, c.itemTotal " +
		 * "from cart c natural join item i " + "where username = '" + username + "'";
		 * 
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB(); try {
		 * dbase.stat = dbase.conn.prepareStatement(query); dbase.rs =
		 * dbase.stat.executeQuery();
		 * 
		 * while(dbase.rs.next()) { checkoutList.add(new
		 * CheckoutBean(dbase.rs.getInt(1), dbase.rs.getString(2), dbase.rs.getInt(3),
		 * dbase.rs.getInt(4), dbase.rs.getInt(5))); String query1 =
		 * "Insert into userpurchases values ('" + username + "', '" +
		 * dbase.rs.getInt(1) + "', '" + dbase.rs.getInt(4) + "','" + dbase.rs.getInt(5)
		 * + "')"; dbase.stat1 = dbase.conn.prepareStatement(query1);
		 * dbase.stat1.executeUpdate(); }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return checkoutList;
		 */
	}
	
	
public void deleteCartUpdateItem(String username) {
		
		
		
	EntityManager em = null;
	EntityManager em2 = null;
	EntityManager em3 = null;
			
			try {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("project");
				em = emf.createEntityManager();
				em.getTransaction().begin();
				
				Query query = em.createQuery("select c.itemID, c.itemQuantity from CartBean c where c.username =?1");
				query.setParameter(1, username);
				List rs = query.getResultList();
				for(int i = 0; i < rs.size();i++) {
					Object[] c = (Object[]) rs.get(i);
					int cartItemID = (int) c[0];
					int cartItemQuantity = (int) c[1];
						Query query2 = em.createQuery("select p from ProductBean p where p.itemID =?1");
						query2.setParameter(1, cartItemID);
						List rs1 = query2.getResultList();
						if(!(rs1.isEmpty())) {
							ProductBean p = (ProductBean) rs1.get(0);
							int stockQuantity = p.getItemCount();
							int actualQuantity = stockQuantity - cartItemQuantity;
							Query query3 = em.createQuery("update ProductBean p set p.itemCount =?1 where itemID =?2");
							query3.setParameter(1, actualQuantity);
							query3.setParameter(2, cartItemID);
							query3.executeUpdate();
							
							
						}
						
				}
				em.getTransaction().commit();
				
				
				em2 = emf.createEntityManager();
				em2.getTransaction().begin();
				Query query4 = em2.createQuery("delete from CartBean c where c.username =?1");
				query4.setParameter(1, username);
				query4.executeUpdate();
				em2.getTransaction().commit();
				
				em3 = emf.createEntityManager();
				em3.getTransaction().begin();
				Query query5 = em3.createQuery("select c from CartBean c");
				List rs2 = query5.getResultList();
				int size = rs2.size();
				if(size != 0) {
					for(int i = 0; i < size; i++) {
						CartBean c = (CartBean) rs2.get(i);
						String fieldCartUsername = c.getUsername();
						int fieldCartItemID = c.getItemID();
						int fieldCartCount = c.getItemQuantity();
						int fieldCartItemPrice = c.getItemPrice();
						Query query6 = em3.createQuery("select p from ProductBean p where p.itemID =?1");
						query6.setParameter(1, fieldCartItemID);
						List rs3 = query6.getResultList();
						if(!rs3.isEmpty()) {
							ProductBean p = (ProductBean) rs3.get(0);
							int stockCount = p.getItemCount();
							if(stockCount == 0) {
								Query query7 = em3.createQuery("update CartBean c set c.itemQuantity = '0', c.itemTotal = '0', c.updateMessage = 'zero' where c.itemID =?1 and c.username =?2");
								query7.setParameter(1, fieldCartItemID);
								query7.setParameter(2, fieldCartUsername);
								query7.executeUpdate();
								
							}
							else if(fieldCartCount > stockCount) {
								Query query8 = em3.createQuery("update CartBean c set c.itemQuantity =?1, c.itemTotal =?2, c.updateMessage = 'yes' where c.itemID =?3 and c.username =?4");
								query8.setParameter(1, stockCount);
								query8.setParameter(2, stockCount*fieldCartItemPrice);
								query8.setParameter(3, fieldCartItemID);
								query8.setParameter(4, fieldCartUsername);
								query8.executeUpdate();
							
							}
						}
					}
					em3.getTransaction().commit();
				}
				
				
			}catch(Exception e) {
				System.out.println(e);
			}
			finally {
				em.close();
				em2.close();
				em3.close();
			}
			
		
		
		
		
		
		
		
		/*
		 * connectDatabase dbase = new connectDatabase(); dbase.connectingDB();
		 * 
		 * try { String query =
		 * "select itemID, itemQuantity from cart where username = '" + username+ "'";
		 * dbase.stat = dbase.conn.prepareStatement(query); dbase.rs =
		 * dbase.stat.executeQuery(); while(dbase.rs.next()) { int cartItemID =
		 * dbase.rs.getInt(1); int cartItemQuantity = dbase.rs.getInt(2); String query1
		 * = "select * from item where itemID = '"+ cartItemID + "'"; dbase.stat1 =
		 * dbase.conn.prepareStatement(query1); dbase.rs1 = dbase.stat1.executeQuery();
		 * if(dbase.rs1.next()) { int stockQuantity = dbase.rs1.getInt(4); int
		 * actualQuantity = stockQuantity - cartItemQuantity; String query2 =
		 * "update item set itemCount = '" + actualQuantity+ "' where itemID = '" +
		 * cartItemID + "'"; dbase.stat2 = dbase.conn.prepareStatement(query2);
		 * dbase.stat2.executeUpdate(); } }
		 * 
		 * String query3 = "delete from cart where username = '" + username + "'";
		 * dbase.stat3 = dbase.conn.prepareStatement(query3);
		 * dbase.stat3.executeUpdate();
		 * 
		 * String query4 =
		 * "select c.username, c.itemid, c.itemQuantity, c.itemTotal, i.itemPrice from cart c natural join item i"
		 * ; dbase.stat4 = dbase.conn.prepareStatement(query4); dbase.rs3 =
		 * dbase.stat4.executeQuery(); while(dbase.rs3.next()) { String
		 * fieldCartUsername = dbase.rs3.getString(1); int fieldCartItemID =
		 * dbase.rs3.getInt(2); int fieldCartCount = dbase.rs3.getInt(3); int
		 * fieldCartItemPrice = dbase.rs3.getInt(5); String query5 =
		 * "select * from item where itemID = '" + fieldCartItemID + "'"; dbase.stat5 =
		 * dbase.conn.prepareStatement(query5); dbase.rs4 = dbase.stat5.executeQuery();
		 * if(dbase.rs4.next()) { int stockCount = dbase.rs4.getInt(4); if(stockCount ==
		 * 0) { String query6 =
		 * "update cart set itemQuantity = '0', itemTotal = '0', updateMessage = 'zero' where itemID = '"
		 * + fieldCartItemID + "' and username = '" + fieldCartUsername +"'";
		 * dbase.stat6 = dbase.conn.prepareStatement(query6);
		 * dbase.stat6.executeUpdate(); } else if(fieldCartCount > stockCount) { String
		 * query7 = "update cart set itemQuantity = '" + stockCount + "', itemTotal = '"
		 * + (stockCount*fieldCartItemPrice)
		 * +"', updateMessage = 'yes' where itemID = '" + fieldCartItemID +
		 * "' and username = '" + fieldCartUsername +"'"; dbase.stat7 =
		 * dbase.conn.prepareStatement(query7); dbase.stat7.executeUpdate(); } } } }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
		
		
		
		
		
	}
}
