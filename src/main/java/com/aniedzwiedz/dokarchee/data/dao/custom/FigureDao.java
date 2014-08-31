package com.aniedzwiedz.dokarchee.data.dao.custom;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.model.Figure;

@Repository
public class FigureDao extends AbstractDao<Figure>
{
	@Override
	public List<Figure> getList(Class<Figure> classObj, Criterion criterion)
	{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Figure.class);
		if (criterion != null)
			criteria.add(criterion);
		return criteria.setFetchMode("", FetchMode.JOIN).list();
	}

	@Override
	public void update(Figure t)
	{
		// TODO generates too many select statements
		getSessionFactory().getCurrentSession().merge(t);
	}
}
