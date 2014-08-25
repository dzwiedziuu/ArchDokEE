package com.aniedzwiedz.dokarchee.logic.presenter.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.data.service.UserService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class UserEditPresenter extends PojoEditPresenter<User>
{
	public interface UserEditView extends PojoEditView<User>
	{
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserEditView userEditView;

	@Override
	protected PojoEditView<User> getPojoEditView()
	{
		return userEditView;
	}

	@Override
	protected void setPojoEditView(PojoEditView<User> namedView)
	{
		this.userEditView = (UserEditView) namedView;
	}

	@Override
	public PojoService<User> getPojoService()
	{
		return userService;
	}
}
