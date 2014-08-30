package com.aniedzwiedz.dokarchee.data.dao.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.model.Photo;

@Repository
public class PhotoDao extends AbstractDao<Photo>
{
	@Override
	public List<Photo> getList(Class<Photo> classObj, Criterion criterion)
	{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Photo.class);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.setFetchMode("", FetchMode.JOIN).list();
	}
}
