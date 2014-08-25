package com.aniedzwiedz.dokarchee.logic.action;

public interface PojoHandler<T>
{
	public void setPojoObject(T pojoObject);

	public T getPojoObject();

}
