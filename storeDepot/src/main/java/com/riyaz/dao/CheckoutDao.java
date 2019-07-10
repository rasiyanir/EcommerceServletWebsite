package com.riyaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.riyaz.bean.CartBean;
import com.riyaz.bean.CheckoutBean;
import com.riyaz.dbconnect.connectDatabase;

public class CheckoutDao {
	
	
	public List<CheckoutBean> getCheckout(String username) {
		
		List<CheckoutBean> checkoutList = new ArrayList<CheckoutBean>();
		
		String query = "select c.itemid, i.itemName, i.itemPrice, c.itemQuantity, c.itemTotal "
				 + "from cart c natural join item i "
				 + "where username = '" + username + "'";
		
		connectDatabase dbase = new connectDatabase();
		dbase.connectingDB();
		try {
			dbase.stat = dbase.conn.prepareStatement(query);
			dbase.rs = dbase.stat.executeQuery();
			
			while(dbase.rs.next()) {
				checkoutList.add(new CheckoutBean(dbase.rs.getInt(1), dbase.rs.getString(2), dbase.rs.getInt(3), dbase.rs.getInt(4), dbase.rs.getInt(5)));
				String query1 = "Insert into userpurchases values ('" + username + "', '" + dbase.rs.getInt(1) + "', '" + dbase.rs.getInt(4) + "','" + dbase.rs.getInt(5) + "')";
				dbase.stat1 = dbase.conn.prepareStatement(query1);
				dbase.stat1.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkoutList;
	}
	
	
	public void deleteCartUpdateItem(String username) {
		
		
		
		connectDatabase dbase = new connectDatabase();
		dbase.connectingDB();
		
		try {
			String query = "select itemID, itemQuantity from cart where username = '" + username+ "'";
			dbase.stat = dbase.conn.prepareStatement(query);
			dbase.rs = dbase.stat.executeQuery();
			while(dbase.rs.next()) {
				int cartItemID = dbase.rs.getInt(1);
				int cartItemQuantity = dbase.rs.getInt(2);
				String query1 = "select * from item where itemID = '"+ cartItemID + "'";
				dbase.stat1 = dbase.conn.prepareStatement(query1);
				dbase.rs1 = dbase.stat1.executeQuery();
				if(dbase.rs1.next()) {
					int stockQuantity = dbase.rs1.getInt(4);
					int actualQuantity = stockQuantity - cartItemQuantity;
					String query2 = "update item set itemCount = '" + actualQuantity+ "' where itemID = '" + cartItemID + "'";
					dbase.stat2 = dbase.conn.prepareStatement(query2);
					dbase.stat2.executeUpdate();
				}
			}
			
			String query3 = "delete from cart where username = '" + username + "'";
			dbase.stat3 = dbase.conn.prepareStatement(query3);
			dbase.stat3.executeUpdate();
			
			String query4 = "select c.username, c.itemid, c.itemQuantity, c.itemTotal, i.itemPrice from cart c natural join item i";
			dbase.stat4 = dbase.conn.prepareStatement(query4);
			dbase.rs3 = dbase.stat4.executeQuery();
			while(dbase.rs3.next()) {
				String fieldCartUsername = dbase.rs3.getString(1);
				int fieldCartItemID = dbase.rs3.getInt(2);
				int fieldCartCount = dbase.rs3.getInt(3);
				int fieldCartItemPrice = dbase.rs3.getInt(5);
					String query5 = "select * from item where itemID = '" + fieldCartItemID + "'";
					dbase.stat5 = dbase.conn.prepareStatement(query5);
					dbase.rs4 = dbase.stat5.executeQuery();
					if(dbase.rs4.next()) {
						int stockCount = dbase.rs4.getInt(4);
						if(stockCount == 0) {
							String query6 = "update cart set itemQuantity = '0', itemTotal = '0', updateMessage = 'zero' where itemID = '" + fieldCartItemID + "' and username = '" + fieldCartUsername +"'";
							dbase.stat6 = dbase.conn.prepareStatement(query6);
							dbase.stat6.executeUpdate();
						}
						else if(fieldCartCount > stockCount) {
							String query7 = "update cart set itemQuantity = '" + stockCount + "', itemTotal = '" + (stockCount*fieldCartItemPrice) +"', updateMessage = 'yes' where itemID = '" + fieldCartItemID + "' and username = '" + fieldCartUsername +"'";
							dbase.stat7 = dbase.conn.prepareStatement(query7);
							dbase.stat7.executeUpdate();
						}
					}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
