package com.aniedzwiedz.dokarchee.gui.view.objects.projectionShapes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProjectionShape;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionShapes.ProjectionShapeEditPresenter.ProjectionShapeEditView;

@Component(ProjectionShapeEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProjectionShapeEditViewImpl.VIEW_NAME)
public class ProjectionShapeEditViewImpl extends AbstractPojoEditView<ProjectionShape> implements ProjectionShapeEditView
{
	public static final String VIEW_NAME = "PROJECTION_SHAPE_EDIT";

	public ProjectionShapeEditViewImpl()
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
