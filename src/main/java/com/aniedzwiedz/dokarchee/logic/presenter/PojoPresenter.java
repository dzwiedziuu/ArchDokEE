package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.data.service.PojoService;

public abstract class PojoPresenter<T> extends AbstractPresenter
{
	public abstract PojoService<T> getPojoService();
}
