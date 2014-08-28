package com.aniedzwiedz.dokarchee.logic.presenter.photoSubjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.service.PhotoSubjectService;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class PhotoSubjectEditPresenter extends PojoEditPresenter<PhotoSubject>
{
	public interface PhotoSubjectEditView extends PojoEditView<PhotoSubject>
	{
	}

	@Autowired
	public PhotoSubjectEditPresenter(PhotoSubjectEditView pojoEditView, PhotoSubjectService pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected AbstractPresenter getDictionaryPresenter(Class<?> ffType)
	{
		return null;
	}
}
