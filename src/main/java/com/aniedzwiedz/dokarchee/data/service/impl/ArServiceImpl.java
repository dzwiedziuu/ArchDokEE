package com.aniedzwiedz.dokarchee.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.dao.custom.AreDao;
import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.service.AbstractService;
import com.aniedzwiedz.dokarchee.data.service.ArService;

@Service
public class ArServiceImpl extends AbstractService<Are> implements ArService
{
	@Autowired
	private AreDao arDao;

	@Override
	protected AbstractDao<Are> getDaoObj()
	{
		return arDao;
	}
}
