package com.aniedzwiedz.dokarchee.gui.view.objects.profileShapes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProfileShape;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.profileShapes.ProfileShapeEditPresenter.ProfileShapeEditView;

@Component(ProfileShapeEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProfileShapeEditViewImpl.VIEW_NAME)
public class ProfileShapeEditViewImpl extends AbstractPojoEditView<ProfileShape> implements ProfileShapeEditView
{
	public static final String VIEW_NAME = "PROFILE_SHAPE_EDIT";

	public ProfileShapeEditViewImpl()
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
		return "Edycja kszta³tu profilu";
	}
}
