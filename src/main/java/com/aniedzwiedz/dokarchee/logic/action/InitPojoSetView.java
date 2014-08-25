package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.list.PojoListPresenter;

public class InitPojoSetView<T> extends Init
{
	public InitPojoSetView(AbstractView abstractView)
	{
		super(abstractView);
	}

	@Override
	public void setAbstractPresenter(AbstractPresenter abstractPresenter)
	{
		if (!(abstractPresenter instanceof PojoListPresenter))
			throw new RuntimeException("Not a PojoAbstractPresenter");
		super.setAbstractPresenter(abstractPresenter);
	}

	@Override
	public void performAction()
	{
		super.performAction();
		((PojoListPresenter<T>) getAbstractPresenter()).setViewData();
	}
}
