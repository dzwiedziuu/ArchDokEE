package com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionShapes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ProjectionShape;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class ProjectionShapeEditPresenter extends PojoEditPresenter<ProjectionShape>
{
	public interface ProjectionShapeEditView extends PojoEditView<ProjectionShape>
	{
	}

	@Autowired
	public ProjectionShapeEditPresenter(ProjectionShapeEditView pojoEditView, AbstractServiceInterface<ProjectionShape> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
