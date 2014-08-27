package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class Refresh extends Action
{
	@Override
	public void performAction()
	{
		getCurrentPresenter().setView(getSenderView());
		AbstractPresenter parentPresenter = getCurrentPresenter().getParentPresenter();
		// getSenderView().setGuiController(parentPresenter.getAbstractView().getGuiController());
		getCurrentPresenter().refreshView();
	}
}
