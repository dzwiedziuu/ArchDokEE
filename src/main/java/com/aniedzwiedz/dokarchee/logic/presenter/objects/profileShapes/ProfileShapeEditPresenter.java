package com.aniedzwiedz.dokarchee.logic.presenter.objects.profileShapes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProfileShape;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ProfileShapeEditPresenter extends PojoEditPresenter<ProfileShape>
{
	public interface ProfileShapeEditView extends PojoEditView<ProfileShape>
	{
	}

	@Autowired
	public ProfileShapeEditPresenter(ProfileShapeEditView pojoEditView, AbstractServiceInterface<ProfileShape> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
