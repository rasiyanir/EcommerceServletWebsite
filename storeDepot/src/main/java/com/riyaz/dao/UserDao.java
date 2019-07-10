package com.riyaz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.riyaz.dbconnect.connectDatabase;

public class UserDao {
	
	
	public boolean checkInDB(String user, String pass) {
		
		String query = "select * from user where username = ? and password = ?";
		connectDatabase dbase = new connectDatabase();
		dbase.connectingDB();
		try {
			dbase.stat = dbase.conn.prepareStatement(query);
			dbase.stat.setString(1, user);
			dbase.stat.setString(2, pass);
			dbase.rs = dbase.stat.executeQuery();
			
			if(dbase.rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
