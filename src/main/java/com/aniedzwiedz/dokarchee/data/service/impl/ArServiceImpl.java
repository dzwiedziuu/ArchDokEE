package com.aniedzwiedz.dokarchee.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.ArDao;
import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.data.service.ArService;

@Service
public class ArServiceImpl implements ArService
{
	@Autowired
	private ArDao arDao;

	@Override
	public void add(Ar t)
	{
		arDao.add(t);
	}

	@Override
	public void update(Ar t)
	{
		arDao.update(t);
	}

	@Override
	public void remove(Ar t)
	{
		arDao.remove(t);
	}

	@Override
	public Ar find(Ar t)
	{
		return arDao.find(t);
	}

	@Override
	public List<Ar> getAll()
	{
		return arDao.getList(Ar.class);
	}

}
