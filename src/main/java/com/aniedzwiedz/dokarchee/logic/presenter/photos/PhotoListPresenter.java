package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

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
		// TODO
		return null;
	}

	@Override
	protected Photo getEmptyObject()
	{
		// TODO
		return new Photo();
	}
}
