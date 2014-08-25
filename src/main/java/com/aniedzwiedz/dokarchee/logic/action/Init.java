package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

public class Init extends Action
{
	public Init(AbstractView abstractView)
	{
		super(abstractView);
	}

	@Override
	public void performAction()
	{
		getAbstractPresenter().setView(getAbstractView());
	}
}
