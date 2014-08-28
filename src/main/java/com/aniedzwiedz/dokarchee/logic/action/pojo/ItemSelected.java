package com.aniedzwiedz.dokarchee.logic.action.pojo;

public class ItemSelected<T> extends PojoAction<T>
{
	@Override
	public void performAction()
	{
		System.out.println("item selected");
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
