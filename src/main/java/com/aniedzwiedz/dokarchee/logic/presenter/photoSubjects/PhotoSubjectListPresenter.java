package com.aniedzwiedz.dokarchee.logic.presenter.photoSubjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.PhotoSubject;
import com.aniedzwiedz.dokarchee.data.service.PhotoSubjectService;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class PhotoSubjectListPresenter extends PojoListPresenter<PhotoSubject>
{
	public interface PhotoSubjectListView extends PojoListView<PhotoSubject>
	{
	}

	@Autowired
	private PhotoSubjectService pojoService;

	@Autowired
	private PhotoSubjectListView pojoListView;

	@Autowired
	private PhotoSubjectEditPresenter pojoEditPresenter;

	@Override
	protected void setPojoListView(PojoListView<PhotoSubject> pojoListView)
	{
		this.pojoListView = (PhotoSubjectListView) pojoListView;
	}

	@Override
	protected PojoListView<PhotoSubject> getPojoListView()
	{
		return pojoListView;
	}

	@Override
	public PojoService<PhotoSubject> getPojoService()
	{
		return pojoService;
	}

	@Override
	public AbstractPresenter getNextPresenter(Action action)
	{
		return pojoEditPresenter;
	}

}
