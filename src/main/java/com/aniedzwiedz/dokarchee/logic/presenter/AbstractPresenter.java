package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.Action;

public abstract class AbstractPresenter implements ActionTaker
{
	private AbstractPresenter parentPresenter;
	private boolean presentsWindow = false;

	@Override
	public void takeAction(Action action)
	{
		action.performAction();
	}

	public AbstractPresenter getParentPresenter()
	{
		return parentPresenter;
	}

	public void setParentPresenter(AbstractPresenter parentPresenter)
	{
		this.parentPresenter = parentPresenter;
	}

	public boolean isPresentsWindow()
	{
		return presentsWindow;
	}

	public void setPresentsWindow(boolean presentsWindow)
	{
		this.presentsWindow = presentsWindow;
	}

	public abstract AbstractView getAbstractView();

	public abstract void setView(AbstractView abstractView);

	public abstract void refreshView();

	public abstract AbstractPresenter getNextPresenter(Action action);

}
