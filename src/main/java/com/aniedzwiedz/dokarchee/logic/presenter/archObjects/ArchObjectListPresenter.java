package com.aniedzwiedz.dokarchee.logic.presenter.archObjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ArchObjectListPresenter extends PojoListPresenter<ArchObject>
{
	public interface ArchObjectListView extends PojoListView<ArchObject>
	{
	}

	@Autowired
	public ArchObjectListPresenter(ArchObjectListView pojoListView, AbstractServiceInterface<ArchObject> pojoService,
			ArchObjectEditPresenter pojoEditPresenter)
	{
		super(ArchObject.class);
		setView(pojoListView);
		setPojoService(pojoService);
		setPojoEditPresenter(pojoEditPresenter);
	}
}
