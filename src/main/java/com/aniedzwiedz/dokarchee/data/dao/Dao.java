package com.aniedzwiedz.dokarchee.data.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

public interface Dao<T>
{
	void add(T t);

	List<T> getList(Class<T> classObj, Criterion criterion);

	T find(T t);

	void remove(T t);

	void update(T t);
}
