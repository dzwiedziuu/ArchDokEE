package com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ChronologyEditPresenter extends PojoEditPresenter<Chronology>
{
	public interface ChronologyEditView extends PojoEditView<Chronology>
	{
	}

	@Autowired
	public ChronologyEditPresenter(ChronologyEditView pojoEditView, AbstractServiceInterface<Chronology> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
