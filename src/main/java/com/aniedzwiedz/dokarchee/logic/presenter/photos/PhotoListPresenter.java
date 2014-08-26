package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class PhotoListPresenter extends PojoListPresenter<Photo>
{
	public interface PhotoListView extends PojoListView<Photo>
	{
	}

	@Autowired
	private PhotoService pojoService;

	@Autowired
	private PhotoListView pojoListView;

	@Autowired
	private PhotoEditPresenter pojoEditPresenter;

	@Override
	protected void setPojoListView(PojoListView<Photo> pojoListView)
	{
		this.pojoListView = (PhotoListView) pojoListView;
	}

	@Override
	protected PojoListView<Photo> getPojoListView()
	{
		return pojoListView;
	}

	@Override
	public PojoService<Photo> getPojoService()
	{
		return pojoService;
	}

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return pojoEditPresenter;
	}
}
