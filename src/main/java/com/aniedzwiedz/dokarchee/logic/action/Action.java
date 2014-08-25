package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public abstract class Action
{
	private AbstractView abstractView;
	private AbstractPresenter abstractPresenter;

	public Action(AbstractView abstractView)
	{
		this.abstractView = abstractView;
	}

	protected AbstractView getAbstractView()
	{
		return abstractView;
	}

	public String getSender()
	{
		return abstractView.getViewName();
	}

	public AbstractPresenter getAbstractPresenter()
	{
		return abstractPresenter;
	}

	public void setAbstractPresenter(AbstractPresenter abstractPresenter)
	{
		this.abstractPresenter = abstractPresenter;
	}

	public abstract void performAction();
}
