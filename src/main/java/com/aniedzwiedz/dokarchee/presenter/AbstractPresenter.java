package com.aniedzwiedz.dokarchee.presenter;

import com.aniedzwiedz.dokarchee.action.Action;
import com.aniedzwiedz.dokarchee.view.AbstractView;
import com.aniedzwiedz.dokarchee.view.ActionTaker;
import com.aniedzwiedz.dokarchee.view.NamedView;

public abstract class AbstractPresenter implements ActionTaker
{
	@Override
	public void takeAction(Action action)
	{
		action.performAction();
	}

	public abstract NamedView getNamedView();

	public abstract void setView(AbstractView namedView);
}
