package com.aniedzwiedz.dokarchee.logic.presenter.businessContext;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class BusinessContextListPresenter extends PojoListPresenter<BusinessContext>
{
	public interface BusinessContextListView extends PojoListView<BusinessContext>
	{
	}

	@Autowired
	public BusinessContextListPresenter(AbstractServiceInterface<BusinessContext> pojoService, BusinessContextListView pojoListView,
			BusinessContextEditPresenter photoSubjectEditPresenter)
	{
		super(BusinessContext.class);
		setPojoService(pojoService);
		setPojoListView(pojoListView);
		setPojoEditPresenter(photoSubjectEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return null;
	}

	@Override
	protected BusinessContext getEmptyObject()
	{
		return new BusinessContext();
	}
}
