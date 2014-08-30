package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

import javax.transaction.Transactional;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;

public abstract class AbstractService<T> implements PojoService<T>
{
	protected abstract AbstractDao<T> getDaoObj();

	@Transactional
	@Override
	public void add(T t)
	{
		getDaoObj().add(t);
	}

	@Transactional
	@Override
	public void update(T t)
	{
		getDaoObj().update(t);
	}

	@Transactional
	@Override
	public void remove(T t)
	{
		getDaoObj().remove(t);
	}

	@Transactional
	@Override
	public T find(T t)
	{
		return getDaoObj().find(t);
	}

	@Transactional
	@Override
	public List<T> getAll(Class<T> clazz)
	{
		return getDaoObj().getList(clazz);
	}
}
