package com.aniedzwiedz.dokarchee.logic.presenter.objects.profileLayers;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProfileLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ProfileLayerListPresenter extends PojoListPresenter<ProfileLayer>
{
	public interface ProfileLayerListView extends PojoListView<ProfileLayer>
	{
	}

	@Autowired
	public ProfileLayerListPresenter(ProfileLayerListView pojoListView, AbstractServiceInterface<ProfileLayer> pojoService,
			ProfileLayerEditPresenter pojoEditPresenter)
	{
		super(ProfileLayer.class);
		setView(pojoListView);
		setPojoService(pojoService);
		pojoEditPresenter.setPresentsInWindow(true);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return null;
	}

	@Override
	protected ProfileLayer getEmptyObject()
	{
		return new ProfileLayer();
	}
}
