package com.aniedzwiedz.dokarchee.gui.view;

import com.aniedzwiedz.dokarchee.gui.ui.GuiController;

public interface AbstractView extends ActionTaker, NamedView
{
	void switchViewTo(AbstractView view);

	void openInNewWindow(AbstractView window);

	void closeLastWindow();

	void setGuiController(GuiController applicationUI);

	GuiController getGuiController();

	void refresh();
}
