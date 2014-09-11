package com.aniedzwiedz.dokarchee.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public class GeneralDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public List<?> getList(Class<?> objectClass, Criterion criterion)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(objectClass);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.list();
	}

	public String getIdentifierPropertyName(Class<?> classObj)
	{
		return sessionFactory.getClassMetadata(classObj).getIdentifierPropertyName();
	}
}
