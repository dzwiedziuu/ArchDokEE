package com.aniedzwiedz.dokarchee.presenter.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.model.User;
import com.aniedzwiedz.dokarchee.presenter.list.UserListPresenter;
import com.aniedzwiedz.dokarchee.view.AbstractView;
import com.aniedzwiedz.dokarchee.view.NamedView;

@Component
@Scope("session")
public class UserEditPresenter extends PojoEditPresenter<User>
{
	public interface UserEditView extends NamedView
	{
		void setUser(User user);
	}

	@Autowired
	private UserEditView userEditView;

	private User user;

	@Autowired
	private UserListPresenter userListPresenter;

	@Override
	public NamedView getNamedView()
	{
		return userEditView;
	}

	@Override
	public void setView(AbstractView namedView)
	{
		userEditView = (UserEditView) namedView;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public void setViewData()
	{
		userEditView.setUser(user);
	}
}
