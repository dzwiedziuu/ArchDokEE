package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

public class ShowEditView<T> extends PojoAction<T>
{
	private ShowNextView showNextView;

	public ShowEditView(AbstractView abstractView)
	{
		super(abstractView);
		showNextView = new ShowNextView(abstractView);
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
