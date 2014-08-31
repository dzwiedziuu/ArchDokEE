package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.data.service.PojoService;

public abstract class PojoPresenter<T> extends AbstractPresenter
{
	private PojoService<T> pojoService;

	public void setPojoService(PojoService<T> pojoService)
	{
		this.pojoService = pojoService;
	}

	public PojoService<T> getPojoService()
	{
		return pojoService;
	}

	private boolean selectable = false;

	public void setSelectable(boolean selectable)
	{
		this.selectable = selectable;
	}

	public boolean isSelectable()
	{
		return selectable;
	}
}
