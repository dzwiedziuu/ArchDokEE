package com.aniedzwiedz.dokarchee.action;

import com.aniedzwiedz.dokarchee.presenter.list.PojoListPresenter;
import com.aniedzwiedz.dokarchee.view.AbstractView;

public class Edit<T> extends PojoAction<T>
{
	public Edit(AbstractView sender)
	{
		super(sender);
	}

	@Override
	public void performAction()
	{
		((PojoListPresenter<T>) getAbstractPresenter()).openEditWindow(getPojoObject());
	}

}
