package com.aniedzwiedz.dokarchee.gui.ui;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

/*
 * klasa wykonuj�ca operacje zmiany widok�w oraz zamykania i otwierania okien
 */
public interface GuiController
{
	void switchViewTo(AbstractView view);

	void openInNewWindow(AbstractView view);

	void closeWindowOrGoPrevView();
}
