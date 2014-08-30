package com.aniedzwiedz.dokarchee.gui.view.users;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.users.UserListPresenter.UserListView;

@Component(UsersListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(UsersListViewImpl.VIEW_NAME)
public class UsersListViewImpl extends AbstractPojoListView<User> implements UserListView
{
	private static final long serialVersionUID = -974633482319703150L;

	static final String VIEW_NAME = "USER_LIST";

	public UsersListViewImpl()
	{
		super(User.class);
		setSizeFull();
	}

	public String getViewName()
	{
		return VIEW_NAME;
	}
}
