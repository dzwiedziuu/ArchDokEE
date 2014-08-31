package com.aniedzwiedz.dokarchee.logic.presenter.misc.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class UserEditPresenter extends PojoEditPresenter<User>
{
	public interface UserEditView extends PojoEditView<User>
	{
	}

	@Autowired
	public UserEditPresenter(UserEditView userEditView, AbstractServiceInterface<User> userService)
	{
		setView(userEditView);
		setPojoService(userService);
	}
}
