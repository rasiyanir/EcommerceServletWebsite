package com.riyaz.service;

import com.riyaz.dao.UserDao;

public class LoginService {

	private UserDao userDao;
	
	public LoginService() {
		userDao = new UserDao();
	}
	
	public boolean verifyUser(String user, String pass) {
		return userDao.checkInDB(user, pass);
	}
	
}
