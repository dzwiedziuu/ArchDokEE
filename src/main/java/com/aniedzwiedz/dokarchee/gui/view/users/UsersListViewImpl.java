package com.aniedzwiedz.dokarchee.gui.view.users;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.aniedzwiedz.dokarchee.gui.view.AbstractListView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.Refresh;
import com.aniedzwiedz.dokarchee.logic.action.RemoveRecord;
import com.aniedzwiedz.dokarchee.logic.action.ShowEditView;
import com.aniedzwiedz.dokarchee.logic.action.ShowNewObjectView;
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
		setSizeFull();
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
	}

	private void createCRUDTable(List<User> userList)
	{
		crudTable = new CRUDTable<>(User.class);

		crudTable.addContextMenuItem("Add", new ShowNewObjectView<User>(this, new User()));
		crudTable.addContextMenuItem("Edit", new ShowEditView<User>(this));
		crudTable.addContextMenuItem("Remove", new RemoveRecord<User>(this, new AbstractListView.Confirm(this)));

		crudTable.addButton("Dodaj", new ShowNewObjectView<User>(this, new User()));
		crudTable.addButton("Edytuj", new ShowEditView<User>(this));
		crudTable.addButton("Usun", new RemoveRecord<User>(this, new AbstractListView.Confirm(this)));

		crudTable.setDoubleClickAction(new ShowEditView<User>(this));

		crudTable.setData(userList);
		verticalLayout.addComponent(crudTable);
	} 

	public void setUserList(List<User> userList)
	{
		verticalLayout.removeAllComponents();
		createCRUDTable(userList);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		takeAction(new Refresh(this));
	}

	public String getViewName()
	{
		return VIEW_NAME;
	}
}
