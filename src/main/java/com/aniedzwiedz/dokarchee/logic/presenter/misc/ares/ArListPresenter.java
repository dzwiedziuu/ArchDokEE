package com.aniedzwiedz.dokarchee.logic.presenter.misc.ares;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.service.ArService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class ArListPresenter extends PojoListPresenter<Are>
{
	public interface ArListView extends PojoListView<Are>
	{
	}

	@Autowired
	public ArListPresenter(ArListView pojoListView, ArService pojoService, ArEditPresenter pojoEditPresenter)
	{
		super(Are.class);
		setView(pojoListView);
		setPojoService(pojoService);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Are getEmptyObject()
	{
		BusinessContext bc = SessionUtils.getCurrentBusinessContext();
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		Are ar = new Are();
		ar.setBusinessContext(bc);
		return ar;
	}
}
