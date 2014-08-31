package com.aniedzwiedz.dokarchee.logic.presenter.figures.subjects;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.FigureSubject;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class FigureSubjectListPresenter extends PojoListPresenter<FigureSubject>
{
	public interface FigureSubjectListView extends PojoListView<FigureSubject>
	{
	}

	@Autowired
	public FigureSubjectListPresenter(AbstractServiceInterface<FigureSubject> pojoService, FigureSubjectListView pojoListView,
			FigureSubjectEditPresenter photoSubjectEditPresenter)
	{
		super(FigureSubject.class);
		setPojoService(pojoService);
		setPojoListView(pojoListView);
		setPojoEditPresenter(photoSubjectEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return null;
	}

	@Override
	protected FigureSubject getEmptyObject()
	{
		return new FigureSubject();
	}
}
