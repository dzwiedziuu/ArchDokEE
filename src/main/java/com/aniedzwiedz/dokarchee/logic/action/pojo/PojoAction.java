package com.aniedzwiedz.dokarchee.logic.action.pojo;

import com.aniedzwiedz.dokarchee.logic.action.Action;

public abstract class PojoAction<T> extends Action implements PojoHandler<T>
{
	private T pojoObject;
	private Class<T> pojoType;

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}

	public T getPojoObject()
	{
		return pojoObject;
	}

	public Class<T> getPojoType()
	{
		return pojoType;
	}

	public void setPojoType(Class<T> pojoType)
	{
		this.pojoType = pojoType;
	}

	public abstract boolean isObjectNecessary();
}
