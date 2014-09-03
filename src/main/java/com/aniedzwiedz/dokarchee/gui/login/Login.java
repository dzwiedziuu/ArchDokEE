package com.aniedzwiedz.dokarchee.gui.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@Theme("archdokee")
public class Login extends UI implements Button.ClickListener
{
	private TextField user;
	private PasswordField password;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Override
	protected void init(VaadinRequest request)
	{
		final VerticalLayout layout = new VerticalLayout();
		setContent(layout);

		user = new TextField("U¿ytkownik");
		password = new PasswordField("Has³o");

		layout.addComponent(user);
		layout.setComponentAlignment(user, Alignment.MIDDLE_CENTER);
		layout.addComponent(password);
		layout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);

		Button button = new Button("Zaloguj!");
		layout.addComponent(button);
		layout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

		button.addClickListener(this);

	}

	@Override
	public void buttonClick(ClickEvent event)
	{
		try
		{
			String username = user.getValue();
			String pass = password.getValue();
			UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(username, pass);

			Authentication result = authManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			String ctx = VaadinService.getCurrentRequest().getContextPath();
			Page.getCurrent().setLocation(ctx + "/app");
		} catch (AuthenticationException e)
		{
			e.printStackTrace();
			Notification.show("Autentykacja nie powiod³a siê...");
		}
	}
}