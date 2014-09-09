package com.aniedzwiedz.dokarchee.gui.view.photos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.photos.PhotoListPresenter.PhotoListView;

@Component(PhotoListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(PhotoListViewImpl.VIEW_NAME)
public class PhotoListViewImpl extends AbstractPojoListView<Photo> implements PhotoListView
{
	public static final String VIEW_NAME = "PHOTO_LIST";

	public PhotoListViewImpl()
	{
		super(Photo.class);
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Lista zdjêæ";
	}
}
