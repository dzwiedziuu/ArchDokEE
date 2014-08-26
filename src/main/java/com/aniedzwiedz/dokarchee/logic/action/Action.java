package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.preAction.BlankPreAction;
import com.aniedzwiedz.dokarchee.logic.action.preAction.PreAction;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public abstract class Action
{
	private AbstractView currentView;
	private AbstractPresenter currentPresenter;
	private PreAction immidiateAction;

	public Action(AbstractView abstractView)
	{
		this.currentView = abstractView;
		this.immidiateAction = new BlankPreAction(abstractView);
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

	public PreAction getPreAction()
	{
		return immidiateAction;
	}

	public void setPreAction(PreAction immidiateAction)
	{
		this.immidiateAction = immidiateAction;
	}
}
