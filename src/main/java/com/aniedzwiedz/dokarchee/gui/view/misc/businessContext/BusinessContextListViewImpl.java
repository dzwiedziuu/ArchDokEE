package com.aniedzwiedz.dokarchee.gui.view.misc.businessContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.businessContext.BusinessContextListPresenter.BusinessContextListView;

@Component(BusinessContextListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(BusinessContextListViewImpl.VIEW_NAME)
public class BusinessContextListViewImpl extends AbstractPojoListView<BusinessContext> implements BusinessContextListView
{
	static final String VIEW_NAME = "BUSINESS_CONTEXT_LIST";

	public BusinessContextListViewImpl()
	{
		super(BusinessContext.class);
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Lista opracowañ";
	}
}
