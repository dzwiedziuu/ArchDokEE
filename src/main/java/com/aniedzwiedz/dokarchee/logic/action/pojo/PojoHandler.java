package com.aniedzwiedz.dokarchee.logic.action.pojo;

public interface PojoHandler<T>
{
	public void setPojoObject(T pojoObject);

	public T getPojoObject();
}
