package com.aniedzwiedz.dokarchee.data.dao.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.model.ArchObject;

@Repository
public class ArchObjectDao extends AbstractDao<ArchObject>
{
	@Override
	public List<ArchObject> getList(Class<ArchObject> classObj, Criterion criterion)
	{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(classObj).setFetchMode("ares", FetchMode.JOIN)
				.setFetchMode("profileLayers", FetchMode.JOIN).setFetchMode("projectionLayers", FetchMode.JOIN);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.list();
	}
}
