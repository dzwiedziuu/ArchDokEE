package com.aniedzwiedz.dokarchee.gui.view;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.RemoveRecord;
import com.aniedzwiedz.dokarchee.logic.action.ShowEditView;
import com.aniedzwiedz.dokarchee.logic.action.ShowNewObjectView;
import com.aniedzwiedz.dokarchee.logic.action.preAction.BlankPreAction;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoListView<T> extends AbstractView
{
	public static class Confirm extends BlankPreAction implements org.vaadin.dialogs.ConfirmDialog.Listener
	{
		private Action action;

		public Confirm(AbstractView sender)
		{
			super(sender);
		}

		@Override
		public void doPreAction(Action action)
		{
			this.action = action;
			ConfirmDialog confirmDialog = ConfirmDialog.getFactory().create("Uwaga", "Na pewno chcesz skasowac ten rekord?", "TAK", "NIE",
					null);
			confirmDialog.setWidth(400.0f, Unit.PIXELS);
			confirmDialog.show(UI.getCurrent(), Confirm.this, true);
		}

		@Override
		public void onClose(ConfirmDialog dialog)
		{
			if (dialog.isConfirmed() == true)
				super.doPreAction(action);
		}
	}

	private VerticalLayout verticalLayout;

	private Class<T> classObj;

	public AbstractPojoListView(Class<T> classObj)
	{
		this.classObj = classObj;
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
	}

	protected CRUDTable<T> createStandardCRUDTable(List<T> userList)
	{
		CRUDTable<T> crudTable = new CRUDTable<>(classObj);

		crudTable.addContextMenuItem("Add", new ShowNewObjectView<User>(this, new User()));
		crudTable.addContextMenuItem("Edit", new ShowEditView<User>(this));
		crudTable.addContextMenuItem("Remove", new RemoveRecord<User>(this, new AbstractPojoListView.Confirm(this)));

		crudTable.addButton("Dodaj", new ShowNewObjectView<User>(this, new User()));
		crudTable.addButton("Edytuj", new ShowEditView<User>(this));
		crudTable.addButton("Usun", new RemoveRecord<User>(this, new AbstractPojoListView.Confirm(this)));

		crudTable.setDoubleClickAction(new ShowEditView<User>(this));

		crudTable.setData(userList);
		return crudTable;
	}

	public void setList(List<T> list)
	{
		verticalLayout.removeAllComponents();
		CRUDTable<T> crudTable = createStandardCRUDTable(list);
		verticalLayout.addComponent(crudTable);
	}
}
