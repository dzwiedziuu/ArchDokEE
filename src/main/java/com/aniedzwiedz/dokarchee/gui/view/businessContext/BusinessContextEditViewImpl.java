package com.aniedzwiedz.dokarchee.gui.view.businessContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.businessContext.BusinessContextEditPresenter.BusinessContextEditView;

@Component(BusinessContextEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(BusinessContextEditViewImpl.VIEW_NAME)
public class BusinessContextEditViewImpl extends AbstractPojoEditView<BusinessContext> implements BusinessContextEditView
{
	static final String VIEW_NAME = "BUSINESS_CONTEXT_EDIT";

	public BusinessContextEditViewImpl()
	{
		super();
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
