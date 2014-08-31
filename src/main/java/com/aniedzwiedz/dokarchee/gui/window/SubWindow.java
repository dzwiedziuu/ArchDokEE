package com.aniedzwiedz.dokarchee.gui.window;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.vaadin.ui.Window;

public class SubWindow extends Window implements AbstractWindow
{
	private AbstractViewImpl view;

	public SubWindow(AbstractView abstractView)
	{
		view = (AbstractViewImpl) abstractView;
		setModal(true);
		setContent(view);
	}

	public AbstractViewImpl getView()
	{
		return view;
	}
}
