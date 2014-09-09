package com.aniedzwiedz.dokarchee.logic.presenter.relics.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.RelicGroup;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class RelicGroupEditPresenter extends PojoEditPresenter<RelicGroup>
{
	public interface RelicGroupEditView extends PojoEditView<RelicGroup>
	{
	}

	@Autowired
	public RelicGroupEditPresenter(RelicGroupEditView pojoEditView, AbstractServiceInterface<RelicGroup> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
