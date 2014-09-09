package com.aniedzwiedz.dokarchee.logic.presenter.objects.profileLayers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProfileLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ProfileLayerEditPresenter extends PojoEditPresenter<ProfileLayer>
{
	public interface ProfileLayerEditView extends PojoEditView<ProfileLayer>
	{
	}

	@Autowired
	public ProfileLayerEditPresenter(ProfileLayerEditView pojoEditView, AbstractServiceInterface<ProfileLayer> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
