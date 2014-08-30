package com.aniedzwiedz.dokarchee.gui.view.photos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.photos.PhotoEditPresenter.PhotoEditView;

@Component(PhotoEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(PhotoEditViewImpl.VIEW_NAME)
public class PhotoEditViewImpl extends AbstractPojoEditView<Photo> implements PhotoEditView
{
	static final String VIEW_NAME = "PHOTO_EDIT";

	public PhotoEditViewImpl()
	{
		super();
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
