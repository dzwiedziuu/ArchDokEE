package com.aniedzwiedz.dokarchee.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.model.utils.ModelUtils;

@Repository
public class AbstractDao<T> implements Dao<T>
{
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GeneralDao generalDao;

	protected SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void add(T t)
	{
		sessionFactory.getCurrentSession().save(t);
	}

	public List<T> getList(Class<T> classObj)
	{
		return sessionFactory.getCurrentSession().createCriteria(classObj).list();
	}

	public T find(T t)
	{
		Class<T> classObj = (Class<T>) t.getClass();
		String propertyId = generalDao.getIdentifierPropertyName(classObj);
		Serializable idValue = (Serializable) ModelUtils.getObjectId(t, propertyId);
		if (idValue == null)
			return null;
		return (T) sessionFactory.getCurrentSession().get(classObj, idValue);
	}

	public void remove(T t)
	{
		sessionFactory.getCurrentSession().delete(find(t));
	}

	public void update(T t)
	{
		sessionFactory.getCurrentSession().update(t);
	}
}
