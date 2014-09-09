package com.aniedzwiedz.dokarchee.logic.presenter.relics.group;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.RelicGroup;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class RelicGroupListPresenter extends PojoListPresenter<RelicGroup>
{
	public interface RelicGroupListView extends PojoListView<RelicGroup>
	{
	}

	@Autowired
	public RelicGroupListPresenter(RelicGroupListView pojoListView, AbstractServiceInterface<RelicGroup> pojoService,
			RelicGroupEditPresenter pojoEditPresenter)
	{
		super(RelicGroup.class);
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
	protected RelicGroup getEmptyObject()
	{
		return new RelicGroup();
	}
}
