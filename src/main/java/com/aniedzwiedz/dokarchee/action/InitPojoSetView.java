package com.aniedzwiedz.dokarchee.action;

import com.aniedzwiedz.dokarchee.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.presenter.list.PojoListPresenter;
import com.aniedzwiedz.dokarchee.view.AbstractView;

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
