package com.aniedzwiedz.dokarchee.logic.presenter.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.UserService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class UserListPresenter extends PojoListPresenter<User>
{
	public interface UserListView extends PojoListView<User>
	{
	}

	@Autowired
	public UserListPresenter(UserListView pojoListView, UserService pojoService, UserEditPresenter pojoEditPresenter)
	{
		setPojoListView(pojoListView);
		setPojoService(pojoService);
		setNextPresenter(pojoEditPresenter);
	}
}
