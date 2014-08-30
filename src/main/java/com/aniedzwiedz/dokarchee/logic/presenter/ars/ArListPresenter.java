package com.aniedzwiedz.dokarchee.logic.presenter.ars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.data.service.ArService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ArListPresenter extends PojoListPresenter<Ar>
{
	public interface ArListView extends PojoListView<Ar>
	{
	}

	@Autowired
	public ArListPresenter(ArListView pojoListView, ArService pojoService, ArEditPresenter pojoEditPresenter)
	{
		super(Ar.class);
		setView(pojoListView);
		setPojoService(pojoService);
		setPojoEditPresenter(pojoEditPresenter);
	}
}
