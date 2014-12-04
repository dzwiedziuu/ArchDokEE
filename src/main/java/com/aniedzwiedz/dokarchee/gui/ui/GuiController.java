package com.aniedzwiedz.dokarchee.gui.ui;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

public interface GuiController
{
	void switchViewTo(AbstractView view);

	void openInNewWindow(AbstractView view);

	void closeWindowOrGoPrevView();
}
