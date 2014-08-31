package com.aniedzwiedz.dokarchee.gui.view.figures.specifications;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.FigureSpecification;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.specifications.FigureSpecificationEditPresenter.FigureSpecificationEditView;

@Component(FigureSpecificationEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FigureSpecificationEditViewImpl.VIEW_NAME)
public class FigureSpecificationEditViewImpl extends AbstractPojoEditView<FigureSpecification> implements FigureSpecificationEditView
{
	static final String VIEW_NAME = "Figure_Specification_EDIT";

	public FigureSpecificationEditViewImpl()
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
