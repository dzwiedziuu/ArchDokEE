package com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionLayers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProjectionLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ProjectionLayerEditPresenter extends PojoEditPresenter<ProjectionLayer>
{
	public interface ProjectionLayerEditView extends PojoEditView<ProjectionLayer>
	{
	}

	@Autowired
	public ProjectionLayerEditPresenter(ProjectionLayerEditView pojoEditView, AbstractServiceInterface<ProjectionLayer> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
