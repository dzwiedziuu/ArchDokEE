package com.aniedzwiedz.dokarchee.logic.presenter.list;

import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public abstract class PojoListPresenter<T> extends AbstractPresenter
{
	public abstract void setViewData();

	public abstract void openEditWindow(T t);
}
