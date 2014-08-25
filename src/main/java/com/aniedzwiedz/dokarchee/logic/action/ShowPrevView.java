package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.vaadin.ui.UI;

public class ShowPrevView extends Action
{
	public ShowPrevView(AbstractView abstractView)
	{
		super(abstractView);
	}

	@Override
	public void performAction()
	{
		AbstractPresenter parent = getCurrentPresenter().getParentPresenter();
		getCurrentPresenter().setParentPresenter(null);
		UI.getCurrent().getNavigator().navigateTo(parent.getAbstractView().getViewName());
	}

}
