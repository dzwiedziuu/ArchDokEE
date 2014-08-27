package com.aniedzwiedz.dokarchee.logic.action.preAction;

import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.Action;

public class BlankPreAction implements PreAction
{
	@Override
	public void doPreAction(Action action, ActionTaker actionTaker)
	{
		actionTaker.takeAction(action);
	}
}
