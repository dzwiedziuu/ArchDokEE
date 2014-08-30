package com.aniedzwiedz.dokarchee.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.dao.custom.PhotoDao;
import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.AbstractService;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;

@Service
public class PhotoServiceImpl extends AbstractService<Photo> implements PhotoService
{
	@Autowired
	private PhotoDao photoDao;

	@Override
	protected AbstractDao<Photo> getDaoObj()
	{
		return photoDao;
	}
}
