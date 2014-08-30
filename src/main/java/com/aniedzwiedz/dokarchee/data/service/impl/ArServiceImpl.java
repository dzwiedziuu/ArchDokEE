package com.aniedzwiedz.dokarchee.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.dao.custom.ArDao;
import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.data.service.AbstractService;
import com.aniedzwiedz.dokarchee.data.service.ArService;

@Service
public class ArServiceImpl extends AbstractService<Ar> implements ArService
{
	@Autowired
	private ArDao arDao;

	@Override
	protected AbstractDao<Ar> getDaoObj()
	{
		return arDao;
	}
}
