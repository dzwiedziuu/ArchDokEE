package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.vaadin.ui.UI;

public class ShowNextView extends Action
{
	private AbstractPresenter nextPresenter;

	public ShowNextView(AbstractView abstractView)
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
		nextPresenter.setParentPresenter(getCurrentPresenter());
		UI.getCurrent().getNavigator().navigateTo(nextPresenter.getAbstractView().getViewName());
	}
}
