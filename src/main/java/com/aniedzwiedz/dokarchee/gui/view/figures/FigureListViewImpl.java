package com.aniedzwiedz.dokarchee.gui.view.figures;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Figure;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.FigureListPresenter.FigureListView;

@Component(FigureListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FigureListViewImpl.VIEW_NAME)
public class FigureListViewImpl extends AbstractPojoListView<Figure> implements FigureListView
{
	public static final String VIEW_NAME = "FIGURE_LIST";

	public FigureListViewImpl()
	{
		super(Figure.class);
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Lista rysunków";
	}
}
