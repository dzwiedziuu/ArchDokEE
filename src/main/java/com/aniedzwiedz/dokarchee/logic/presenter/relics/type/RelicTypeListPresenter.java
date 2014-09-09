package com.aniedzwiedz.dokarchee.logic.presenter.relics.type;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.RelicType;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class RelicTypeListPresenter extends PojoListPresenter<RelicType>
{
	public interface RelicTypeListView extends PojoListView<RelicType>
	{
	}

	@Autowired
	public RelicTypeListPresenter(RelicTypeListView pojoListView, AbstractServiceInterface<RelicType> pojoService,
			RelicTypeEditPresenter pojoEditPresenter)
	{
		super(RelicType.class);
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
	protected RelicType getEmptyObject()
	{
		return new RelicType();
	}
}
