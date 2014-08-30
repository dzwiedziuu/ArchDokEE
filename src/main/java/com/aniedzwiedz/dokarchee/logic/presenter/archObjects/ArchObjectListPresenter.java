package com.aniedzwiedz.dokarchee.logic.presenter.archObjects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.vaadin.server.VaadinSession;

@Component
@Scope("session")
public class ArchObjectListPresenter extends PojoListPresenter<ArchObject>
{
	public interface ArchObjectListView extends PojoListView<ArchObject>
	{
	}

	@Autowired
	public ArchObjectListPresenter(ArchObjectListView pojoListView, AbstractServiceInterface<ArchObject> pojoService,
			ArchObjectEditPresenter pojoEditPresenter)
	{
		super(ArchObject.class);
		setView(pojoListView);
		setPojoService(pojoService);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		BusinessContext bc = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		return Restrictions.eq("businessContext", bc);
	}

	@Override
	protected ArchObject getEmptyObject()
	{
		BusinessContext bc = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		ArchObject archObject = new ArchObject();
		archObject.setBusinessContext(bc);
		return archObject;
	}
}
