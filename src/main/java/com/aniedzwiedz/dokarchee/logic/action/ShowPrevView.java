package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class ShowPrevView extends Action
{
	@Override
	public void performAction()
	{
		AbstractPresenter currentPresenter = getCurrentPresenter();
		AbstractPresenter parent = currentPresenter.getParentPresenter();
		currentPresenter.setParentPresenter(null);
		if (!currentPresenter.isPresentsWindow())
			UI.getCurrent().getNavigator().navigateTo(parent.getAbstractView().getViewName());
		else
		{
			for (Window window : UI.getCurrent().getWindows())
				if (window.isEnabled() == true)
					System.out.println("ssss");
		}
	}
}
