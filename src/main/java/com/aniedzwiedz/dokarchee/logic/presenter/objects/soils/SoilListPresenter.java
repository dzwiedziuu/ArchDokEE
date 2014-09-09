package com.aniedzwiedz.dokarchee.logic.presenter.objects.soils;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Soil;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class SoilListPresenter extends PojoListPresenter<Soil>
{
	public interface SoilListView extends PojoListView<Soil>
	{
	}

	@Autowired
	public SoilListPresenter(SoilListView pojoListView, AbstractServiceInterface<Soil> pojoService, SoilEditPresenter pojoEditPresenter)
	{
		super(Soil.class);
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
	protected Soil getEmptyObject()
	{
		return new Soil();
	}
}
