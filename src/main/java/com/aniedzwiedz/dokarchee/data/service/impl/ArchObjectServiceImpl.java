package com.aniedzwiedz.dokarchee.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.dao.custom.ArchObjectDao;
import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.service.AbstractService;
import com.aniedzwiedz.dokarchee.data.service.ArchObjectService;

@Service
public class ArchObjectServiceImpl extends AbstractService<ArchObject> implements ArchObjectService
{
	@Autowired
	private ArchObjectDao archObjectDao;

	@Override
	protected AbstractDao<ArchObject> getDaoObj()
	{
		return archObjectDao;
	}
}
