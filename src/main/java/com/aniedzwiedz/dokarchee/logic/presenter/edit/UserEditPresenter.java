package com.aniedzwiedz.dokarchee.logic.presenter.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.NamedView;
import com.aniedzwiedz.dokarchee.logic.action.Action;

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
	public void setData(String property, Object data)
	{
		// setData in showEditView
		if (data != null)
			this.user = (User) data;
		// setData in init
		else
			userEditView.setUser(user);
	}

	@Override
	public void setParams(Action action)
	{
		// TODO Auto-generated method stub

	}
}
