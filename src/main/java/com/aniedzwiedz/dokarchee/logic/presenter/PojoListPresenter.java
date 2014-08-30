package com.aniedzwiedz.dokarchee.logic.presenter;

import java.util.List;

import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.aniedzwiedz.dokarchee.gui.view.AbstractListView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;

public abstract class PojoListPresenter<T> extends PojoPresenter<T> implements AbstractListView.ListViewListener
{
	public interface PojoListView<T> extends AbstractListView
	{
		public void setList(List<T> list);

		public void setSelectable(boolean selectable);

		public boolean isSelectable();
	}

	private Class<T> typeClass;

	protected PojoListPresenter(Class<T> typeClass)
	{
		this.typeClass = typeClass;
	}

	private PojoListView<T> pojoListView;
	private boolean listSelectable = false;
	private PojoEditPresenter<T> pojoEditPresenter;

	protected void setPojoListView(PojoListView<T> pojoListView)
	{
		this.pojoListView = pojoListView;
		pojoListView.addListViewListener(this);
		pojoListView.addViewListener(this);
	}

	protected PojoListView<T> getPojoListView()
	{
		return pojoListView;
	}

	@Override
	public void setView(AbstractView asbtactView)
	{
		setPojoListView((PojoListView<T>) asbtactView);
	}

	@Override
	public AbstractView getAbstractView()
	{
		return pojoListView;
	}

	public void setListSelectable(boolean listSelectable)
	{
		this.listSelectable = listSelectable;
	}

	public void addItem(TableEvent crudTableEvent)
	{
		openEditWindowFromEvent(crudTableEvent, true);
	}

	public void editItem(TableEvent crudTableEvent)
	{
		openEditWindowFromEvent(crudTableEvent, false);
	}

	public void openEditWindowFromEvent(TableEvent crudTableEvent, boolean newObject)
	{
		pojoEditPresenter.setPojoObject((T) crudTableEvent.getPojoObject());
		pojoEditPresenter.setNewObject(newObject);
		goToNextView(pojoEditPresenter);
	}

	public void removeItem(TableEvent crudTableEvent)
	{
		getPojoService().remove((T) crudTableEvent.getPojoObject());
		crudTableEvent.getCrudTable().removeItem(crudTableEvent.getPojoObject());
	}

	public void doubleClickedItem(TableEvent crudTableEvent)
	{
		if (pojoListView.isSelectable())
			selectedItem(crudTableEvent);
		else
			editItem(crudTableEvent);
	}

	public void selectedItem(TableEvent event)
	{
		((SelectListener) getParentPresenter()).returnValue(this, event.getPojoObject());
		pojoListView.closeLastWindow();
	}

	@Override
	public void initializeView(AbstractView abstractView)
	{
		pojoListView.setSelectable(listSelectable);
		pojoListView.setList(getPojoService().getAll(typeClass));
	}

	protected void setPojoEditPresenter(PojoEditPresenter<T> pojoEditPresenter)
	{
		this.pojoEditPresenter = pojoEditPresenter;
	}

	@Override
	public void focusAfterClosedWindow(ViewEvent viewEvent)
	{
		if (viewEvent.getClosedWindowView() == pojoListView)
			getParentPresenter().focusAfterClosedWindow(viewEvent);
	}
}
