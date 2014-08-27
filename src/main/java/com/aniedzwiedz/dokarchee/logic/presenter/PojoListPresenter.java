package com.aniedzwiedz.dokarchee.logic.presenter;

import java.util.List;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.Action;

public abstract class PojoListPresenter<T> extends PojoPresenter<T>
{
	public interface PojoListView<T> extends AbstractView
	{
		public void setList(List<T> list);
	}

	private PojoListView<T> pojoListView;
	private AbstractPresenter nextPresenter;

	protected void setPojoListView(PojoListView<T> pojoListView)
	{
		this.pojoListView = pojoListView;
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
		return getPojoListView();
	}

	@Override
	public void refreshView()
	{
		getPojoListView().setList(getPojoService().getAll());
	}

	public void setNextPresenter(AbstractPresenter abstractPresenter)
	{
		this.nextPresenter = abstractPresenter;
	}

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return nextPresenter;
	}
}
