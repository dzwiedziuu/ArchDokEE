package com.aniedzwiedz.dokarchee.gui.view;

import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.CRUDTableListener;

public interface AbstractListView extends AbstractView
{
	public interface ListViewListener extends CRUDTableListener, ViewListener
	{
	}

	void addListViewListener(ListViewListener listViewListener);

	void addItem(Object value);
}
