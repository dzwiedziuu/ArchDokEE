package com.aniedzwiedz.dokarchee.table.contextMenu;

import java.util.EventObject;

import org.vaadin.peter.contextmenu.ContextMenu;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.FooterClickEvent;
import com.vaadin.ui.CustomTable.FooterClickListener;
import com.vaadin.ui.CustomTable.HeaderClickEvent;
import com.vaadin.ui.CustomTable.HeaderClickListener;
import com.vaadin.util.ReflectTools;

public class MyContextMenu extends ContextMenu
{
	private static final long serialVersionUID = 625173504234868007L;

	public void setAsTableContextMenu(final CustomTable table)
	{
		extend(table);

		setOpenAutomatically(true);

		table.addItemClickListener(new ItemClickEvent.ItemClickListener()
		{
			private static final long serialVersionUID = -348059189217149508L;

			@Override
			public void itemClick(ItemClickEvent event)
			{
				if (event.getButton() == MouseButton.RIGHT)
				{
					fireEvent(new MyContextMenuOpenedOnTableRowEvent(MyContextMenu.this, table, event.getItemId(), event.getPropertyId()));
					open(event.getClientX(), event.getClientY());
				}
			}
		});

		table.addHeaderClickListener(new HeaderClickListener()
		{
			private static final long serialVersionUID = -5880755689414670581L;

			@Override
			public void headerClick(HeaderClickEvent event)
			{
				if (event.getButton() == MouseButton.RIGHT)
				{
					fireEvent(new MyContextMenuOpenedOnTableHeaderEvent(MyContextMenu.this, table, event.getPropertyId()));
					open(event.getClientX(), event.getClientY());
				}
			}
		});

		table.addFooterClickListener(new FooterClickListener()
		{
			private static final long serialVersionUID = 2884227013964132482L;

			@Override
			public void footerClick(FooterClickEvent event)
			{
				if (event.getButton() == MouseButton.RIGHT)
				{
					fireEvent(new MyContextMenuOpenedOnTableHeaderEvent(MyContextMenu.this, table, event.getPropertyId()));
					open(event.getClientX(), event.getClientY());
				}
			}
		});
	}

	public static class MyContextMenuOpenedOnTableHeaderEvent extends EventObject
	{
		private static final long serialVersionUID = -1220618848356241248L;

		private final Object propertyId;

		private final ContextMenu contextMenu;

		public MyContextMenuOpenedOnTableHeaderEvent(ContextMenu contextMenu, CustomTable source, Object propertyId)
		{
			super(source);

			this.contextMenu = contextMenu;
			this.propertyId = propertyId;
		}

		public ContextMenu getContextMenu()
		{
			return contextMenu;
		}

		public Object getPropertyId()
		{
			return propertyId;
		}
	}

	public static class MyContextMenuOpenedOnTableFooterEvent extends EventObject
	{
		private static final long serialVersionUID = 1999781663913723438L;

		private final Object propertyId;

		private final ContextMenu contextMenu;

		public MyContextMenuOpenedOnTableFooterEvent(ContextMenu contextMenu, CustomTable source, Object propertyId)
		{
			super(source);

			this.contextMenu = contextMenu;
			this.propertyId = propertyId;
		}

		public ContextMenu getContextMenu()
		{
			return contextMenu;
		}

		public Object getPropertyId()
		{
			return propertyId;
		}
	}

	public static class MyContextMenuOpenedOnTableRowEvent extends EventObject
	{
		private static final long serialVersionUID = -470218301318358912L;

		private final ContextMenu contextMenu;
		private final Object propertyId;
		private final Object itemId;

		public MyContextMenuOpenedOnTableRowEvent(ContextMenu contextMenu, CustomTable table, Object itemId, Object propertyId)
		{
			super(table);

			this.contextMenu = contextMenu;
			this.itemId = itemId;
			this.propertyId = propertyId;
		}

		public ContextMenu getContextMenu()
		{
			return contextMenu;
		}

		public Object getItemId()
		{
			return itemId;
		}

		public Object getPropertyId()
		{
			return propertyId;
		}
	}

	public void addMyContextMenuTableListener(MyTableListener contextMenuTableListener)
	{
		addListener(MyContextMenuOpenedOnTableRowEvent.class, contextMenuTableListener,
				ReflectTools.findMethod(MyTableListener.class, "myOnContextMenuOpenFromRow", MyContextMenuOpenedOnTableRowEvent.class));

		addListener(MyContextMenuOpenedOnTableHeaderEvent.class, contextMenuTableListener, ReflectTools.findMethod(MyTableListener.class,
				"myOnContextMenuOpenFromHeader", MyContextMenuOpenedOnTableHeaderEvent.class));

		addListener(MyContextMenuOpenedOnTableFooterEvent.class, contextMenuTableListener, ReflectTools.findMethod(MyTableListener.class,
				"myOnContextMenuOpenFromFooter", MyContextMenuOpenedOnTableFooterEvent.class));
	}

	public interface MyTableListener
	{
		public void myOnContextMenuOpenFromRow(MyContextMenuOpenedOnTableRowEvent myContextMenuOpenedOnTableRowEvent);

		public void myOnContextMenuOpenFromHeader(MyContextMenuOpenedOnTableHeaderEvent myContextMenuOpenedOnTableHeaderEvent);

		public void myOnContextMenuOpenFromFooter(MyContextMenuOpenedOnTableFooterEvent myContextMenuOpenedOnTableFooterEvent);

	}
}
