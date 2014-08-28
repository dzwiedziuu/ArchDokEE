package com.aniedzwiedz.dokarchee.logic.action.pojo;

import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;

public class RemoveRecord<T> extends PojoAction<T>
{
	@Override
	public void performAction()
	{
		PojoService<T> pojoService = ((PojoPresenter<T>) getCurrentPresenter()).getPojoService();
		pojoService.remove(getPojoObject());
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
