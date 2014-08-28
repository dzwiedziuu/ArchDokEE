package com.aniedzwiedz.dokarchee.logic.presenter.ars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.data.service.ArService;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ArEditPresenter extends PojoEditPresenter<Ar>
{
	public interface ArEditView extends PojoEditView<Ar>
	{
	}

	@Autowired
	public ArEditPresenter(ArEditView pojoEditView, ArService pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected AbstractPresenter getDictionaryPresenter(Class<?> ffType)
	{
		return null;
	}
}
