package com.aniedzwiedz.dokarchee.logic.presenter;

import java.util.List;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;

public abstract class PojoListPresenter<T> extends PojoPresenter<T>
{
	public interface PojoListView<T> extends AbstractView
	{
		public void setList(List<T> list);
	}

	protected abstract void setPojoListView(PojoListView<T> pojoListView);

	protected abstract PojoListView<T> getPojoListView();

	@Override
	public void setView(AbstractView asbtactView)
	{
		setPojoListView((PojoListView<T>) asbtactView);
	}

	@Override
	public AbstractView getAbstractView()
	{
		return getPojoListView();
	}

	@Override
	public void refreshView()
	{
		getPojoListView().setList(getPojoService().getAll());
	}
}