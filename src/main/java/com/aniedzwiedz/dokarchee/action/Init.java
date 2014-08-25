package com.aniedzwiedz.dokarchee.action;

import com.aniedzwiedz.dokarchee.view.AbstractView;

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
