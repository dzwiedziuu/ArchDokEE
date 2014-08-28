package com.aniedzwiedz.dokarchee.data.service.impl;

import java.util.List;

import javax.transaction.Transactional;

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

	@Transactional
	@Override
	public void add(Ar t)
	{
		arDao.add(t);
	}

	@Transactional
	@Override
	public void update(Ar t)
	{
		arDao.update(t);
	}

	@Transactional
	@Override
	public void remove(Ar t)
	{
		arDao.remove(t);
	}

	@Transactional
	@Override
	public Ar find(Ar t)
	{
		return arDao.find(t);
	}

	@Transactional
	@Override
	public List<Ar> getAll()
	{
		return arDao.getList(Ar.class);
	}

}
