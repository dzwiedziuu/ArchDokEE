package com.aniedzwiedz.dokarchee.logic.presenter.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.data.service.UserService;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class UserListPresenter extends PojoListPresenter<User>
{
	public interface UserListView extends PojoListView<User>
	{
	}

	@Autowired
	private UserService pojoService;

	@Autowired
	private UserListView pojoListView;

	@Autowired
	private UserEditPresenter pojoEditPresenter;

	@Override
	protected void setPojoListView(PojoListView<User> pojoListView)
	{
		this.pojoListView = (UserListView) pojoListView;
	}

	@Override
	protected PojoListView<User> getPojoListView()
	{
		return pojoListView;
	}

	@Override
	public PojoService<User> getPojoService()
	{
		return pojoService;
	}

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return pojoEditPresenter;
	}
}
