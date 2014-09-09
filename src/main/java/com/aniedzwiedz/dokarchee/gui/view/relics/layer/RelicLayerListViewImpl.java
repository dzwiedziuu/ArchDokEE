package com.aniedzwiedz.dokarchee.gui.view.relics.layer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.RelicLayer;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.layer.RelicLayerListPresenter.RelicLayerListView;

@Component(RelicLayerListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(RelicLayerListViewImpl.VIEW_NAME)
public class RelicLayerListViewImpl extends AbstractPojoListView<RelicLayer> implements RelicLayerListView
{
	public static final String VIEW_NAME = "RelicLayer_LIST";

	public RelicLayerListViewImpl()
	{
		super(RelicLayer.class);
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
		return "Lista warstw zabytków";
	}
}
