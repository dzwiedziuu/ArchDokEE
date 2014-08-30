package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

import org.hibernate.criterion.Criterion;

public interface PojoService<T>
{
	void add(T t);

	void update(T t);

	void remove(T t);

	T find(T t);

	List<T> getAll(Class<T> clazz, Criterion criterion);
}
