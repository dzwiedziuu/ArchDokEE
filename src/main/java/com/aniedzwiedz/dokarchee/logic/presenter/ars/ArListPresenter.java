package com.aniedzwiedz.dokarchee.logic.presenter.ars;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.service.ArService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.vaadin.server.VaadinSession;

@Component
@Scope("session")
public class ArListPresenter extends PojoListPresenter<Ar>
{
	public interface ArListView extends PojoListView<Ar>
	{
	}

	@Autowired
	public ArListPresenter(ArListView pojoListView, ArService pojoService, ArEditPresenter pojoEditPresenter)
	{
		super(Ar.class);
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
	protected Ar getEmptyObject()
	{
		BusinessContext bc = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		Ar ar = new Ar();
		ar.setBusinessContext(bc);
		return ar;
	}
}
