package com.aniedzwiedz.dokarchee.logic.action.pojo;

import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.ShowNextView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public class ShowListObjectView<T> extends Action
{
	private Class<T> classObj;
	private ShowNextView showNextView;

	public ShowListObjectView(Class<T> classObj)
	{
		this(classObj, true);
	}

	public ShowListObjectView(Class<T> classObj, boolean showInNewWindow)
	{
		this.classObj = classObj;
		this.showNextView = new ShowNextView(showInNewWindow);
	}

	@Override
	public void setCurrentPresenter(AbstractPresenter currentPresenter)
	{
		super.setCurrentPresenter(currentPresenter);
		showNextView.setCurrentPresenter(currentPresenter);
	}

	@Override
	public void performAction()
	{
		showNextView.performAction();
	}

	public Class<T> getClassObj()
	{
		return classObj;
	}
}
