package com.aniedzwiedz.dokarchee.gui.view.photos.subject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.photos.subjects.PhotoSubjectListPresenter.PhotoSubjectListView;

@Component(PhotoSubjectListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(PhotoSubjectListViewImpl.VIEW_NAME)
public class PhotoSubjectListViewImpl extends AbstractPojoListView<PhotoSubject> implements PhotoSubjectListView
{
	static final String VIEW_NAME = "PHOTOSUBJECT_LIST";

	public PhotoSubjectListViewImpl()
	{
		super(PhotoSubject.class);
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
