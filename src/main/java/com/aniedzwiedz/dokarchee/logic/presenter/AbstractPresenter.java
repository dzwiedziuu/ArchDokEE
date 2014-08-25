package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.gui.view.NamedView;
import com.aniedzwiedz.dokarchee.logic.action.Action;

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
