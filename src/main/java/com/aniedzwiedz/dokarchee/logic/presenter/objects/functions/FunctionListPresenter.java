package com.aniedzwiedz.dokarchee.logic.presenter.objects.functions;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Function;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class FunctionListPresenter extends PojoListPresenter<Function>
{
	public interface FunctionListView extends PojoListView<Function>
	{
	}

	@Autowired
	public FunctionListPresenter(FunctionListView pojoListView, AbstractServiceInterface<Function> pojoService,
			FunctionEditPresenter pojoEditPresenter)
	{
		super(Function.class);
		setView(pojoListView);
		setPojoService(pojoService);
		pojoEditPresenter.setPresentsInWindow(true);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return null;
	}

	@Override
	protected Function getEmptyObject()
	{
		return new Function();
	}
}
