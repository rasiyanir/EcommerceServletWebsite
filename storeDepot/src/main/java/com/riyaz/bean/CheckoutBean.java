package com.riyaz.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "userpurchases")
public class CheckoutBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseID;
	private String username;
	private int itemID;
	private String itemName;
	private int itemPrice;
	private int itemQuantity;
	private int itemTotal;
	public CheckoutBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckoutBean(String username, int itemID, String itemName, int itemPrice, int itemQuantity, int itemTotal) {
		super();
		this.username = username;
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemTotal = itemTotal;
	}
	
	public int getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
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
	@Override
	public String toString() {
		return "CheckoutBean [purchaseID=" + purchaseID + ", username=" + username + ", itemID=" + itemID
				+ ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemQuantity=" + itemQuantity
				+ ", itemTotal=" + itemTotal + "]";
	}
	
}
