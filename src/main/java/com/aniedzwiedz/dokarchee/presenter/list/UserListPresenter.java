package com.aniedzwiedz.dokarchee.presenter.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.model.User;
import com.aniedzwiedz.dokarchee.presenter.edit.UserEditPresenter;
import com.aniedzwiedz.dokarchee.service.UserService;
import com.aniedzwiedz.dokarchee.view.AbstractView;
import com.aniedzwiedz.dokarchee.view.NamedView;
import com.aniedzwiedz.dokarchee.view.users.UserEditViewImpl;
import com.vaadin.ui.UI;

@Component
@Scope("session")
public class UserListPresenter extends PojoListPresenter<User>
{
	public interface UserListView extends NamedView
	{
		public void setUserList(List<User> userList);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserListView userListView;

	@Autowired
	private UserEditPresenter userEditPresenter;

	@Override
	public void setView(AbstractView asbtactView)
	{
		this.userListView = (UserListView) asbtactView;
	}

	@Override
	public NamedView getNamedView()
	{
		return userListView;
	}

	@Override
	public void setViewData()
	{
		userListView.setUserList(userService.getAllUsers());
	}

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void openEditWindow(User user)
	{
		// VaadinSession.getCurrent().setAttribute(User.class, user);
		userEditPresenter.setUser(user);
		UI.getCurrent().getNavigator().navigateTo(UserEditViewImpl.VIEW_NAME);
	}
}
