package com.aniedzwiedz.dokarchee.gui.view.users;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.AddNew;
import com.aniedzwiedz.dokarchee.logic.action.Edit;
import com.aniedzwiedz.dokarchee.logic.action.InitPojoSetView;
import com.aniedzwiedz.dokarchee.logic.action.Remove;
import com.aniedzwiedz.dokarchee.logic.presenter.list.UserListPresenter.UserListView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@Component(UsersListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(UsersListViewImpl.VIEW_NAME)
public class UsersListViewImpl extends AbstractView implements UserListView
{
	private static final long serialVersionUID = -974633482319703150L;

	public static final String VIEW_NAME = "USER_LIST";

	private CRUDTable<User> crudTable;

	private VerticalLayout verticalLayout;

	public UsersListViewImpl()
	{
		System.out.println("create");
		setSizeFull();
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
	}

	private void createCRUDTable(List<User> userList)
	{
		crudTable = new CRUDTable<>(User.class);

		crudTable.addContextMenuItem("Add", new AddNew<User>(this));
		crudTable.addContextMenuItem("Edit", new Edit<User>(this));
		crudTable.addContextMenuItem("Remove", new Remove<User>(this));

		crudTable.addButton("Dodaj", new AddNew<User>(this));
		crudTable.addButton("Edytuj", new Edit<User>(this));
		crudTable.addButton("Usun", new Remove<User>(this));

		crudTable.setDoubleClickAction(new Edit<User>(this));

		crudTable.setActionTaker(this);
		crudTable.setData(userList);
		verticalLayout.addComponent(crudTable);
	}

	public void setUserList(List<User> userList)
	{
		if (crudTable != null)
			verticalLayout.removeAllComponents();
		createCRUDTable(userList);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		takeAction(new InitPojoSetView<User>(this));
	}

	public String getViewName()
	{
		return VIEW_NAME;
	}
}
