package com.aniedzwiedz.dokarchee.logic.action.preAction;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.Action;

public class BlankPreAction implements PreAction
{
	private AbstractView sender;

	public BlankPreAction(AbstractView sender)
	{
		this.sender = sender;
	}

	public AbstractView getAbstractView()
	{
		return sender;
	}

	@Override
	public void doPreAction(Action action)
	{
		if (sender != null)
			sender.takeAction(action);
	}
}
