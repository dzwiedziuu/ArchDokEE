package com.aniedzwiedz.dokarchee.gui.view.figures.subjects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.FigureSubject;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.subjects.FigureSubjectListPresenter.FigureSubjectListView;

@Component(FigureSubjectListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FigureSubjectListViewImpl.VIEW_NAME)
public class FigureSubjectListViewImpl extends AbstractPojoListView<FigureSubject> implements FigureSubjectListView
{
	public static final String VIEW_NAME = "FIGURE_SUBJECT_LIST";

	public FigureSubjectListViewImpl()
	{
		super(FigureSubject.class);
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
		return "Lista tematów rysunków";
	}
}
