package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.gui.view.NamedView;
import com.aniedzwiedz.dokarchee.logic.action.Action;

public abstract class AbstractPresenter implements ActionTaker
{
	private AbstractPresenter parentPresenter;

	@Override
	public void takeAction(Action action)
	{
		action.doPerform();
	}

	public abstract NamedView getAbstractView();

	public abstract void setView(AbstractView namedView);

	public AbstractPresenter getParentPresenter()
	{
		return parentPresenter;
	}

	public void setParentPresenter(AbstractPresenter parentPresenter)
	{
		this.parentPresenter = parentPresenter;
	}

	public abstract void setData(String property, Object data);

	public abstract void setParams(Action action);
}
