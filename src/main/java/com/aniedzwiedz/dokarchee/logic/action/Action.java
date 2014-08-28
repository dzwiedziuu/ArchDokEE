package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public abstract class Action
{
	private AbstractView currentView;
	private AbstractPresenter currentPresenter;

	public void setCurrentView(AbstractView currentView)
	{
		this.currentView = currentView;
	}

	protected AbstractView getSenderView()
	{
		return currentView;
	}

	public String getSenderViewName()
	{
		return currentView.getViewName();
	}

	protected AbstractPresenter getCurrentPresenter()
	{
		return currentPresenter;
	}

	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		this.currentPresenter = currentPresenter;
	}

	public abstract void performAction();
}
