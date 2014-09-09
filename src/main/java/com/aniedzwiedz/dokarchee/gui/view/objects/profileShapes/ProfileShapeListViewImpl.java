package com.aniedzwiedz.dokarchee.gui.view.objects.profileShapes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProfileShape;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.profileShapes.ProfileShapeListPresenter.ProfileShapeListView;

@Component(ProfileShapeListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProfileShapeListViewImpl.VIEW_NAME)
public class ProfileShapeListViewImpl extends AbstractPojoListView<ProfileShape> implements ProfileShapeListView
{
	public static final String VIEW_NAME = "PROFILE_SHAPE_LIST";

	public ProfileShapeListViewImpl()
	{
		super(ProfileShape.class);
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
		return "Lista kszta³tów profilu";
	}
}
