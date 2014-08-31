package com.aniedzwiedz.dokarchee.data.dao.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.model.Are;

@Repository
public class AreDao extends AbstractDao<Are>
{
	@Override
	public List<Are> getList(Class<Are> classObj, Criterion criterion)
	{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Are.class);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.setFetchMode("", FetchMode.JOIN).list();
	}
}
