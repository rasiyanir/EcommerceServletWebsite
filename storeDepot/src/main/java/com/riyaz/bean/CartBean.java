package com.riyaz.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CartPK.class)
@Table(name = "cart")
public class CartBean {
	@Id
	private String username;
	@Id
	private int itemID;
	private String itemName;
	private int itemPrice;
	private int itemQuantity;
	private int itemTotal;
	private String updateMessage;
	
	
	public CartBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartBean(String username, int itemID, String itemName, int itemPrice, int itemQuantity, int itemTotal,
			String updateMessage) {
		super();
		this.username = username;
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemTotal = itemTotal;
		this.updateMessage = updateMessage;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public int getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(int itemTotal) {
		this.itemTotal = itemTotal;
	}
	public String getUpdateMessage() {
		return updateMessage;
	}
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}
	@Override
	public String toString() {
		return "CartBean [username=" + username + ", itemID=" + itemID + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice + ", itemQuantity=" + itemQuantity + ", itemTotal=" + itemTotal + ", updateMessage="
				+ updateMessage + "]";
	}
	
	
	
	
	
	
}
