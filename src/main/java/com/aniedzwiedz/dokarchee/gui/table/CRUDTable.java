package com.aniedzwiedz.dokarchee.gui.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.tepi.filtertable.FilterTable;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItemClickEvent;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItemClickListener;

import com.aniedzwiedz.dokarchee.gui.ComponentWithAction;
import com.aniedzwiedz.dokarchee.gui.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.gui.annotations.ForeignFieldLabel;
import com.aniedzwiedz.dokarchee.gui.form.fields.ActiveComponent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignFieldColumnGenerator;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyContextMenuOpenedOnTableFooterEvent;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyContextMenuOpenedOnTableHeaderEvent;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyContextMenuOpenedOnTableRowEvent;
import com.aniedzwiedz.dokarchee.gui.table.contextMenu.MyContextMenu.MyTableListener;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.pojo.PojoAction;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public class CRUDTable<T> extends VerticalLayout implements ActiveComponent
{
	private static final long serialVersionUID = 1371883572521815469L;

	private ActionButtonClickListener actionListener;

	private List<ComponentWithAction<ContextMenuItem>> contextMenuItems;
	private List<ComponentWithAction<Button>> buttons;

	private Action doubleClickedAction;

	private FilterTable filterTable;
	private MyContextMenu myContextMenu;
	private HorizontalLayout buttonPanel;

	private Class<T> classObj;
	private ActionTaker parentActionTaker;

	public CRUDTable(Class<T> classObj)
	{
		this.classObj = classObj;
		actionListener = new ActionButtonClickListener();

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
		contextMenuItems = new ArrayList<>();
		myContextMenu.addMyContextMenuTableListener(actionListener);
		myContextMenu.addItemClickListener(actionListener);

		buttonPanel = new HorizontalLayout();
		buttons = new ArrayList<>();
	}

	public void addContextMenuItem(String caption, Action action)
	{
		ContextMenuItem item = myContextMenu.addItem(caption);
		contextMenuItems.add(new ComponentWithAction<ContextMenuItem>(item, action));
	}

	public void addButton(String caption, Action action)
	{
		Button button = new Button(caption, actionListener);
		buttonPanel.addComponent(button);
		buttons.add(new ComponentWithAction<Button>(button, action));
	}

	public void setDoubleClickAction(Action action)
	{
		this.doubleClickedAction = action;
	}

	private class ActionButtonClickListener implements Button.ClickListener, ContextMenuItemClickListener, MyTableListener,
			ItemClickListener
	{
		private static final long serialVersionUID = 7823307317994804055L;
		private Object lastClickedItemId;

		@Override
		public void buttonClick(ClickEvent event)
		{
			for (ComponentWithAction<Button> componentWithAction : buttons)
				if (event.getButton() == componentWithAction.getComponent())
					performActionOrReturn(componentWithAction.getAction(), lastClickedItemId);
		}

		@Override
		public void contextMenuItemClicked(ContextMenuItemClickEvent event)
		{
			ContextMenuItem item = (ContextMenuItem) event.getSource();
			for (ComponentWithAction<ContextMenuItem> componentWithAction : contextMenuItems)
				if (item == componentWithAction.getComponent())
					performActionOrReturn(componentWithAction.getAction(), lastClickedItemId);
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
				if (doubleClickedAction != null)
					performActionOrReturn(doubleClickedAction, lastClickedItemId);
		}
	}

	private void performActionOrReturn(Action action, Object itemId)
	{
		if (action instanceof PojoAction)
		{
			if (itemId == null && ((PojoAction<T>) action).isObjectNecessary())
			{
				Notification.show("UWAGA", "Zaden rekord nie zostal wybrany", Type.HUMANIZED_MESSAGE);
				return;
			}
			((PojoAction<T>) action).setPojoObject((T) itemId);
		}
		action.getPreAction().doPreAction(action, parentActionTaker);
	}

	public void setData(List<T> list)
	{
		removeAllComponents();
		filterTable.setContainerDataSource(getContainerDataSource(list));
		setColumnHeaders();
		addColumnGenerators();
		if (!buttons.isEmpty())
		{
			addComponent(buttonPanel);
			setExpandRatio(buttonPanel, 0);
		}
		addComponent(filterTable);
		setExpandRatio(filterTable, 1);
		if (!contextMenuItems.isEmpty())
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

	private Container getContainerDataSource(List<T> list)
	{
		BeanItemContainer<T> beanItemContainer = new BeanItemContainer<>(classObj);
		for (T t : list)
			beanItemContainer.addBean(t);
		return beanItemContainer;
	}

	@Override
	public void setParentActionTaker(ActionTaker actionTaker)
	{
		this.parentActionTaker = actionTaker;
	}
}
