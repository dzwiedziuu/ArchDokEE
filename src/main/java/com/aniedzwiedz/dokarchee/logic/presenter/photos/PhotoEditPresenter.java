package com.aniedzwiedz.dokarchee.logic.presenter.photos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class PhotoEditPresenter extends PojoEditPresenter<Photo>
{
	public interface PhotoEditView extends PojoEditView<Photo>
	{
	}

	@Autowired
	private PhotoService userService;

	@Autowired
	private PhotoEditView photoEditView;

	@Override
	protected PojoEditView<Photo> getPojoEditView()
	{
		return photoEditView;
	}

	@Override
	protected void setPojoEditView(PojoEditView<Photo> namedView)
	{
		this.photoEditView = (PhotoEditView) namedView;
	}

	@Override
	public PojoService<Photo> getPojoService()
	{
		return userService;
	}
}
