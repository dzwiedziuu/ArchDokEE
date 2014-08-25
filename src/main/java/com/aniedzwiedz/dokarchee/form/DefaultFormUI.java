package com.aniedzwiedz.dokarchee.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.model.User;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Component
@Scope("prototype")
public class DefaultFormUI extends UI
{
	private static final long serialVersionUID = -9195785347432747602L;

	@Override
	protected void init(VaadinRequest request)
	{
		User user = new User();
		user.setFirstName("dzw");
		DefaultForm<User> defaultForm = new DefaultForm<>(user);
		setContent(defaultForm);
	}
}
