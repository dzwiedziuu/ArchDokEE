package com.aniedzwiedz.dokarchee.gui.view.objects.soils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Soil;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.soils.SoilEditPresenter.SoilEditView;

@Component(SoilEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(SoilEditViewImpl.VIEW_NAME)
public class SoilEditViewImpl extends AbstractPojoEditView<Soil> implements SoilEditView
{
	public static final String VIEW_NAME = "SOIL_EDIT";

	public SoilEditViewImpl()
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
		return "Edycja calca";
	}
}
