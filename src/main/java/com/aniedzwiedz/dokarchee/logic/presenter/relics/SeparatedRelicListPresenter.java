package com.aniedzwiedz.dokarchee.logic.presenter.relics;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.SeparatedRelic;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class SeparatedRelicListPresenter extends PojoListPresenter<SeparatedRelic>
{
	public interface SeparatedRelicListView extends PojoListView<SeparatedRelic>
	{
	}

	@Autowired
	public SeparatedRelicListPresenter(SeparatedRelicListView pojoListView, AbstractServiceInterface<SeparatedRelic> pojoService,
			SeparatedRelicEditPresenter pojoEditPresenter)
	{
		super(SeparatedRelic.class);
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
	protected SeparatedRelic getEmptyObject()
	{
		BusinessContext bc = SessionUtils.getCurrentBusinessContext();
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		SeparatedRelic separatedRelic = new SeparatedRelic();
		separatedRelic.setBusinessContext(bc);
		return separatedRelic;
	}
}
