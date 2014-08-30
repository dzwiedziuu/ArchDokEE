package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.GeneralDao;

@Service
public class GeneralService
{
	@Autowired
	private GeneralDao generalDao;

	@Transactional
	public List<?> getList(Class<?> objectClass, Criterion criterion)
	{
		return generalDao.getList(objectClass, criterion);
	}

	public String getIdentifierPropertyName(Class<?> classObj)
	{
		return generalDao.getIdentifierPropertyName(classObj);
	}
}
