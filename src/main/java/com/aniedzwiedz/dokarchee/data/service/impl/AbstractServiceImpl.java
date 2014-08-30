package com.aniedzwiedz.dokarchee.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.dao.AbstractDaoImpl;
import com.aniedzwiedz.dokarchee.data.service.AbstractService;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;

@Service
public class AbstractServiceImpl<T> extends AbstractService<T> implements AbstractServiceInterface<T>
{
	@Autowired
	private AbstractDaoImpl<T> abstractDaoImpl;

	@Override
	protected AbstractDao<T> getDaoObj()
	{
		return abstractDaoImpl;
	}

}
