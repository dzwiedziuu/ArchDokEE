package com.aniedzwiedz.dokarchee.gui.view.figures;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Figure;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.FigureEditPresenter.FigureEditView;

@Component(FigureEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FigureEditViewImpl.VIEW_NAME)
public class FigureEditViewImpl extends AbstractPojoEditView<Figure> implements FigureEditView
{
	static final String VIEW_NAME = "Figure_EDIT";

	public FigureEditViewImpl()
	{
		super();
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
