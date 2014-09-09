package com.aniedzwiedz.dokarchee.gui.view.objects.profileLayers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProfileLayer;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.profileLayers.ProfileLayerEditPresenter.ProfileLayerEditView;

@Component(ProfileLayerEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProfileLayerEditViewImpl.VIEW_NAME)
public class ProfileLayerEditViewImpl extends AbstractPojoEditView<ProfileLayer> implements ProfileLayerEditView
{
	public static final String VIEW_NAME = "PROFILE_LAYER_EDIT";

	public ProfileLayerEditViewImpl()
	{
		super();
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Edycja warstwy profilu";
	}
}
