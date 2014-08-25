package com.aniedzwiedz.dokarchee.presenter.list;

import com.aniedzwiedz.dokarchee.presenter.AbstractPresenter;

public abstract class PojoListPresenter<T> extends AbstractPresenter
{
	public abstract void setViewData();

	public abstract void openEditWindow(T t);
}
