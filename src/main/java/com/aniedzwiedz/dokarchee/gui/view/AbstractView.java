package com.aniedzwiedz.dokarchee.gui.view;

import com.aniedzwiedz.dokarchee.gui.ui.GuiController;

public interface AbstractView extends NamedView
{
	public interface ViewListener
	{
		void initializeView(ViewEvent viewEvent);
	}

	public static class ViewEvent
	{
	}

	void addViewListener(ViewListener viewListener);

	void switchViewTo(AbstractView view);

	void openInNewWindow(AbstractView window);

	void closeLastWindow();

	void setGuiController(GuiController applicationUI);

	void refresh();
}
