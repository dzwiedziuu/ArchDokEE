package com.aniedzwiedz.dokarchee.gui.window;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.logic.action.Refresh;
import com.vaadin.ui.HasComponents;
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

	@Override
	public void setParent(HasComponents parent)
	{
		super.setParent(parent);
		if (parent == null) // when closing window
			return;
		view.takeAction(new Refresh());
	}

	public AbstractViewImpl getView()
	{
		return view;
	}
}
