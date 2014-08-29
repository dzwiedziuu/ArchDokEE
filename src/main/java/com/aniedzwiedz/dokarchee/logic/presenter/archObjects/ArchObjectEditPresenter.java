package com.aniedzwiedz.dokarchee.logic.presenter.archObjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.service.ArchObjectService;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ArchObjectEditPresenter extends PojoEditPresenter<ArchObject>
{
	public interface ArchObjectEditView extends PojoEditView<ArchObject>
	{
	}

	@Autowired
	public ArchObjectEditPresenter(ArchObjectEditView pojoEditView, ArchObjectService pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected AbstractPresenter getDictionaryPresenter(Class<?> ffType)
	{
		return null;
	}

	@Override
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		return null;
	}
}
