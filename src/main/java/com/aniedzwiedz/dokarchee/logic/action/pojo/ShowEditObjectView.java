package com.aniedzwiedz.dokarchee.logic.action.pojo;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.ShowNextView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

public class ShowEditObjectView<T> extends PojoAction<T>
{
	private ShowNextView showNextView = new ShowNextView(true);

	@Override
	public void setCurrentView(AbstractView currentView)
	{
		super.setCurrentView(currentView);
		showNextView.setCurrentView(currentView);
	}

	@Override
	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		super.setCurrentPresenter(currentPresenter);
		showNextView.setCurrentPresenter(currentPresenter);
	}

	@Override
	public void performAction()
	{
		PojoEditPresenter<T> nextPresenter = (PojoEditPresenter<T>) getCurrentPresenter().getNextPresenter(this);
		nextPresenter.setPojoObject(getPojoObject());
		showNextView.setNextPresenter(nextPresenter);
		showNextView.performAction();
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
