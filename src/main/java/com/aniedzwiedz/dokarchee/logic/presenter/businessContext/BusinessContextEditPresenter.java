package com.aniedzwiedz.dokarchee.logic.presenter.businessContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class BusinessContextEditPresenter extends PojoEditPresenter<BusinessContext>
{
	public interface BusinessContextEditView extends PojoEditView<BusinessContext>
	{
	}

	@Autowired
	public BusinessContextEditPresenter(BusinessContextEditView pojoEditView, AbstractServiceInterface<BusinessContext> pojoService)
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
