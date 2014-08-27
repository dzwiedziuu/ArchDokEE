package com.aniedzwiedz.dokarchee.logic.action;


public class ShowNewObjectView<T> extends ShowEditView<T>
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
