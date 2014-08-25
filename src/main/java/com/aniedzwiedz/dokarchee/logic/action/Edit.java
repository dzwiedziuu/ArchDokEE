package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.list.PojoListPresenter;

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
