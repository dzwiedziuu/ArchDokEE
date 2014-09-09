package com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionShapes;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProjectionShape;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoListPresenter;

@Component
@Scope("session")
public class ProjectionShapeListPresenter extends PojoListPresenter<ProjectionShape>
{
	public interface ProjectionShapeListView extends PojoListView<ProjectionShape>
	{
	}

	@Autowired
	public ProjectionShapeListPresenter(ProjectionShapeListView pojoListView, AbstractServiceInterface<ProjectionShape> pojoService,
			ProjectionShapeEditPresenter pojoEditPresenter)
	{
		super(ProjectionShape.class);
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
	protected ProjectionShape getEmptyObject()
	{
		return new ProjectionShape();
	}
}
