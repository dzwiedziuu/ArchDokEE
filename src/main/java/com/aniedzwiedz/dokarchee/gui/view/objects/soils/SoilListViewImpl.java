package com.aniedzwiedz.dokarchee.gui.view.objects.soils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Soil;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.soils.SoilListPresenter.SoilListView;

@Component(SoilListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(SoilListViewImpl.VIEW_NAME)
public class SoilListViewImpl extends AbstractPojoListView<Soil> implements SoilListView
{
	public static final String VIEW_NAME = "SOIL_LIST";

	public SoilListViewImpl()
	{
		super(Soil.class);
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
		return "Lista calców";
	}
}
