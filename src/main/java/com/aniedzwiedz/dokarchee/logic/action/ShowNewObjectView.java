package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

public class ShowNewObjectView<T> extends ShowEditView<T>
{
	private T blankObject;
	
	public ShowNewObjectView(AbstractView abstractView, T blankObject)
	{
		super(abstractView);
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
