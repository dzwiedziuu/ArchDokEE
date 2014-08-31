package com.aniedzwiedz.dokarchee.gui.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
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

		addColumnGenerators();

		buttonPanel = new HorizontalLayout();
		lowerButtonPanel = new HorizontalLayout();

		verticalLayout.addComponent(buttonPanel);
		verticalLayout.setExpandRatio(buttonPanel, 0);
		verticalLayout.addComponent(filterTable);
		verticalLayout.setExpandRatio(filterTable, 1);
		verticalLayout.addComponent(lowerButtonPanel);
		verticalLayout.setExpandRatio(lowerButtonPanel, 0);

		myContextMenu = new MyContextMenu();
		myContextMenu.addMyContextMenuTableListener(actionListener);
		myContextMenu.addItemClickListener(actionListener);
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

	private void setDataRows(Iterable<T> list)
	{
		List<PropertyWithClass> visibleProperties = setColumnHeadersAndGetPropertyList();
		Container container = getContainerDataSource(list, visibleProperties);
		filterTable.setContainerDataSource(container);
		Object[] visCol = new Object[visibleProperties.size()];
		for (int i = 0; i < visibleProperties.size(); i++)
			visCol[i] = visibleProperties.get(i).getPropertyName();
		filterTable.setVisibleColumns(visCol);
		if (anyItemAdded)
			myContextMenu.setAsTableContextMenu(filterTable);
	}

	private Container getContainerDataSource(Iterable<T> list, List<PropertyWithClass> visibleProperties)
	{
		BeanItemContainer<T> beanItemContainer = new BeanItemContainer<>(classObj);
		IndexedContainer indexedContainer = new IndexedContainer();
		for (PropertyWithClass p : visibleProperties)
			indexedContainer.addContainerProperty(p.getPropertyName(), p.getPropertyType(), null);
		if (list.iterator().hasNext())
		{
			for (T t : list)
			{
				// Item item = indexedContainer.addItem(t);
				// beanItemContainer.add
				// beanItemContainer.addItem(itemId)
				// Object id = beanItemContainer.addItem();
				// Item item = beanItemContainer.getItem(id);
				// for (String property : visibleProperties)
				BeanItem<T> beanItem = beanItemContainer.addBean(t);
				List<Object> listToRemove = new ArrayList<>();
				outer: for (Object id : beanItem.getItemPropertyIds())
				{
					for (PropertyWithClass property : visibleProperties)
						if (id.equals(property.getPropertyName()))
							continue outer;
					listToRemove.add(id);
					// Object value = ReflectionUtils.getObjectPropertyValue(t,
					// property);
					// item.addItemProperty(property, new
					// ObjectProperty<>(value));
				}
				for (Object toRem : listToRemove)
					beanItem.removeItemProperty(toRem);
				// beanItemContainer.addBean(t);
			}
			return beanItemContainer;
		} else
		{
		}
		return indexedContainer;
	}

	// TODO unefficent
	private List<PropertyWithClass> setColumnHeadersAndGetPropertyList()
	{
		List<PropertyWithClass> result = new ArrayList<>();
		Map<String, Class<?>> propertyClassMap = new HashMap<>();
		TreeMap<Integer, List<String>> map = new TreeMap<>();
		for (Field field : classObj.getDeclaredFields())
			if (field.isAnnotationPresent(ColumnHeader.class))
			{
				ColumnHeader columnHeader = field.getAnnotation(ColumnHeader.class);
				map.put(columnHeader.order(), Arrays.asList(field.getName(), columnHeader.value()));
				propertyClassMap.put(field.getName(), field.getType());
			}
		String[] visibleProperties = new String[map.size()];
		int i = 0;
		for (Entry<Integer, List<String>> entry : map.entrySet())
		{
			String property = entry.getValue().get(0);
			filterTable.setColumnHeader(property, entry.getValue().get(1));
			result.add(new PropertyWithClass(property, propertyClassMap.get(property)));
		}
		return result;
	}

	private void addColumnGenerators()
	{
		for (Field field : classObj.getDeclaredFields())
		{
			if (!field.isAnnotationPresent(ColumnHeader.class))
				continue;
			if (field.getType().isAnnotationPresent(ForeignFieldLabel.class))
				filterTable.addGeneratedColumn(field.getName(), new ForeignFieldColumnGenerator<>(field.getType()));
			else if (Iterable.class.isAssignableFrom(field.getType()))
			{
				ColumnHeader columnHeader = field.getAnnotation(ColumnHeader.class);
				Class<?> classObj = columnHeader.genericType();
				if (Object.class.equals(classObj))
					throw new RuntimeException("Not declared generic type in ColumnHeader annotation for Set field");
				filterTable.addGeneratedColumn(field.getName(), new TableFieldColumnGenerator<>(classObj));
			}
		}
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
	}

	private TablePropertyHolder<T> tablePropertyHolder = new TablePropertyHolder<>();

	@Override
	public void setPropertyDataSource(Property newDataSource)
	{
		newDataSource = tablePropertyHolder.setProperty(newDataSource);
		setDataRows((Iterable<T>) newDataSource.getValue());
	}

	public void removeItem(T item)
	{
		filterTable.removeItem(item);
		tablePropertyHolder.removeItem(item);
	}

	@Override
	public Property getPropertyDataSource()
	{
		return tablePropertyHolder.getProperty();
	}

	@Override
	public void addNewValueToTable(Object value)
	{
		if (!tablePropertyHolder.addUnique((T) value) && manyToMany)
			Notification.show("Blad", "Podany rekord zostal juz dodany do tabeli", Type.HUMANIZED_MESSAGE);
		// notify about table content change
		// TODO unefficent
		setPropertyDataSource(tablePropertyHolder.getProperty());
	}

	public T getSelectedItem()
	{
		return (T) actionListener.getLastClickedItemId();
	}

	private boolean manyToMany = false;

	public void setManyToMany(boolean manyToMany)
	{
		this.manyToMany = manyToMany;
	}
}
