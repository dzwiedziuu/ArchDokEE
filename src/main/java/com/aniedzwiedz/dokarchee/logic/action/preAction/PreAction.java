package com.aniedzwiedz.dokarchee.logic.action.preAction;

import com.aniedzwiedz.dokarchee.logic.action.Action;

public interface PreAction
{
	/*
	 * if action should be continued, invoke super.doPreAction()
	 */
	void doPreAction(Action action);
}
