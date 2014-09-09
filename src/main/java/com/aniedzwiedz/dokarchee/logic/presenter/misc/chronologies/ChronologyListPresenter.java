package com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ChronologyListPresenter extends PojoListPresenter<Chronology>
{
	public interface ChronologyListView extends PojoListView<Chronology>
	{
	}

	@Autowired
	public ChronologyListPresenter(ChronologyListView pojoListView, AbstractServiceInterface<Chronology> pojoService,
			ChronologyEditPresenter pojoEditPresenter)
	{
		super(Chronology.class);
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
	protected Chronology getEmptyObject()
	{
		return new Chronology();
	}
}
