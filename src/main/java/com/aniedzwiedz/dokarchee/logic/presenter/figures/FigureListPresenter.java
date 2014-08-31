package com.aniedzwiedz.dokarchee.logic.presenter.figures;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.Figure;
import com.aniedzwiedz.dokarchee.data.service.FigureService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class FigureListPresenter extends PojoListPresenter<Figure>
{
	public interface FigureListView extends PojoListView<Figure>
	{
	}

	@Autowired
	public FigureListPresenter(FigureService pojoService, FigureListView pojoListView, FigureEditPresenter figureEditPresenter)
	{
		super(Figure.class);
		setPojoService(pojoService);
		setPojoListView(pojoListView);
		figureEditPresenter.setPresentsInWindow(true);
		setPojoEditPresenter(figureEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return SessionUtils.getBusinessContextCriterion("businessContext");
	}

	@Override
	protected Figure getEmptyObject()
	{
		BusinessContext bc = SessionUtils.getCurrentBusinessContext();
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		Figure figure = new Figure();
		figure.setBusinessContext(bc);
		return figure;
	}
}
