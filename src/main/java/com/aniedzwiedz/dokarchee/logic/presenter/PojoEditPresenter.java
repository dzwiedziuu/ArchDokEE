package com.aniedzwiedz.dokarchee.logic.presenter;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.Action;

public abstract class PojoEditPresenter<T> extends PojoPresenter<T>
{
	public interface PojoEditView<T> extends AbstractView
	{
		void setPojoObject(T t);
	}

	private T pojoObject;

	protected abstract PojoEditView<T> getPojoEditView();

	protected abstract void setPojoEditView(PojoEditView<T> namedView);

	@Override
	public AbstractView getAbstractView()
	{
		return getPojoEditView();
	}

	@Override
	public void setView(AbstractView namedView)
	{
		setPojoEditView((PojoEditView<T>) namedView);
	}

	@Override
	public void refreshView()
	{
		getPojoEditView().setPojoObject(pojoObject);
	}

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return null;
	}

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}
}
