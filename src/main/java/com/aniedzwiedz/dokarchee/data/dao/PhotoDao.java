package com.aniedzwiedz.dokarchee.data.dao;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.model.Photo;

@Repository
public class PhotoDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void addPhoto(Photo photo)
	{
		sessionFactory.getCurrentSession().save(photo);
	}

	public List<Photo> getAllPhotos()
	{
		// return
		// sessionFactory.getCurrentSession().createQuery("from Photo").list();
		return sessionFactory.getCurrentSession().createCriteria(Photo.class).setFetchMode("", FetchMode.JOIN).list();
	}

	public Photo findPhoto(Photo photo)
	{
		if (photo.getId() == null)
			return null;
		return (Photo) sessionFactory.getCurrentSession().get(Photo.class, photo.getId());
	}

	public void removePhoto(Photo photo)
	{
		sessionFactory.getCurrentSession().delete(findPhoto(photo));
	}

	public void updatePhoto(Photo photo)
	{
		sessionFactory.getCurrentSession().update(photo);
	}
}
