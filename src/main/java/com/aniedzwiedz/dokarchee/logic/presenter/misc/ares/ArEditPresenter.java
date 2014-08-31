package com.aniedzwiedz.dokarchee.logic.presenter.misc.ares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.service.ArService;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ArEditPresenter extends PojoEditPresenter<Are>
{
	public interface ArEditView extends PojoEditView<Are>
	{
	}

	@Autowired
	public ArEditPresenter(ArEditView pojoEditView, ArService pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
