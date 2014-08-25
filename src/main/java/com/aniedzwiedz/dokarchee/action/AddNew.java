package com.aniedzwiedz.dokarchee.action;

import com.aniedzwiedz.dokarchee.presenter.list.PojoListPresenter;
import com.aniedzwiedz.dokarchee.view.AbstractView;

public class AddNew<T> extends PojoAction<T>
{
	public AddNew(AbstractView sender)
	{
		super(sender);
	}

	@Override
	public void performAction()
	{
		((PojoListPresenter<T>) getAbstractPresenter()).openEditWindow(getPojoObject());
	}
}
