package com.aniedzwiedz.dokarchee.gui.view.users;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.users.UserEditPresenter.UserEditView;

@Component(UserEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(UserEditViewImpl.VIEW_NAME)
public class UserEditViewImpl extends AbstractPojoEditView<User> implements UserEditView
{
	private static final long serialVersionUID = -6321530911411760761L;

	static final String VIEW_NAME = "USER_EDIT";

	public UserEditViewImpl()
	{
		super();
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
