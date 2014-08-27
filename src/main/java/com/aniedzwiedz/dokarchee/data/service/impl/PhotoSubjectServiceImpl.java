package com.aniedzwiedz.dokarchee.data.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.PhotoSubjectDao;
import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.service.PhotoSubjectService;

@Service
public class PhotoSubjectServiceImpl implements PhotoSubjectService
{
	@Autowired
	private PhotoSubjectDao photoSubjectDao;

	@Transactional
	@Override
	public void add(PhotoSubject t)
	{
		photoSubjectDao.add(t);
	}

	@Transactional
	@Override
	public void update(PhotoSubject t)
	{
		photoSubjectDao.update(t);
	}

	@Transactional
	@Override
	public void remove(PhotoSubject t)
	{
		photoSubjectDao.remove(t);
	}

	@Transactional
	@Override
	public PhotoSubject find(PhotoSubject t)
	{
		return photoSubjectDao.find(t);
	}

	@Transactional
	@Override
	public List<PhotoSubject> getAll()
	{
		return photoSubjectDao.getList(PhotoSubject.class);
	}

}
