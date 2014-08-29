package com.aniedzwiedz.dokarchee.logic.presenter.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.UserService;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class UserEditPresenter extends PojoEditPresenter<User>
{
	public interface UserEditView extends PojoEditView<User>
	{
	}

	@Autowired
	public UserEditPresenter(UserEditView userEditView, UserService userService)
	{
		setView(userEditView);
		setPojoService(userService);
	}

	@Override
	protected AbstractPresenter getDictionaryPresenter(Class<?> ffType)
	{
		return null;
	}

	@Override
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		return null;
	}
}
