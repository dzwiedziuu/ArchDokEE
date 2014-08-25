package com.aniedzwiedz.dokarchee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.dao.UserDAO;
import com.aniedzwiedz.dokarchee.model.User;

@Service
public class UserService
{
	@Autowired
	private UserDAO userDAO;

	@Transactional
	public List<User> getAllUsers()
	{
		return userDAO.getAllUsers();
	}

	@Transactional
	public void addUser(User user)
	{
		userDAO.addUser(user);
	}

}
