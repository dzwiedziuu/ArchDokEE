package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.pojo.ShowListObjectView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.photoSubjects.PhotoSubjectListPresenter;

@Component
@Scope("session")
public class PhotoEditPresenter extends PojoEditPresenter<Photo>
{
	public interface PhotoEditView extends PojoEditView<Photo>
	{
	}

	@Autowired
	public PhotoEditPresenter(PhotoEditView photoEditView, PhotoService pojoService)
	{
		setView(photoEditView);
		setPojoService(pojoService);
	}

	@Autowired
	private PhotoSubjectListPresenter photoSubjectListPresenter;

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		if (action instanceof ShowListObjectView)
		{
			ShowListObjectView<?> showListObjectView = (ShowListObjectView<?>) action;
			Class<?> listClass = showListObjectView.getClassObj();
			if (PhotoSubject.class.isAssignableFrom(listClass))
				return photoSubjectListPresenter;
		}
		return super.getNextPresenter(action);
	}
}
