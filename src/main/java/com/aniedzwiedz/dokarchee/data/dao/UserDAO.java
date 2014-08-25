package com.aniedzwiedz.dokarchee.data.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.model.User;

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
	
	public User findUser(User user)
	{
		if(user.getId() == null)
			return null;
		return (User) sessionFactory.getCurrentSession().get(User.class, user.getId());
	}

	public void removeUser(User user)
	{
		sessionFactory.getCurrentSession().delete(findUser(user));
	}

	public void update(User user)
	{
		sessionFactory.getCurrentSession().update(user);
	}
}
