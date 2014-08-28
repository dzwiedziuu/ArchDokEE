package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.pojo.ShowListObjectView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.ars.ArListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.photoSubjects.PhotoSubjectListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.users.UserListPresenter;

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

	private PhotoSubjectListPresenter photoSubjectListPresenter;
	private UserListPresenter userListPresenter;
	private ArListPresenter arListPresenter;

	@Autowired
	public void setPhotoSubjectListPresenter(PhotoSubjectListPresenter photoSubjectListPresenter)
	{
		this.photoSubjectListPresenter = photoSubjectListPresenter;
		this.photoSubjectListPresenter.setListSelectable(true);
	}

	@Autowired
	public void setUserListPresenter(UserListPresenter userListPresenter)
	{
		this.userListPresenter = userListPresenter;
		this.userListPresenter.setListSelectable(true);
	}

	@Autowired
	public void setArListPresenter(ArListPresenter arListPresenter)
	{
		this.arListPresenter = arListPresenter;
		this.arListPresenter.setListSelectable(true);
	}

	public AbstractPresenter getNextPresenter(Action action)
	{
		if (action instanceof ShowListObjectView)
		{
			ShowListObjectView<?> showListObjectView = (ShowListObjectView<?>) action;
			Class<?> listClass = showListObjectView.getClassObj();
			if (PhotoSubject.class.isAssignableFrom(listClass))
				return photoSubjectListPresenter;
			if (User.class.isAssignableFrom(listClass))
				return userListPresenter;
			if (Ar.class.isAssignableFrom(listClass))
				return arListPresenter;
		}
		return null;
	}

	@Override
	protected AbstractPresenter getDictionaryPresenter(Class<?> ffType)
	{
		if (PhotoSubject.class.isAssignableFrom(ffType))
			return photoSubjectListPresenter;
		if (User.class.isAssignableFrom(ffType))
			return userListPresenter;
		// if (Ar.class.isAssignableFrom(ffType))
		// return arListPresenter;
		return null;
	}
}
