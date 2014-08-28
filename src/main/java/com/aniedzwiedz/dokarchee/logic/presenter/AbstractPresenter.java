package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

public abstract class AbstractPresenter implements AbstractView.ViewListener
{
	private AbstractPresenter parentPresenter;
	private boolean presentsInWindow = false;

	public AbstractPresenter getParentPresenter()
	{
		return parentPresenter;
	}

	public void setParentPresenter(AbstractPresenter parentPresenter)
	{
		this.parentPresenter = parentPresenter;
	}

	public void setPresentsInWindow(boolean presentsWindow)
	{
		this.presentsInWindow = presentsWindow;
	}

	public abstract AbstractView getAbstractView();

	public abstract void setView(AbstractView abstractView);

	protected void goToNextView(AbstractPresenter abstractPresenter)
	{
		AbstractView abstractView = abstractPresenter.getAbstractView();
		abstractPresenter.setParentPresenter(this);
		if (abstractPresenter.presentsInWindow == false)
			getAbstractView().switchViewTo(abstractView);
		else
		{
			abstractPresenter.presentsInWindow = true;
			getAbstractView().openInNewWindow(abstractView);
		}
	}
}
