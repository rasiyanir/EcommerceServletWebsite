package com.riyaz.bean;

public class CartBean {

	private int itemID;
	private String itemName;
	private int itemPrice;
	private int itemQuantity;
	private int itemTotal;
	public CartBean(int itemID, String itemName, int itemPrice, int itemQuantity, int itemTotal) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemTotal = itemTotal;
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
		return "CartBean [itemID=" + itemID + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemQuantity="
				+ itemQuantity + ", itemTotal=" + itemTotal + "]";
	}
	
	
	
	
}
