package com.riyaz.service;

import java.util.List;

import com.riyaz.bean.CheckoutBean;
import com.riyaz.dao.CheckoutDao;

public class CheckoutService {

		private CheckoutDao checkoutDao;
		
		public CheckoutService() {
			checkoutDao = new CheckoutDao();
		}
		
		public List<CheckoutBean> gettingCheckout(String username) {
			return checkoutDao.getCheckout(username);
		}
		
		public void deletingCartUpdatingItem(String username) {
			checkoutDao.deleteCartUpdateItem(username);
		}
}
