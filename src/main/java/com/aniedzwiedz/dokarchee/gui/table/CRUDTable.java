package com.aniedzwiedz.dokarchee.gui.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.tepi.filtertable.FilterTable;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItemClickEvent;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItemClickListener;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;
import com.aniedzwiedz.dokarchee.common.utils.ModelUtils;
import com.aniedzwiedz.dokarchee.gui.form.fields.ActiveComponent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignFieldColumnGenerator;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyContextMenuOpenedOnTableFooterEvent;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyContextMenuOpenedOnTableHeaderEvent;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyContextMenuOpenedOnTableRowEvent;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyTableListener;
import com.aniedzwiedz.dokarchee.gui.view.PojoEvent;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CRUDTable<T> extends CustomField<Set<T>> implements ActiveComponent
{
	public interface CRUDTableListener
	{
		void addItem(TableEvent crudTableEvent);

		void editItem(TableEvent crudTableEvent);

		void removeItem(TableEvent crudTableEvent);

		void doubleClickedItem(TableEvent crudTableEvent);

		void selectedItem(TableEvent event);
	}

	public static class TableEvent extends PojoEvent
	{
		private CRUDTable crudTable;

		private void setCRUDTable(CRUDTable crudTable)
		{
			this.crudTable = crudTable;
		}

		public CRUDTable getCrudTable()
		{
			return crudTable;
		}
	}

	private List<CRUDTableListener> crudTableListeners = new ArrayList<>();

	public void addCRUDTableListener(CRUDTableListener crudTableListener)
	{
		crudTableListeners.add(crudTableListener);
	}

	private ActionButtonClickListener actionListener;

	private FilterTable filterTable;
	private MyContextMenu myContextMenu;
	private HorizontalLayout buttonPanel;
	private AbstractOrderedLayout lowerButtonPanel;
	private VerticalLayout verticalLayout;

	private Class<T> classObj;

	private Button addActionButton;
	private Button editActionButton;
	private Button removeActionButton;
	private Button selectActionButton;

	private ContextMenuItem addActionMenuItem;
	private ContextMenuItem editActionMenuItem;
	private ContextMenuItem removeActionMenuItem;

	public CRUDTable(Class<T> classObj)
	{
		this.classObj = classObj;
		actionListener = new ActionButtonClickListener();
		verticalLayout = new VerticalLayout();

		filterTable = new FilterTable();
		filterTable.setSizeFull();
		filterTable.setFilterGenerator(new FilterGeneratorImpl());
		filterTable.setFilterDecorator(new FilterDecoratorImpl());
		filterTable.setFilterBarVisible(true);
		filterTable.setSelectable(true);
		filterTable.setMultiSelect(false);
		filterTable.setNullSelectionAllowed(false);
		filterTable.addItemClickListener(actionListener);

		myContextMenu = new MyContextMenu();
		myContextMenu.addMyContextMenuTableListener(actionListener);
		myContextMenu.addItemClickListener(actionListener);

		buttonPanel = new HorizontalLayout();
		lowerButtonPanel = new HorizontalLayout();
	}

	public Class<?> getContentType()
	{
		return classObj;
	}

	public void setAddActionButton(Button addActionButton)
	{
		this.addActionButton = addActionButton;
		this.addActionButton.addClickListener(actionListener);
	}

	public void setEditActionButton(Button editActionButton)
	{
		this.editActionButton = editActionButton;
		this.editActionButton.addClickListener(actionListener);
	}

	public void setRemoveActionButton(Button removeActionButton)
	{
		this.removeActionButton = removeActionButton;
		this.removeActionButton.addClickListener(actionListener);
	}

	public void setSelectActionButton(Button selectActionButton)
	{
		this.selectActionButton = selectActionButton;
		this.selectActionButton.addClickListener(actionListener);
	}

	public void setAddActionMenuItem(ContextMenuItem addActionMenuItem)
	{
		this.addActionMenuItem = addActionMenuItem;
	}

	public void setEditActionMenuItem(ContextMenuItem editActionMenuItem)
	{
		this.editActionMenuItem = editActionMenuItem;
	}

	public void setRemoveActionMenuItem(ContextMenuItem removeActionMenuItem)
	{
		this.removeActionMenuItem = removeActionMenuItem;
	}

	private boolean anyItemAdded = false;

	public ContextMenuItem addContextMenuItem(String caption)
	{
		anyItemAdded = true;
		return myContextMenu.addItem(caption);
	}

	public Button addUpperButton(Button button)
	{
		buttonPanel.addComponent(button);
		return button;
	}

	public Button addLowerButton(Button button)
	{
		lowerButtonPanel.addComponent(button);
		return button;
	}

	/**
	 * 
	 * @param item
	 *            == item in table container
	 */
	public void removeItem(T item)
	{
		filterTable.removeItem(item);
		Property p = getPropertyDataSource();
		Set c = (Set) p.getValue();
		List<Object> toRemove = new ArrayList<>();
		for (Object object : c)
			if (ModelUtils.equals(item, object))
				toRemove.add(object);
		for (Object o : toRemove)
			c.remove(o);
	}

	private class ActionButtonClickListener implements Button.ClickListener, ContextMenuItemClickListener, MyTableListener,
			ItemClickListener
	{
		private static final long serialVersionUID = 7823307317994804055L;
		private Object lastClickedItemId;

		@Override
		public void buttonClick(ClickEvent event)
		{
			if (event.getButton() == addActionButton)
				performAddAction();
			if (event.getButton() == editActionButton)
				performEditAction();
			if (event.getButton() == removeActionButton)
				performRemoveAction();
			if (event.getButton() == selectActionButton)
				performSelectAction();
		}

		@Override
		public void contextMenuItemClicked(ContextMenuItemClickEvent event)
		{
			ContextMenuItem item = (ContextMenuItem) event.getSource();
			if (item == addActionMenuItem)
				performAddAction();
			if (item == editActionMenuItem)
				performEditAction();
			if (item == removeActionMenuItem)
				performRemoveAction();
		}

		@Override
		public void myOnContextMenuOpenFromRow(MyContextMenuOpenedOnTableRowEvent myContextMenuOpenedOnTableRowEvent)
		{
			Object clickedId = myContextMenuOpenedOnTableRowEvent.getItemId();
			filterTable.select(clickedId);
			lastClickedItemId = clickedId;
		}

		@Override
		public void myOnContextMenuOpenFromHeader(MyContextMenuOpenedOnTableHeaderEvent myContextMenuOpenedOnTableHeaderEvent)
		{
		}

		@Override
		public void myOnContextMenuOpenFromFooter(MyContextMenuOpenedOnTableFooterEvent myContextMenuOpenedOnTableFooterEvent)
		{
		}

		@Override
		public void itemClick(ItemClickEvent event)
		{
			// it is necessary to remember last selected field, becouse
			// Table.getValue() doesn't truly remember selection (in case of
			// selecting many values despite setting multiMode = false)
			if (event.getButton().equals(MouseButton.LEFT))
				lastClickedItemId = event.getItemId();
			if (event.isDoubleClick())
				performDoubleClickAction();
		}

		public Object getLastClickedItemId()
		{
			return lastClickedItemId;
		}
	}

	private void performAddAction()
	{
		T obj;
		try
		{
			obj = classObj.newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		for (CRUDTableListener crudListener : crudTableListeners)
		{
			TableEvent event = new TableEvent();
			event.setPojoObject(obj);
			event.setCRUDTable(this);
			crudListener.addItem(event);
		}
	}

	private void performEditAction()
	{
		T lastClicked = getSelectedItem();
		if (lastClicked == null)
		{
			Notification.show("UWAGA", "Zaden rekord nie zostal wybrany", Type.HUMANIZED_MESSAGE);
			return;
		}
		for (CRUDTableListener crudListener : crudTableListeners)
		{
			TableEvent event = new TableEvent();
			event.setPojoObject(lastClicked);
			event.setCRUDTable(this);
			crudListener.editItem(event);
		}
	}

	private void performRemoveAction()
	{
		final T lastClicked = getSelectedItem();
		if (lastClicked == null)
		{
			Notification.show("UWAGA", "Zaden rekord nie zostal wybrany", Type.HUMANIZED_MESSAGE);
			return;
		}
		ConfirmDialog confirmDialog = ConfirmDialog.getFactory()
				.create("Uwaga", "Na pewno chcesz skasowac ten rekord?", "TAK", "NIE", null);
		confirmDialog.setWidth(400.0f, Unit.PIXELS);
		confirmDialog.show(UI.getCurrent(), new ConfirmDialog.Listener()
		{
			@Override
			public void onClose(ConfirmDialog dialog)
			{
				if (dialog.isConfirmed())
				{
					for (CRUDTableListener crudListener : crudTableListeners)
					{
						TableEvent event = new TableEvent();
						event.setPojoObject(lastClicked);
						event.setCRUDTable(CRUDTable.this);
						crudListener.removeItem(event);
					}
				}
			}
		}, true);
	}

	private void performDoubleClickAction()
	{
		T lastClicked = getSelectedItem();
		for (CRUDTableListener crudListener : crudTableListeners)
		{
			TableEvent event = new TableEvent();
			event.setPojoObject(lastClicked);
			event.setCRUDTable(this);
			crudListener.doubleClickedItem(event);
		}
	}

	private void performSelectAction()
	{
		T lastClicked = getSelectedItem();
		if (lastClicked == null)
		{
			Notification.show("UWAGA", "Zaden rekord nie zostal wybrany", Type.HUMANIZED_MESSAGE);
			return;
		}
		for (CRUDTableListener crudListener : crudTableListeners)
		{
			TableEvent event = new TableEvent();
			event.setPojoObject(lastClicked);
			event.setCRUDTable(this);
			crudListener.selectedItem(event);
		}
	}

	public void setDataRows(Iterable<T> list)
	{
		verticalLayout.removeAllComponents();
		filterTable.setContainerDataSource(getContainerDataSource(list));
		setColumnHeaders();
		addColumnGenerators();
		verticalLayout.addComponent(buttonPanel);
		verticalLayout.setExpandRatio(buttonPanel, 0);
		verticalLayout.addComponent(filterTable);
		verticalLayout.setExpandRatio(filterTable, 1);
		verticalLayout.addComponent(lowerButtonPanel);
		verticalLayout.setExpandRatio(lowerButtonPanel, 0);
		if (anyItemAdded)
			myContextMenu.setAsTableContextMenu(filterTable);
	}

	private void setColumnHeaders()
	{
		TreeMap<Integer, List<String>> map = new TreeMap<>();
		for (Field field : classObj.getDeclaredFields())
			if (field.isAnnotationPresent(ColumnHeader.class))
			{
				ColumnHeader columnHeader = field.getAnnotation(ColumnHeader.class);
				map.put(columnHeader.order(), Arrays.asList(field.getName(), columnHeader.value()));
			}
		Object[] visibleColumns = new Object[map.size()];
		int i = 0;
		for (Entry<Integer, List<String>> entry : map.entrySet())
		{
			filterTable.setColumnHeader(entry.getValue().get(0), entry.getValue().get(1));
			visibleColumns[i++] = entry.getValue().get(0);
		}
		filterTable.setVisibleColumns(visibleColumns);
	}

	private void addColumnGenerators()
	{
		for (Field field : classObj.getDeclaredFields())
			if (field.getType().isAnnotationPresent(ForeignFieldLabel.class))
				filterTable.addGeneratedColumn(field.getName(), new ForeignFieldColumnGenerator<>(field.getType()));
	}

	private Container getContainerDataSource(Iterable<T> list)
	{
		BeanItemContainer<T> beanItemContainer = new BeanItemContainer<>(classObj);
		for (T t : list)
			beanItemContainer.addBean(t);
		return beanItemContainer;
	}

	@Override
	protected Component initContent()
	{
		return verticalLayout;
	}

	@Override
	public Class<? extends Set<T>> getType()
	{
		return super.getPropertyDataSource().getType();
		// return (Class<? extends Set<T>>) (new HashSet<T>()).getClass();
	}

	// private Property<?> property = new Property<?>;

	private Set<T> initialSet;

	@Override
	public void setPropertyDataSource(Property newDataSource)
	{
		if (initialSet == null)
			initialSet = (Set<T>) newDataSource.getValue();
		Iterable<T> iterable = (Iterable<T>) newDataSource.getValue();
		setDataRows(iterable);
		Set<T> mergedSet = mergePropertyDataSource(initialSet, (Set<T>) newDataSource.getValue());
		newDataSource.setValue(mergedSet);
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Property getPropertyDataSource()
	{
		return super.getPropertyDataSource();
	}

	private Set<T> mergePropertyDataSource(Set<T> initialSet, Set<T> currentSet)
	{
		Set<T> resultSet = new HashSet<>();
		outer: for (T currentObj : currentSet)
		{
			for (T initialObj : initialSet)
				if (ModelUtils.equals(initialObj, currentObj))
				{
					resultSet.add(initialObj);
					continue outer;
				}
			resultSet.add(currentObj);
		}
		return resultSet;
	}

	public T getSelectedItem()
	{
		return (T) actionListener.getLastClickedItemId();
	}

	@Override
	public void getSelectedValue(Object value)
	{
		Property p = getPropertyDataSource();
		Set c = (Set) p.getValue();
		for (Object object : c)
			if (ModelUtils.equals(value, object))
			{
				Notification.show("Blad", "Podany rekord zostal juz dodany do tabeli", Type.HUMANIZED_MESSAGE);
				return;
			}
		c.add(value);
		setPropertyDataSource(p);
	}
}
