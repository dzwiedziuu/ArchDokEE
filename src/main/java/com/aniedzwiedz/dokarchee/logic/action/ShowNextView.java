package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class ShowNextView extends Action
{
	private AbstractPresenter nextPresenter;
	private boolean newWindow;

	public ShowNextView(boolean newWindow)
	{
		this.newWindow = newWindow;
	}

	public void setNextPresenter(AbstractPresenter nextPresenter)
	{
		this.nextPresenter = nextPresenter;
	}

	@Override
	public void performAction()
	{
		AbstractPresenter abstractPresenter = getCurrentPresenter();
		AbstractView currentView = abstractPresenter.getAbstractView();
		nextPresenter.setParentPresenter(abstractPresenter);
		if (newWindow == false)
			currentView.switchViewTo(nextPresenter.getAbstractView());
		else
		{
			nextPresenter.setPresentsWindow(true);
			currentView.openInNewWindow(nextPresenter.getAbstractView());
		}
	}
}
