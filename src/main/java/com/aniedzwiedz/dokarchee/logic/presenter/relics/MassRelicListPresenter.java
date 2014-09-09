package com.aniedzwiedz.dokarchee.logic.presenter.relics;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.MassRelic;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class MassRelicListPresenter extends PojoListPresenter<MassRelic>
{
	public interface MassRelicListView extends PojoListView<MassRelic>
	{
	}

	@Autowired
	public MassRelicListPresenter(MassRelicListView pojoListView, AbstractServiceInterface<MassRelic> pojoService,
			MassRelicEditPresenter pojoEditPresenter)
	{
		super(MassRelic.class);
		setView(pojoListView);
		setPojoService(pojoService);
		pojoEditPresenter.setPresentsInWindow(true);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return SessionUtils.getBusinessContextCriterion("businessContext");
	}

	@Override
	protected MassRelic getEmptyObject()
	{
		BusinessContext bc = SessionUtils.getCurrentBusinessContext();
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		MassRelic massRelic = new MassRelic();
		massRelic.setBusinessContext(bc);
		return massRelic;
	}
}
