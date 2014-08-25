package com.aniedzwiedz.dokarchee.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.model.User;

@Repository
public class UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user)
	{
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}
}
