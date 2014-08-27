package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.preAction.BlankPreAction;
import com.aniedzwiedz.dokarchee.logic.action.preAction.PreAction;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;

public class RemoveRecord<T> extends PojoAction<T>
{
	private Refresh refresh = new Refresh();

	public RemoveRecord()
	{
		this(new BlankPreAction());
	}

	public RemoveRecord(PreAction preAction)
	{
		setPreAction(preAction);
	}

	@Override
	public void setCurrentView(AbstractView currentView)
	{
		// TODO Auto-generated method stub
		super.setCurrentView(currentView);
		refresh.setCurrentView(currentView);
	}

	@Override
	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		super.setCurrentPresenter(currentPresenter);
		refresh.setCurrentPresenter(currentPresenter);
	}

	@Override
	public void performAction()
	{
		PojoService<T> pojoService = ((PojoPresenter<T>) getCurrentPresenter()).getPojoService();
		pojoService.remove(getPojoObject());
		refresh.performAction();
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
