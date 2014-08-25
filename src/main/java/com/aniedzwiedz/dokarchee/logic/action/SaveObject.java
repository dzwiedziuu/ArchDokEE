package com.aniedzwiedz.dokarchee.logic.action;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class SaveObject<T> extends PojoAction<T>
{
	private ShowPrevView showPrevView;
	private PojoService<T> pojoService;
	
	public SaveObject(AbstractView abstractView)
	{
		super(abstractView);
		showPrevView = new ShowPrevView(abstractView);
	}
	
	@Override
	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		super.setCurrentPresenter(currentPresenter);
		showPrevView.setCurrentPresenter(currentPresenter);
	}

	@Override
	public void performAction()
	{
		// need to set pojoService obj
		getCurrentPresenter().getParentPresenter().setParams(this);
		T pojoObject = getPojoObject();
		T obj = pojoService.find(pojoObject);
		if(obj == null)
			pojoService.add(pojoObject);
		else
			pojoService.update(pojoObject);
		showPrevView.performAction();
	}
	
	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}

	public void setPojoService(PojoService<T> pojoService)
	{
		this.pojoService = pojoService;
	}
}
