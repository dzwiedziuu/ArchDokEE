package com.aniedzwiedz.dokarchee.logic.presenter;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.aniedzwiedz.dokarchee.gui.view.AbstractListView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

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

	public void addItem(TableEvent crudTableEvent)
	{
		openEditWindowFromEvent(crudTableEvent, true, getEmptyObject());
	}

	public void editItem(TableEvent crudTableEvent)
	{
		openEditWindowFromEvent(crudTableEvent, false, (T) crudTableEvent.getPojoObject());
	}

	public void openEditWindowFromEvent(TableEvent crudTableEvent, boolean newObject, T pojoObject)
	{
		pojoEditPresenter.setPojoObject(pojoObject);
		pojoEditPresenter.setNewObject(newObject);
		goToNextView(pojoEditPresenter);
	}

	public void removeItem(TableEvent crudTableEvent)
	{
		try
		{
			getPojoService().remove((T) crudTableEvent.getPojoObject());
			crudTableEvent.getCrudTable().removeItem(crudTableEvent.getPojoObject());
		} catch (Exception e)
		{
			String message = "Usuwany rekord jest powiazany z innymi rekordami. Nie mozna go usunac. Usun najpierw rekordy odwolujace sie do niego.";
			Notification.show("Blad", message, Type.ERROR_MESSAGE);
		}
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
	public void refreshView(AbstractView abstractView)
	{
		pojoListView.setSelectable(isSelectable());
		pojoListView.setList(getPojoService().getAll(typeClass, getCriterion()));
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

	protected abstract Criterion getCriterion();

	protected abstract T getEmptyObject();
}
