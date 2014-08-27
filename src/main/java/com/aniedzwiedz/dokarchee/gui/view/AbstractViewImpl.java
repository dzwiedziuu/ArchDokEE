package com.aniedzwiedz.dokarchee.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.ui.GuiController;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.Refresh;
import com.aniedzwiedz.dokarchee.logic.queue.AbstractApplicationQueue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

public abstract class AbstractViewImpl extends Panel implements AbstractView, View
{
	private static final long serialVersionUID = -8228882086603009109L;

	@Autowired
	private AbstractApplicationQueue<Action> queue;

	private GuiController guiController;

	public void takeAction(Action action)
	{
		action.setCurrentView(this);
		queue.put(action);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		refresh();
	}

	@Override
	public void refresh()
	{
		takeAction(new Refresh());
	}

	public void switchViewTo(AbstractView view)
	{
		guiController.switchViewTo(view);
	}

	public void openInNewWindow(AbstractView view)
	{
		guiController.openInNewWindow(view);
	}

	public void closeLastWindow()
	{
		guiController.closeLastOpenedWindow();
	}

	public void setGuiController(GuiController guiController)
	{
		this.guiController = guiController;
	}

	public GuiController getGuiController()
	{
		return guiController;
	}
}
