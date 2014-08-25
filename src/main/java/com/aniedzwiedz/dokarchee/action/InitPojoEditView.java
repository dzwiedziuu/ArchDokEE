package com.aniedzwiedz.dokarchee.action;

import com.aniedzwiedz.dokarchee.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.presenter.edit.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.view.AbstractView;

public class InitPojoEditView<T> extends Init
{
	public InitPojoEditView(AbstractView abstractView)
	{
		super(abstractView);
	}

	@Override
	public void setAbstractPresenter(AbstractPresenter abstractPresenter)
	{
		if (!(abstractPresenter instanceof PojoEditPresenter))
			throw new RuntimeException("Not a PojoEditPresenter");
		super.setAbstractPresenter(abstractPresenter);
	}

	@Override
	public void performAction()
	{
		super.performAction();
		((PojoEditPresenter<T>) getAbstractPresenter()).setViewData();
	}
}
