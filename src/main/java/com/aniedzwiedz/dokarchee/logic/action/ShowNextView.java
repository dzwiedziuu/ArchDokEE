package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.window.SubWindow;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.UI;

public class ShowNextView extends Action
{
	private AbstractPresenter nextPresenter;
	private boolean newWindow;

	public ShowNextView(boolean newWindow)
	{
		this.newWindow = newWindow;
	}

	public void setNextPresenter(AbstractPresenter nextPresenter)
	{
		this.nextPresenter = nextPresenter;
	}

	@Override
	public void performAction()
	{
		nextPresenter.setParentPresenter(getCurrentPresenter());
		if (newWindow == false)
			UI.getCurrent().getNavigator().navigateTo(nextPresenter.getAbstractView().getViewName());
		else
		{
			nextPresenter.setPresentsWindow(true);
			SubWindow subWindow = new SubWindow(nextPresenter.getAbstractView());
			subWindow.setHeight(300, Unit.PIXELS);
			subWindow.setWidth(400, Unit.PIXELS);
			UI.getCurrent().addWindow(subWindow);
		}
	}
}
