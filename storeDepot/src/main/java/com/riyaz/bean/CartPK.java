package com.riyaz.bean;

import java.io.Serializable;

import javax.persistence.Id;

public class CartPK implements Serializable {
	private String username;
	private int itemID;
	public CartPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
}
