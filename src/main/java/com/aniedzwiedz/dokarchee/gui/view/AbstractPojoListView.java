package com.aniedzwiedz.dokarchee.gui.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.form.ExtendedFieldGroupFieldFactory;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.CRUDTableListener;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoListView<T> extends AbstractViewImpl implements AbstractListView
{
	private List<ListViewListener> listViewListeners = new ArrayList<>();

	public void addListViewListener(ListViewListener listViewListener)
	{
		listViewListeners.add(listViewListener);
	}

	private VerticalLayout verticalLayout;
	private Class<T> classObj;
	private boolean selectable = false;
	private ListViewCRUDTableListener listViewCRUDTableListener = new ListViewCRUDTableListener();

	@Autowired
	private ExtendedFieldGroupFieldFactory extendedFieldGroupFieldFactory;
	private CRUDTable<T> crudTable;

	public AbstractPojoListView(Class<T> classObj)
	{
		this.classObj = classObj;
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
	}

	protected CRUDTable<T> createStandardCRUDTable(List<T> pojoList)
	{
		CRUDTable<T> crudTable = extendedFieldGroupFieldFactory.createTableField(classObj, false);
		if (selectable)
			crudTable.setSelectActionButton(crudTable.addLowerButton(new Button("Wybierz")));
		crudTable.setPropertyDataSource(new ObjectProperty<>(pojoList, List.class));
		crudTable.addCRUDTableListener(listViewCRUDTableListener);
		return crudTable;
	}

	public void setList(List<T> list)
	{
		verticalLayout.removeAllComponents();
		crudTable = createStandardCRUDTable(list);
		verticalLayout.addComponent(crudTable);
	}

	public void setSelectable(boolean selectable)
	{
		this.selectable = selectable;
	}

	public boolean isSelectable()
	{
		return selectable;
	}

	private class ListViewCRUDTableListener implements CRUDTableListener
	{
		@Override
		public void addItem(TableEvent crudTableEvent)
		{
			for (ListViewListener listViewListener : listViewListeners)
				listViewListener.addItem(crudTableEvent);
		}

		@Override
		public void editItem(TableEvent crudTableEvent)
		{
			for (ListViewListener listViewListener : listViewListeners)
				listViewListener.editItem(crudTableEvent);
		}

		@Override
		public void removeItem(TableEvent crudTableEvent)
		{
			for (ListViewListener listViewListener : listViewListeners)
				listViewListener.removeItem(crudTableEvent);
		}

		@Override
		public void doubleClickedItem(TableEvent crudTableEvent)
		{
			for (ListViewListener listViewListener : listViewListeners)
				listViewListener.doubleClickedItem(crudTableEvent);
		}

		@Override
		public void selectedItem(TableEvent event)
		{
			for (ListViewListener listViewListener : listViewListeners)
				listViewListener.selectedItem(event);
		}
	}
}
