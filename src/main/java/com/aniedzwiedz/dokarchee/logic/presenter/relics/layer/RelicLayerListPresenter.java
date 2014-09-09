package com.aniedzwiedz.dokarchee.logic.presenter.relics.layer;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.RelicLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class RelicLayerListPresenter extends PojoListPresenter<RelicLayer>
{
	public interface RelicLayerListView extends PojoListView<RelicLayer>
	{
	}

	@Autowired
	public RelicLayerListPresenter(RelicLayerListView pojoListView, AbstractServiceInterface<RelicLayer> pojoService,
			RelicLayerEditPresenter pojoEditPresenter)
	{
		super(RelicLayer.class);
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
	protected RelicLayer getEmptyObject()
	{
		return new RelicLayer();
	}
}
