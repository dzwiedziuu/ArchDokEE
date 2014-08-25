package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.preAction.BlankPreAction;
import com.aniedzwiedz.dokarchee.logic.action.preAction.PreAction;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;

public class RemoveRecord<T> extends PojoAction<T>
{
	private Refresh refresh;

	public RemoveRecord(AbstractView sender)
	{
		this(sender, new BlankPreAction(sender));
	}

	public RemoveRecord(AbstractView sender, PreAction preAction)
	{
		super(sender);
		setPreAction(preAction);
		refresh = new Refresh(sender);
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
