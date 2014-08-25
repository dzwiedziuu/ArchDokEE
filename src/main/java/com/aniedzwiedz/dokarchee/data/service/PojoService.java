package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

public interface PojoService<T>
{
	void add(T t);
	void update(T t);
	void remove(T t);
	T find(T t);
	List<T> getAll();
}
