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
		pojoEditPresenter.setPojoObject((T) crudTableEvent.getPojoObject());
		goToNextView(pojoEditPresenter);
	}

	public void editItem(TableEvent crudTableEvent)
	{
		pojoEditPresenter.setPojoObject((T) crudTableEvent.getPojoObject());
		goToNextView(pojoEditPresenter);
	}

	public void removeItem(TableEvent crudTableEvent)
	{
		getPojoService().remove((T) crudTableEvent.getPojoObject());
		initializeView(null);
	}

	public void doubleClickedItem(TableEvent crudTableEvent)
	{
		editItem(crudTableEvent);
	}

	public void selectedItem(TableEvent event)
	{
	}

	@Override
	public void initializeView(ViewEvent viewEvent)
	{
		pojoListView.setSelectable(listSelectable);
		pojoListView.setList(getPojoService().getAll());
	}

	protected void setPojoEditPresenter(PojoEditPresenter<T> pojoEditPresenter)
	{
		this.pojoEditPresenter = pojoEditPresenter;
	}
}
