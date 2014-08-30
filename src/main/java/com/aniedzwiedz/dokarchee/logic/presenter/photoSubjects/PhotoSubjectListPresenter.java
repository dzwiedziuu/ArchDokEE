package com.aniedzwiedz.dokarchee.logic.presenter.photoSubjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class PhotoSubjectListPresenter extends PojoListPresenter<PhotoSubject>
{
	public interface PhotoSubjectListView extends PojoListView<PhotoSubject>
	{
	}

	@Autowired
	public PhotoSubjectListPresenter(AbstractServiceInterface<PhotoSubject> pojoService, PhotoSubjectListView pojoListView,
			PhotoSubjectEditPresenter photoSubjectEditPresenter)
	{
		super(PhotoSubject.class);
		setPojoService(pojoService);
		setPojoListView(pojoListView);
		setPojoEditPresenter(photoSubjectEditPresenter);
	}
}
