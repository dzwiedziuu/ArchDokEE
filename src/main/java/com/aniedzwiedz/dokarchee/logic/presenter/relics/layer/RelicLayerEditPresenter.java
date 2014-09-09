package com.aniedzwiedz.dokarchee.logic.presenter.relics.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.RelicLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class RelicLayerEditPresenter extends PojoEditPresenter<RelicLayer>
{
	public interface RelicLayerEditView extends PojoEditView<RelicLayer>
	{
	}

	@Autowired
	public RelicLayerEditPresenter(RelicLayerEditView pojoEditView, AbstractServiceInterface<RelicLayer> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
