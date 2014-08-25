package com.aniedzwiedz.dokarchee.queue;

public interface AbstractApplicationQueue<T>
{
	T take();

	void put(T t);
}
