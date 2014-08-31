package com.aniedzwiedz.dokarchee.gui.view.photos.subject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.photos.subjects.PhotoSubjectEditPresenter.PhotoSubjectEditView;

@Component(PhotoSubjectEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(PhotoSubjectEditViewImpl.VIEW_NAME)
public class PhotoSubjectEditViewImpl extends AbstractPojoEditView<PhotoSubject> implements PhotoSubjectEditView
{
	static final String VIEW_NAME = "PHOTOSUBJECT_EDIT";

	public PhotoSubjectEditViewImpl()
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
