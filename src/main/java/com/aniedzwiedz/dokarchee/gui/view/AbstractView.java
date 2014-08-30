package com.aniedzwiedz.dokarchee.gui.view;

import com.aniedzwiedz.dokarchee.gui.ui.GuiController;

public interface AbstractView extends NamedView
{
	public interface ViewListener
	{
		void refreshView(AbstractView abstractView);

		void focusAfterClosedWindow(ViewEvent viewEvent);
	}

	public static class ViewEvent
	{
		private AbstractView abstractView;

		public ViewEvent(AbstractView abstractView)
		{
			this.abstractView = abstractView;
		}

		public AbstractView getClosedWindowView()
		{
			return abstractView;
		}
	}

	void addViewListener(ViewListener viewListener);

	void switchViewTo(AbstractView view);

	void openInNewWindow(AbstractView window);

	void closeLastWindow();

	void setGuiController(GuiController applicationUI);

	void refresh();

	void takeFocusAfterClosedWindow(AbstractView closedView);
}
