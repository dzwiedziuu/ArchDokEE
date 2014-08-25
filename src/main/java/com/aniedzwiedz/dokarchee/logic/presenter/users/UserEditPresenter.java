package com.aniedzwiedz.dokarchee.logic.presenter.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.NamedView;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class UserEditPresenter extends PojoEditPresenter<User>
{
	public interface UserEditView extends NamedView
	{
		void setUser(User user);
	}
	
	@Autowired
	private PojoService<User> userService;

	@Autowired
	private UserEditView userEditView;

	private User user;

	@Override
	public NamedView getAbstractView()
	{
		return userEditView;
	}

	@Override
	public void setView(AbstractView namedView)
	{
		userEditView = (UserEditView) namedView;
	}

	@Override
	public void refreshView()
	{
			userEditView.setUser(user);
	}

	@Override
	public PojoService<User> getPojoService()
	{
		return userService;
	}

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return null;
	}

	@Override
	public void setPojoObject(User object)
	{
		this.user = object;
	}
}
