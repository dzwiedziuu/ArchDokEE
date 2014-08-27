package com.aniedzwiedz.dokarchee.data.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.UserDAO;
import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public void add(User user)
	{
		userDAO.add(user);
	}

	@Transactional
	@Override
	public void update(User user)
	{
		userDAO.update(user);
	}

	@Transactional
	@Override
	public void remove(User user)
	{
		userDAO.remove(user);
	}

	@Transactional
	@Override
	public List<User> getAll()
	{
		return userDAO.getList(User.class);
	}

	@Transactional
	@Override
	public User find(User user)
	{
		return userDAO.find(user);
	}

}
