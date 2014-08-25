package com.aniedzwiedz.dokarchee.gui.view.users;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.InitPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.edit.UserEditPresenter.UserEditView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Component(UserEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(UserEditViewImpl.VIEW_NAME)
public class UserEditViewImpl extends AbstractView implements UserEditView
{
	private static final long serialVersionUID = -6321530911411760761L;

	public static final String VIEW_NAME = "USER_EDIT";

	private VerticalLayout verticalLayout;

	private User user;

	public UserEditViewImpl()
	{
		setSizeFull();
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
		Button button = new Button(UsersListViewImpl.VIEW_NAME, new Button.ClickListener()
		{
			private static final long serialVersionUID = 1427431040468097393L;

			@Override
			public void buttonClick(ClickEvent event)
			{
				UI.getCurrent().getNavigator().navigateTo(UsersListViewImpl.VIEW_NAME);
			}
		});
		verticalLayout.addComponent(button);
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		takeAction(new InitPojoEditView<User>(this));
	}

	@Override
	public void setUser(User user)
	{
		this.user = user;
	}
}
