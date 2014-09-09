package com.aniedzwiedz.dokarchee.logic.presenter.objects.soils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Soil;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class SoilEditPresenter extends PojoEditPresenter<Soil>
{
	public interface SoilEditView extends PojoEditView<Soil>
	{
	}

	@Autowired
	public SoilEditPresenter(SoilEditView pojoEditView, AbstractServiceInterface<Soil> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
