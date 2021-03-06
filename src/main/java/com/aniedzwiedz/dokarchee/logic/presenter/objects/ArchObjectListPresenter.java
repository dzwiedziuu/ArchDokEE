package com.aniedzwiedz.dokarchee.logic.presenter.objects;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.service.ArchObjectService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class ArchObjectListPresenter extends PojoListPresenter<ArchObject>
{
	public interface ArchObjectListView extends PojoListView<ArchObject>
	{
	}

	@Autowired
	public ArchObjectListPresenter(ArchObjectListView pojoListView, ArchObjectService pojoService, ArchObjectEditPresenter pojoEditPresenter)
	{
		super(ArchObject.class);
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
	protected ArchObject getEmptyObject()
	{
		BusinessContext bc = SessionUtils.getCurrentBusinessContext();
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		ArchObject archObject = new ArchObject();
		archObject.setBusinessContext(bc);
		return archObject;
	}
}
