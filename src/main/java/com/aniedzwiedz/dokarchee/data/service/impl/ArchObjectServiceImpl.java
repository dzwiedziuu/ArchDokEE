package com.aniedzwiedz.dokarchee.data.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.ArchObjectDao;
import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.service.ArchObjectService;

@Service
public class ArchObjectServiceImpl implements ArchObjectService
{
	@Autowired
	private ArchObjectDao archObjectDao;

	@Transactional
	@Override
	public void add(ArchObject t)
	{
		archObjectDao.add(t);
	}

	@Transactional
	@Override
	public void update(ArchObject t)
	{
		archObjectDao.update(t);
	}

	@Transactional
	@Override
	public void remove(ArchObject t)
	{
		archObjectDao.remove(t);
	}

	@Transactional
	@Override
	public ArchObject find(ArchObject t)
	{
		return archObjectDao.find(t);
	}

	@Transactional
	@Override
	public List<ArchObject> getAll()
	{
		return archObjectDao.getList(ArchObject.class);
	}
}
