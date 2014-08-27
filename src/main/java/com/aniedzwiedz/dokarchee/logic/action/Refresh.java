package com.aniedzwiedz.dokarchee.logic.action;


public class Refresh extends Action
{
	@Override
	public void performAction()
	{
		getCurrentPresenter().setView(getSenderView());
		getCurrentPresenter().refreshView();
	}
}
