package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class ShowEditView<T> extends PojoAction<T>
{
	private AbstractPresenter nextPresenter;

	public ShowEditView(AbstractView abstractView)
	{
		super(abstractView);
	}

	public void setNextPresenter(AbstractPresenter nextPresenter)
	{
		this.nextPresenter = nextPresenter;
	}

	@Override
	public void performAction()
	{
		nextPresenter.setData(null, getPojoObject());
		ShowNextView showNextView = new ShowNextView(getCurrentPresenter().getAbstractView().getViewName());
		showNextView.setCurrentPresenter(getCurrentPresenter());
		showNextView.setNextPresenter(nextPresenter);
		showNextView.performAction();
	}

}
