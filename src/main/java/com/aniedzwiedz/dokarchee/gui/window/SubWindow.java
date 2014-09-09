package com.aniedzwiedz.dokarchee.gui.window;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SubWindow extends Window implements AbstractWindow
{
	private AbstractViewImpl view;

	public SubWindow(AbstractView abstractView)
	{
		view = (AbstractViewImpl) abstractView;
		setModal(true);

		VerticalLayout vl = new VerticalLayout();
		Label titleLabel = new Label(view.getTitle());
		titleLabel.setStyleName("titleInWindow");
		vl.addComponent(titleLabel);
		vl.setExpandRatio(titleLabel, 0);
		vl.addComponent(view);
		vl.setExpandRatio(view, 1);
		setContent(vl);
	}

	public AbstractViewImpl getView()
	{
		return view;
	}
}
