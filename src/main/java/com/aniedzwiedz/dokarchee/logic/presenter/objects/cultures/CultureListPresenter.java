package com.aniedzwiedz.dokarchee.logic.presenter.objects.cultures;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Culture;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class CultureListPresenter extends PojoListPresenter<Culture>
{
	public interface CultureListView extends PojoListView<Culture>
	{
	}

	@Autowired
	public CultureListPresenter(CultureListView pojoListView, AbstractServiceInterface<Culture> pojoService,
			CultureEditPresenter pojoEditPresenter)
	{
		super(Culture.class);
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
	protected Culture getEmptyObject()
	{
		return new Culture();
	}
}
