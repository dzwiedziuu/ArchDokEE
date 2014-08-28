package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormEvent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField.ForeignFieldEvent;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.aniedzwiedz.dokarchee.gui.view.AbstractEditView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractEditView.EditViewListener;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;

public abstract class PojoEditPresenter<T> extends PojoPresenter<T> implements EditViewListener<T>
{
	public interface PojoEditView<T> extends AbstractEditView<T>
	{
		void setPojoObject(T t);
	}

	private T pojoObject;
	private PojoEditView<T> pojoEditView;

	@Override
	public AbstractView getAbstractView()
	{
		return pojoEditView;
	}

	@Override
	public void setView(AbstractView namedView)
	{
		setPojoEditView((PojoEditView<T>) namedView);
	}

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}

	protected PojoEditView<T> getPojoEditView()
	{
		return pojoEditView;
	}

	protected void setPojoEditView(PojoEditView<T> namedView)
	{
		this.pojoEditView = namedView;
		pojoEditView.addEditViewListener(this);
		pojoEditView.addViewListener(this);
	}

	@Override
	public void dictionaryOpened(ForeignFieldEvent foreignFieldEvent)
	{
		Class<?> ffType = foreignFieldEvent.getForeignField().getType();
		AbstractPresenter abstractPresenter = getDictionaryPresenter(ffType);
		goToNextView(abstractPresenter);
	}

	protected abstract AbstractPresenter getDictionaryPresenter(Class<?> ffType);

	@Override
	public void addItem(TableEvent crudTableEvent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void editItem(TableEvent crudTableEvent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItem(TableEvent crudTableEvent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doubleClickedItem(TableEvent crudTableEvent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void selectedItem(TableEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void saveButtonClicked(FormEvent<T> formEvent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void discardButtonClicked(FormEvent<T> formEvent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeView(ViewEvent viewEvent)
	{
		pojoEditView.setPojoObject(pojoObject);
	}
}
