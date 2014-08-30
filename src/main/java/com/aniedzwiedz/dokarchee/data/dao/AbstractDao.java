package com.aniedzwiedz.dokarchee.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.common.utils.ReflectionUtils;

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

	public List<T> getList(Class<T> classObj, Criterion criterion)
	{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(classObj);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.list();
	}

	public T find(T t)
	{
		Class<T> classObj = (Class<T>) t.getClass();
		String propertyId = generalDao.getIdentifierPropertyName(classObj);
		Serializable idValue = (Serializable) ReflectionUtils.getObjectPropertyValue(t, propertyId);
		if (idValue == null)
			return null;
		return (T) sessionFactory.getCurrentSession().get(classObj, idValue);
	}

	public void remove(T t)
	{
		sessionFactory.getCurrentSession().delete(t);
	}

	public void update(T t)
	{
		sessionFactory.getCurrentSession().update(t);
	}
}
