package com.aniedzwiedz.dokarchee.data.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public List<?> getList(Class<?> objectClass)
	{
		return sessionFactory.getCurrentSession().createCriteria(objectClass).list();
	}

	public String getIdentifierPropertyName(Class<?> classObj)
	{
		return sessionFactory.getClassMetadata(classObj).getIdentifierPropertyName();
	}
}
