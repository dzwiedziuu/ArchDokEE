package com.aniedzwiedz.dokarchee.logic.queue;

public interface AbstractApplicationQueue<T>
{
	T take();

	void put(T t);
}
