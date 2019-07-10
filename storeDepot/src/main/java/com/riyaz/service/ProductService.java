package com.riyaz.service;

import java.util.List;

import com.riyaz.bean.ProductBean;
import com.riyaz.dao.ProductDao;

public class ProductService {
	
private ProductDao productDao;
	
	public ProductService() {
		productDao = new ProductDao();
	}
	
	public List<ProductBean> gettingProduct() {
		return productDao.getProduct();
	}
}
