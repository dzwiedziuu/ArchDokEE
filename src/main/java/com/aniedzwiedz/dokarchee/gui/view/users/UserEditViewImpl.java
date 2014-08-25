package com.aniedzwiedz.dokarchee.gui.view.users;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.Refresh;
import com.aniedzwiedz.dokarchee.logic.action.SaveObject;
import com.aniedzwiedz.dokarchee.logic.action.ShowPrevView;
import com.aniedzwiedz.dokarchee.logic.presenter.edit.UserEditPresenter.UserEditView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		verticalLayout.removeAllComponents();
		takeAction(new Refresh(this)); // sets user to real Object
		DefaultForm<User> defaultForm = new DefaultForm<User>(user);
		defaultForm.setSaveAction(new SaveObject<>(this));
		defaultForm.setDiscardAction(new ShowPrevView(this));
		verticalLayout.addComponent(defaultForm);
	}

	@Override
	public void setUser(User user)
	{
		this.user = user;
	}
}
