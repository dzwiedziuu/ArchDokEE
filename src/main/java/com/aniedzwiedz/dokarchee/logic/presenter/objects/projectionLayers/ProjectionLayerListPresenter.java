package com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionLayers;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProjectionLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ProjectionLayerListPresenter extends PojoListPresenter<ProjectionLayer>
{
	public interface ProjectionLayerListView extends PojoListView<ProjectionLayer>
	{
	}

	@Autowired
	public ProjectionLayerListPresenter(ProjectionLayerListView pojoListView, AbstractServiceInterface<ProjectionLayer> pojoService,
			ProjectionLayerEditPresenter pojoEditPresenter)
	{
		super(ProjectionLayer.class);
		setView(pojoListView);
		setPojoService(pojoService);
		pojoEditPresenter.setPresentsInWindow(true);
		setPojoEditPresenter(pojoEditPresenter);
	}

	@Override
	protected Criterion getCriterion()
	{
		return null;
	}

	@Override
	protected ProjectionLayer getEmptyObject()
	{
		return new ProjectionLayer();
	}
}
