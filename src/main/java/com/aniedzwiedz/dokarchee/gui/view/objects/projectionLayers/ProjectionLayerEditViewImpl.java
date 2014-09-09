package com.aniedzwiedz.dokarchee.gui.view.objects.projectionLayers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProjectionLayer;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionLayers.ProjectionLayerEditPresenter.ProjectionLayerEditView;

@Component(ProjectionLayerEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProjectionLayerEditViewImpl.VIEW_NAME)
public class ProjectionLayerEditViewImpl extends AbstractPojoEditView<ProjectionLayer> implements ProjectionLayerEditView
{
	public static final String VIEW_NAME = "PROJECTION_LAYER_EDIT";

	public ProjectionLayerEditViewImpl()
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
		return "Edycja zarysu rzutu";
	}
}
