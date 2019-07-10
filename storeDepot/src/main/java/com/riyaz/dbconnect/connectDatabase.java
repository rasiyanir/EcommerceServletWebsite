package com.riyaz.dbconnect;

import java.sql.*;

public class connectDatabase {
	
	public Connection conn;
	public PreparedStatement stat = null;
	public PreparedStatement stat1 = null;
	public PreparedStatement stat2 = null;
	public PreparedStatement stat3 = null;
	public PreparedStatement stat4 = null;
	public PreparedStatement stat5 = null;
	public PreparedStatement stat6 = null;
	public PreparedStatement stat7 = null;
	public ResultSet rs = null;
	public ResultSet rs1 = null;
	public ResultSet rs2 = null;
	public ResultSet rs3 = null;
	public ResultSet rs4 = null;
	public ResultSet rs5 = null;
	public ResultSet rs6 = null;
	public int rsu;
	public int rsi;
	public void connectingDB() {
		
		String url = "jdbc:mysql://localhost:3306/riyaz";
		String username = "root";
		String password = "Riyaz@123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
	
	public void closeDB() throws SQLException {
		if(conn != null) {
			conn.close();
		}
		if(stat != null) {
			stat.close();
		}
		if(rs != null) {
			rs.close();
		}
		
	}
}
