package com.aniedzwiedz.dokarchee.logic.action.pojo;



public class ShowNewObjectView<T> extends ShowEditObjectView<T>
{
	private T blankObject;

	public ShowNewObjectView(T blankObject)
	{
		this.blankObject = blankObject;
	}

	@Override
	public T getPojoObject()
	{
		return blankObject;
	}

	@Override
	public boolean isObjectNecessary()
	{
		return false;
	}
}
