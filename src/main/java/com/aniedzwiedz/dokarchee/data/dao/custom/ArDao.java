package com.aniedzwiedz.dokarchee.data.dao.custom;

import java.util.List;

import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import com.aniedzwiedz.dokarchee.data.dao.AbstractDao;
import com.aniedzwiedz.dokarchee.data.model.Ar;

@Repository
public class ArDao extends AbstractDao<Ar>
{
	@Override
	public List<Ar> getList(Class<Ar> classObj)
	{
		return getSessionFactory().getCurrentSession().createCriteria(Ar.class).setFetchMode("", FetchMode.JOIN).list();
	}
}
