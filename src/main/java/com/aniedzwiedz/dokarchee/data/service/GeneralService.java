package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.GeneralDao;

@Service
public class GeneralService
{
	@Autowired
	private GeneralDao generalDao;

	@Transactional
	public List<?> getList(Class<?> objectClass)
	{
		return generalDao.getList(objectClass);
	}

	public String getIdentifierPropertyName(Class<?> classObj)
	{
		return generalDao.getIdentifierPropertyName(classObj);
	}
}
