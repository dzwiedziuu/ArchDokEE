package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.vaadin.ui.Label;

@Component(StartPageView.VIEW_NAME)
@Scope("prototype")
@VaadinView(StartPageView.VIEW_NAME)
public class StartPageView extends AbstractViewImpl
{
	public StartPageView()
	{
		setContent(new Label("Content of start page"));
	}

	public static final String VIEW_NAME = "StartPage";

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
