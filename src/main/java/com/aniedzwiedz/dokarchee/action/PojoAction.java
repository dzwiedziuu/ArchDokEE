package com.aniedzwiedz.dokarchee.action;

import com.aniedzwiedz.dokarchee.view.AbstractView;

public abstract class PojoAction<T> extends Action implements PojoHandler<T>
{
	private T pojoObject;

	public PojoAction(AbstractView abstractView)
	{
		super(abstractView);
	}

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}

	public T getPojoObject()
	{
		return pojoObject;
	}
}
