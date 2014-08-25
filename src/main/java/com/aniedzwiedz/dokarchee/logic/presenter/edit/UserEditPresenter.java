package com.aniedzwiedz.dokarchee.logic.presenter.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.NamedView;
import com.aniedzwiedz.dokarchee.logic.presenter.list.UserListPresenter;

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
