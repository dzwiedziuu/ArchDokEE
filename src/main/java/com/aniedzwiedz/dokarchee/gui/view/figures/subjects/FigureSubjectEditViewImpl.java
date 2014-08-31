package com.aniedzwiedz.dokarchee.gui.view.figures.subjects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.FigureSubject;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.subjects.FigureSubjectEditPresenter.FigureSubjectEditView;

@Component(FigureSubjectEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FigureSubjectEditViewImpl.VIEW_NAME)
public class FigureSubjectEditViewImpl extends AbstractPojoEditView<FigureSubject> implements FigureSubjectEditView
{
	static final String VIEW_NAME = "Figure_Subject_EDIT";

	public FigureSubjectEditViewImpl()
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
