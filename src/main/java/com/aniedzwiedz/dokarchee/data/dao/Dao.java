package com.aniedzwiedz.dokarchee.data.dao;

import java.util.List;

public interface Dao<T>
{
	void add(T t);

	List<T> getList(Class<T> classObj);

	T find(T t);

	void remove(T t);

	void update(T t);
}
