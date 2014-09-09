package com.aniedzwiedz.dokarchee.gui.view.objects.projectionLayers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProjectionLayer;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionLayers.ProjectionLayerListPresenter.ProjectionLayerListView;

@Component(ProjectionLayerListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProjectionLayerListViewImpl.VIEW_NAME)
public class ProjectionLayerListViewImpl extends AbstractPojoListView<ProjectionLayer> implements ProjectionLayerListView
{
	public static final String VIEW_NAME = "PROJECTION_LAYER_LIST";

	public ProjectionLayerListViewImpl()
	{
		super(ProjectionLayer.class);
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
		return "Lista warstw rzutu";
	}
}
