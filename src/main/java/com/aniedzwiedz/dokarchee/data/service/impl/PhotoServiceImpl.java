package com.aniedzwiedz.dokarchee.data.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.PhotoDao;
import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService
{
	@Autowired
	private PhotoDao photoDao;

	@Transactional
	@Override
	public void add(Photo photo)
	{
		photoDao.addPhoto(photo);
	}

	@Transactional
	@Override
	public void update(Photo photo)
	{
		photoDao.updatePhoto(photo);
	}

	@Transactional
	@Override
	public void remove(Photo photo)
	{
		photoDao.removePhoto(photo);
	}

	@Transactional
	@Override
	public Photo find(Photo photo)
	{
		return photoDao.findPhoto(photo);
	}

	@Transactional
	@Override
	public List<Photo> getAll()
	{
		return photoDao.getAllPhotos();
	}
}
