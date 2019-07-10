package com.riyaz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.riyaz.bean.CartBean;
import com.riyaz.bean.ProductBean;
import com.riyaz.dbconnect.connectDatabase;

public class CartDao {

	
public String addToCart(String username, int itemID, int quantity, int price) {
	
		
		int itemTotal = quantity*price;
		int stockCount = 0;
		int cartCount = 0;
		connectDatabase dbase = new connectDatabase();
		dbase.connectingDB();
		try {
			String query1 = "select * from cart where username = '" + username + "' and itemID = '" + itemID + "'";
			dbase.stat = dbase.conn.prepareStatement(query1);
			dbase.rs = dbase.stat.executeQuery();
			if(dbase.rs.next()) {
				String query2  = "select c.username, c.itemID, c.itemQuantity, i.itemCount from cart c natural join item i  where c.itemID = '"+ itemID +"' and c.username = '" + username + "' group by itemID";
				dbase.stat3 = dbase.conn.prepareStatement(query2);
				dbase.rs1 = dbase.stat3.executeQuery();
				if(dbase.rs1.next()) {
					stockCount = dbase.rs1.getInt(4);
					cartCount = dbase.rs1.getInt(3);
				}
				if(quantity <= (stockCount - cartCount)) {
					int qty = dbase.rs.getInt(3);
					int tot = dbase.rs.getInt(4);
					int totalQuantity = qty + quantity;
					int finalTotal = tot + itemTotal;
					String query3 = "update cart set itemQuantity = '" + totalQuantity + "', itemTotal = '" + finalTotal + "' where username = '" + username + "' and itemID = '" + itemID + "'";
					dbase.stat1 = dbase.conn.prepareStatement(query3);
					dbase.stat1.executeUpdate();
					return "add";
				}
				else {
					return "excessQuantity";
				}		
			}
			else {
				String query4 = "Insert into cart values ('" + username + "', '" + itemID + "', '" + quantity + "','" + itemTotal + "', 'no');";
				dbase.stat2 = dbase.conn.prepareStatement(query4);
				dbase.stat2.executeUpdate();
				return "add";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "exception";
	
	}


public String deleteFromCart(String username, int itemID, int quantity, int price) {
	
	String query1 = "select * from cart where username = '" + username + "' and itemID = '" + itemID + "'";
	int itemTotal = quantity*price;
	connectDatabase dbase = new connectDatabase();
	dbase.connectingDB();
	try {
		dbase.stat = dbase.conn.prepareStatement(query1);
		dbase.rs = dbase.stat.executeQuery();
		if(dbase.rs.next()) {
			int qty = dbase.rs.getInt(3);
			int tot = dbase.rs.getInt(4);
			int totalQuantity = qty - quantity;
			int finalTotal = tot - itemTotal;
				if(totalQuantity <= 0) {
					String query3 = "delete from cart where username = '" + username + "' and itemID = '" + itemID + "'";
					dbase.stat1 = dbase.conn.prepareStatement(query3);
					dbase.stat1.executeUpdate();
					return "delete";
				}
				else {
					String query2 = "update cart set itemQuantity = '" + totalQuantity + "', itemTotal = '" + finalTotal + "' where username = '" + username + "' and itemID = '" + itemID + "'";
					dbase.stat1 = dbase.conn.prepareStatement(query2);
					dbase.stat1.executeUpdate();
					return "delete";
				}
		}
		else {
			return "norecord";
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "exception";

}


public String checkMessage(String username) {
	connectDatabase dbase = new connectDatabase();
	dbase.connectingDB();
	String changeMessage = null;
	try {
		
		String query = "select * from cart where updateMessage = 'zero' and username = '" + username + "'";
		dbase.stat = dbase.conn.prepareStatement(query);
		dbase.rs = dbase.stat.executeQuery();
		while(dbase.rs.next()) {
			changeMessage = " Some items have been updated according to availability!!!!!" ;
			String query2 = "delete from cart where updateMessage = 'zero' and username ='" + username +"'";
			dbase.stat1 = dbase.conn.prepareStatement(query2);
			dbase.stat1.executeUpdate();
		}
		String query3 = "select * from cart where updateMessage = 'yes' and username = '" + username + "'";
		dbase.stat = dbase.conn.prepareStatement(query3);
		dbase.rs = dbase.stat.executeQuery();
		while(dbase.rs.next()) {
			changeMessage = " Some items have been updated according to availability!!!!!" ;
			String query2 = " update cart set updateMessage = 'no' where updateMessage = 'yes' and username ='" + username +"'";
			dbase.stat2 = dbase.conn.prepareStatement(query2);
			dbase.stat2.executeUpdate();
		}
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return changeMessage;
	
}

public List<CartBean> getCart(String username) {
	
	List<CartBean> cartList = new ArrayList<CartBean>();
	
	String query = "select c.itemid, i.itemName, i.itemPrice, c.itemQuantity, c.itemTotal "
				 + "from cart c natural join item i "
				 + "where username = '" + username + "'";
	connectDatabase dbase = new connectDatabase();
	dbase.connectingDB();
	try {
		dbase.stat = dbase.conn.prepareStatement(query);
		dbase.rs = dbase.stat.executeQuery();
		
		while(dbase.rs.next()) {
			cartList.add(new CartBean(dbase.rs.getInt(1), dbase.rs.getString(2), dbase.rs.getInt(3), dbase.rs.getInt(4), dbase.rs.getInt(5)));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return cartList;
}

public int getTotal(String username) {
	
	int total = 0;
	
	String query = "select SUM(itemTotal) from cart where username = '" + username + "'";
	connectDatabase dbase = new connectDatabase();
	dbase.connectingDB();
	try {
		dbase.stat = dbase.conn.prepareStatement(query);
		dbase.rs = dbase.stat.executeQuery();
		
		if(dbase.rs.next()) {
			total = dbase.rs.getInt(1);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return total;
}

}
	

