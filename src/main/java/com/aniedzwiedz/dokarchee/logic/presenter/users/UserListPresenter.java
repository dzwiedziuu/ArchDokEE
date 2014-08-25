package com.aniedzwiedz.dokarchee.logic.presenter.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.data.service.UserService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.NamedView;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.RemoveRecord;
import com.aniedzwiedz.dokarchee.logic.action.SaveObject;
import com.aniedzwiedz.dokarchee.logic.action.ShowEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class UserListPresenter extends PojoListPresenter<User>
{
	public interface UserListView extends NamedView
	{
		public void setUserList(List<User> userList);
	}

	@Autowired
	private PojoService<User> userService;

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
	public NamedView getAbstractView()
	{
		return userListView;
	}

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void refreshView()
	{
		userListView.setUserList(userService.getAll());
	}

	@Override
	public PojoService<User> getPojoService()
	{
		return userService;
	}
	
	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return userEditPresenter;
	}
}
