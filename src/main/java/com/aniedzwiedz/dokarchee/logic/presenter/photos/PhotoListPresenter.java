package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class PhotoListPresenter extends PojoListPresenter<Photo>
{
	public interface PhotoListView extends PojoListView<Photo>
	{
	}

	@Autowired
	public PhotoListPresenter(PhotoListView pojoListView, PhotoService pojoService, PhotoEditPresenter pojoEditPresenter)
	{
		super(Photo.class);
		setView(pojoListView);
		setPojoService(pojoService);
		pojoEditPresenter.setPresentsInWindow(true);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return SessionUtils.getBusinessContextCriterion("businessContext");
	}

	@Override
	protected Photo getEmptyObject()
	{
		BusinessContext bc = SessionUtils.getCurrentBusinessContext();
		if (bc == null)
			throw new RuntimeException("Business context not selected");
		Photo photo = new Photo();
		photo.setBusinessContext(bc);
		return photo;
	}
}
