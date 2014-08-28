package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class ShowPrevView extends Action
{
	@Override
	public void performAction()
	{
		AbstractPresenter currentPresenter = getCurrentPresenter();
		AbstractPresenter parent = currentPresenter.getParentPresenter();
		AbstractView currentView = currentPresenter.getAbstractView();
		currentPresenter.setParentPresenter(null);
		// if (!currentPresenter.isPresentsWindow())
		currentView.switchViewTo(parent.getAbstractView());
		// else
		currentView.closeLastWindow();
	}
}
