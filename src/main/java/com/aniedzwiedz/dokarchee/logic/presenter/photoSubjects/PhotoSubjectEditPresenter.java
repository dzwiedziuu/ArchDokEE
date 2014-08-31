package com.aniedzwiedz.dokarchee.logic.presenter.photoSubjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class PhotoSubjectEditPresenter extends PojoEditPresenter<PhotoSubject>
{
	public interface PhotoSubjectEditView extends PojoEditView<PhotoSubject>
	{
	}

	@Autowired
	public PhotoSubjectEditPresenter(PhotoSubjectEditView pojoEditView, AbstractServiceInterface<PhotoSubject> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
