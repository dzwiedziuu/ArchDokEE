package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.UserDAO;
import com.aniedzwiedz.dokarchee.data.model.User;

@Service
public class UserService implements PojoService<User>
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

	@Transactional
	public void removeUser(User user)
	{
		userDAO.removeUser(user);
	}

	@Transactional
	@Override
	public void add(User user)
	{
		addUser(user);
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
		removeUser(user);
	}

	@Transactional
	@Override
	public List<User> getAll()
	{
		return getAllUsers();
	}

	@Transactional
	@Override
	public User find(User user)
	{
		return userDAO.findUser(user);
	}

}
