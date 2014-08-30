package com.aniedzwiedz.dokarchee.data.dao.custom;

import java.util.List;

import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.model.Photo;

@Repository
public class PhotoDao extends AbstractDao<Photo>
{
	@Override
	public List<Photo> getList(Class<Photo> classObj)
	{
		return getSessionFactory().getCurrentSession().createCriteria(Photo.class).setFetchMode("", FetchMode.JOIN).list();
	}
}
