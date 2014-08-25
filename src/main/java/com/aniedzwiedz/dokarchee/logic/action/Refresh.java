package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

public class Refresh extends Action
{
	public Refresh(AbstractView abstractView)
	{
		super(abstractView);
	}

	@Override
	public void performAction()
	{
		getCurrentPresenter().setView(getSenderView());
		getCurrentPresenter().setData(null, null);
	}
}
