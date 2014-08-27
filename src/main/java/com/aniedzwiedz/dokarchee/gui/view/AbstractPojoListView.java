package com.aniedzwiedz.dokarchee.gui.view;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.pojo.RemoveRecord;
import com.aniedzwiedz.dokarchee.logic.action.pojo.ShowEditObjectView;
import com.aniedzwiedz.dokarchee.logic.action.pojo.ShowNewObjectView;
import com.aniedzwiedz.dokarchee.logic.action.preAction.BlankPreAction;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoListView<T> extends AbstractViewImpl
{
	public static class Confirm extends BlankPreAction implements org.vaadin.dialogs.ConfirmDialog.Listener
	{
		private Action action;
		private ActionTaker actionTaker;

		@Override
		public void doPreAction(Action action, ActionTaker actionTaker)
		{
			this.action = action;
			this.actionTaker = actionTaker;
			ConfirmDialog confirmDialog = ConfirmDialog.getFactory().create("Uwaga", "Na pewno chcesz skasowac ten rekord?", "TAK", "NIE",
					null);
			confirmDialog.setWidth(400.0f, Unit.PIXELS);
			confirmDialog.show(UI.getCurrent(), Confirm.this, true);
		}

		@Override
		public void onClose(ConfirmDialog dialog)
		{
			if (dialog.isConfirmed())
				super.doPreAction(action, actionTaker);
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
		crudTable.setParentActionTaker(this);
		T blankObj;
		try
		{
			blankObj = classObj.newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			// TODO print stackTrace
			e.printStackTrace();
			return crudTable;
		}

		crudTable.addContextMenuItem("Add", new ShowNewObjectView<T>(blankObj));
		crudTable.addContextMenuItem("Edit", new ShowEditObjectView<T>());
		crudTable.addContextMenuItem("Remove", new RemoveRecord<T>(new AbstractPojoListView.Confirm()));

		crudTable.addButton("Dodaj", new ShowNewObjectView<T>(blankObj));
		crudTable.addButton("Edytuj", new ShowEditObjectView<T>());
		crudTable.addButton("Usun", new RemoveRecord<T>(new AbstractPojoListView.Confirm()));

		crudTable.setDoubleClickAction(new ShowEditObjectView<T>());

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
