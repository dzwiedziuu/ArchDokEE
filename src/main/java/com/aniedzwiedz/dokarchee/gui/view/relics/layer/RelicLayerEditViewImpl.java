package com.aniedzwiedz.dokarchee.gui.view.relics.layer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.RelicLayer;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.layer.RelicLayerEditPresenter.RelicLayerEditView;

@Component(RelicLayerEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(RelicLayerEditViewImpl.VIEW_NAME)
public class RelicLayerEditViewImpl extends AbstractPojoEditView<RelicLayer> implements RelicLayerEditView
{
	public static final String VIEW_NAME = "RelicLayer_EDIT";

	public RelicLayerEditViewImpl()
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
		return "Edycja warstw zabytku";
	}
}
