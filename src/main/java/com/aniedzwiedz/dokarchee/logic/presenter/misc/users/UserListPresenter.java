package com.aniedzwiedz.dokarchee.logic.presenter.misc.users;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class UserListPresenter extends PojoListPresenter<User>
{
	public interface UserListView extends PojoListView<User>
	{
	}

	@Autowired
	public UserListPresenter(UserListView pojoListView, AbstractServiceInterface<User> pojoService, UserEditPresenter pojoEditPresenter)
	{
		super(User.class);
		setPojoListView(pojoListView);
		setPojoService(pojoService);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return null;
	}

	@Override
	protected User getEmptyObject()
	{
		return new User();
	}
}
