package net.virtela.enrollmentsystem.service;

import net.virtela.enrollmentsystem.dao.UserDao;
import net.virtela.enrollmentsystem.model.User;

public class SecurityService {

	private UserDao userDao = ServiceRegistry.getInstance(UserDao.class);

	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
