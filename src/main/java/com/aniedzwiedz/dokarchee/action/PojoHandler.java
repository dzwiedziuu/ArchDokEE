package com.aniedzwiedz.dokarchee.action;

public interface PojoHandler<T>
{
	public void setPojoObject(T pojoObject);

	public T getPojoObject();
}
