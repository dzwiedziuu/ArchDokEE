package com.aniedzwiedz.dokarchee.logic.action.pojo;

import java.lang.reflect.Field;

import com.aniedzwiedz.dokarchee.data.model.utils.ModelUtils;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.action.ShowPrevView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;

public class SaveObject<T> extends PojoAction<T>
{
	private ShowPrevView showPrevView = new ShowPrevView();

	@Override
	public void setCurrentView(AbstractView currentView)
	{
		super.setCurrentView(currentView);
		showPrevView.setCurrentView(currentView);
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
		PojoService<T> pojoService = ((PojoPresenter<T>) getCurrentPresenter()).getPojoService();
		T pojoObject = getPojoObject();
		boolean newObj = checkIfObjectIsNew(pojoObject);
		if (newObj)
			pojoService.add(pojoObject);
		else
			pojoService.update(pojoObject);
		showPrevView.performAction();
	}

	private boolean checkIfObjectIsNew(T pojoObject)
	{
		try
		{
			for (Field field : ModelUtils.getObjectIdFields(pojoObject.getClass()))
			{
				field.setAccessible(true);
				if (field.get(pojoObject) == null)
					return true;
			}
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isObjectNecessary()
	{
		return true;
	}
}
