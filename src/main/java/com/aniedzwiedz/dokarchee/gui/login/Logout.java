package com.aniedzwiedz.dokarchee.gui.login;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@Theme("archdokee")
public class Logout extends UI
{
	@Override
	protected void init(VaadinRequest request)
	{
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(new Label("Zostales pomyslnie wylogowany."));
		layout.addComponent(new Label("Kliknij w ponizszy link aby zalogowac sie ponownie"));
		String ctx = VaadinService.getCurrentRequest().getContextPath();
		layout.addComponent(new Link("Do strony logowania", new ExternalResource(ctx + "/app")));
	}
}