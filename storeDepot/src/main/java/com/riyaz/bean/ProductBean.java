package com.riyaz.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ProductBean {
	@Id
	private int itemID;
	private String itemName;
	private int itemPrice;
	private int itemCount;
	
	public ProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductBean(int itemID, String itemName, int itemPrice, int itemCount) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
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
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	@Override
	public String toString() {
		return "ProductBean [itemID=" + itemID + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount="
				+ itemCount + "]";
	}
	
	
}
