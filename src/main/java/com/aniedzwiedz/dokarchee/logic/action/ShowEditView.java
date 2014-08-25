package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class ShowEditView<T> extends PojoAction<T>
{
	private AbstractPresenter nextPresenter;
	private ShowNextView showNextView;

	public ShowEditView(AbstractView abstractView)
	{
		super(abstractView);
		showNextView = new ShowNextView(abstractView);
	}

	public void setNextPresenter(AbstractPresenter nextPresenter)
	{
		this.nextPresenter = nextPresenter;
		showNextView.setNextPresenter(nextPresenter);
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
		nextPresenter.setData(null, getPojoObject());
		showNextView.performAction();
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
