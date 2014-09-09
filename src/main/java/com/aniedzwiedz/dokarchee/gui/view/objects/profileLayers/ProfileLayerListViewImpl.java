package com.aniedzwiedz.dokarchee.gui.view.objects.profileLayers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProfileLayer;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.profileLayers.ProfileLayerListPresenter.ProfileLayerListView;

@Component(ProfileLayerListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProfileLayerListViewImpl.VIEW_NAME)
public class ProfileLayerListViewImpl extends AbstractPojoListView<ProfileLayer> implements ProfileLayerListView
{
	public static final String VIEW_NAME = "PROFILE_LAYER_LIST";

	public ProfileLayerListViewImpl()
	{
		super(ProfileLayer.class);
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
		return "Lista warstw profilu";
	}
}
