package com.aniedzwiedz.dokarchee.gui.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.ui.GuiController;
import com.aniedzwiedz.dokarchee.logic.controller.SessionController;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

public abstract class AbstractViewImpl extends Panel implements AbstractView, View
{
	private GuiController guiController;

	@Autowired
	private SessionController sessionController;

	private List<ViewListener> viewListeners = new ArrayList<>();

	@Override
	public void addViewListener(ViewListener viewListener)
	{
		viewListeners.add(viewListener);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
	}

	public void refresh()
	{
		for (ViewListener viewListener : viewListeners)
			viewListener.initializeView(this);
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

	public void takeFocusAfterClosedWindow(AbstractView closedView)
	{
		for (ViewListener viewListener : viewListeners)
			viewListener.focusAfterClosedWindow(new ViewEvent(closedView));
	}
}
