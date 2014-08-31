package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.ares.ArListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.users.UserListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.ArchObjectListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.photos.subjects.PhotoSubjectListPresenter;
import com.vaadin.server.VaadinSession;

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

	@Autowired
	private UserListPresenter userListPresenter;

	private ArListPresenter arListPresenter;

	@Autowired
	public void setArListPresenter(ArListPresenter arListPresenter)
	{
		this.arListPresenter = arListPresenter;
		this.arListPresenter.setSelectable(true);
	}

	@Autowired
	private ArchObjectListPresenter archObjectListPresenter;

	@Override
	protected PojoPresenter getDictionaryPresenter(Class<?> ffType)
	{
		if (PhotoSubject.class.isAssignableFrom(ffType))
			return photoSubjectListPresenter;
		if (User.class.isAssignableFrom(ffType))
			return userListPresenter;
		if (ArchObject.class.isAssignableFrom(ffType))
			return archObjectListPresenter;
		return null;
	}

	@Override
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		if (Are.class.isAssignableFrom(ffType))
			return arListPresenter;
		return null;
	}

	@Override
	protected DataProvider getDataProvider()
	{
		return photoDictDataProvider;
	}

	private PhotoDictDataProvider photoDictDataProvider = new PhotoDictDataProvider();

	private class PhotoDictDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> ffType)
		{
			if (PhotoSubject.class.isAssignableFrom(ffType))
				return (List<T>) photoSubjectService.getAll(PhotoSubject.class, null);
			else if (User.class.isAssignableFrom(ffType))
				return (List<T>) userListService.getAll(User.class, null);
			else if (ArchObject.class.isAssignableFrom(ffType))
			{
				BusinessContext bc = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
				return (List<T>) archObjectService.getAll(ArchObject.class, Restrictions.eq("businessContext", bc));
			}
			return null;
		}
	}

	@Autowired
	private AbstractServiceInterface<PhotoSubject> photoSubjectService;

	@Autowired
	private AbstractServiceInterface<User> userListService;

	@Autowired
	private AbstractServiceInterface<ArchObject> archObjectService;
}
