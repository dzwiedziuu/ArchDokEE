package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Component(StartPageView.VIEW_NAME)
@Scope("prototype")
@VaadinView(StartPageView.VIEW_NAME)
public class StartPageView extends AbstractViewImpl
{
	public StartPageView()
	{
		VerticalLayout vl = new VerticalLayout();
		vl.setStyleName("page-content");
		vl.addComponent(new Label("Witaj!"));
		vl.addComponent(new Label("To jest aplikacja DokArch."));
		vl.setSizeFull();
		setSizeFull();
		setContent(vl);
	}

	public static final String VIEW_NAME = "StartPage";

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Start";
	}
}
