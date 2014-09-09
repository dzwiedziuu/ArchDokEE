package com.aniedzwiedz.dokarchee.logic.presenter.objects.profileShapes;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProfileShape;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ProfileShapeListPresenter extends PojoListPresenter<ProfileShape>
{
	public interface ProfileShapeListView extends PojoListView<ProfileShape>
	{
	}

	@Autowired
	public ProfileShapeListPresenter(ProfileShapeListView pojoListView, AbstractServiceInterface<ProfileShape> pojoService,
			ProfileShapeEditPresenter pojoEditPresenter)
	{
		super(ProfileShape.class);
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
	protected ProfileShape getEmptyObject()
	{
		return new ProfileShape();
	}
}
