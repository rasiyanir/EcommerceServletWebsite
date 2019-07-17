package com.riyaz.service;

import java.util.List;

import com.riyaz.bean.CartBean;
import com.riyaz.dao.CartDao;



public class CartService {

	private CartDao cartDao;
	
	public CartService() {
		cartDao = new CartDao();
	}
	
	public String addProduct(String username, int itemID, int quantity, int price, String itemName) {
		return cartDao.addToCart(username, itemID, quantity, price, itemName);
	}
	
	public String deleteProduct(String username, int itemID, int quantity, int price) {
		return cartDao.deleteFromCart(username, itemID, quantity, price);
	}
	
	public String checkingMessage(String username) {
		return cartDao.checkMessage(username);
	}
	
	public List<CartBean> gettingCart(String username) {
		return cartDao.getCart(username);
	}
	
	public int getFinalTotal(String username) {
		return cartDao.getTotal(username);
	}
}
