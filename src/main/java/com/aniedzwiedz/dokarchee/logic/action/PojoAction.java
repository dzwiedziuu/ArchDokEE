package com.aniedzwiedz.dokarchee.logic.action;


public abstract class PojoAction<T> extends Action implements PojoHandler<T>
{
	private T pojoObject;

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}

	public T getPojoObject()
	{
		return pojoObject;
	}

	public abstract boolean isObjectNecessary();
}
