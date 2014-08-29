package com.aniedzwiedz.dokarchee.gui.view;

public abstract class PojoEvent<T>
{
	private T pojoObject;

	public T getPojoObject()
	{
		return pojoObject;
	}

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}
}
