package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.edit.PojoEditPresenter;

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
