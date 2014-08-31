package com.aniedzwiedz.dokarchee.logic.presenter.figures.subjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.FigureSubject;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class FigureSubjectEditPresenter extends PojoEditPresenter<FigureSubject>
{
	public interface FigureSubjectEditView extends PojoEditView<FigureSubject>
	{
	}

	@Autowired
	public FigureSubjectEditPresenter(FigureSubjectEditView pojoEditView, AbstractServiceInterface<FigureSubject> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
