package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;

public class SaveObject<T> extends PojoAction<T>
{
	private ShowPrevView showPrevView = new ShowPrevView();

	@Override
	public void setCurrentView(AbstractView currentView)
	{
		super.setCurrentView(currentView);
		showPrevView.setCurrentView(currentView);
	}

	@Override
	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		super.setCurrentPresenter(currentPresenter);
		showPrevView.setCurrentPresenter(currentPresenter);
	}

	@Override
	public void performAction()
	{
		PojoService<T> pojoService = ((PojoPresenter<T>) getCurrentPresenter()).getPojoService();
		T pojoObject = getPojoObject();
		T obj = pojoService.find(pojoObject);
		if (obj == null)
			pojoService.add(pojoObject);
		else
			pojoService.update(pojoObject);
		showPrevView.performAction();
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
