package com.aniedzwiedz.dokarchee.gui.ui;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

/*
 * klasa wykonuj¹ca operacje zmiany widoków oraz zamykania i otwierania okien
 */
public interface GuiController
{
	void switchViewTo(AbstractView view);

	void openInNewWindow(AbstractView view);

	void closeWindowOrGoPrevView();
}
