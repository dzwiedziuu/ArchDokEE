package com.aniedzwiedz.dokarchee.gui.view.objects.projectionShapes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ProjectionShape;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionShapes.ProjectionShapeListPresenter.ProjectionShapeListView;

@Component(ProjectionShapeListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ProjectionShapeListViewImpl.VIEW_NAME)
public class ProjectionShapeListViewImpl extends AbstractPojoListView<ProjectionShape> implements ProjectionShapeListView
{
	public static final String VIEW_NAME = "PROJECTION_SHAPE_LIST";

	public ProjectionShapeListViewImpl()
	{
		super(ProjectionShape.class);
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
		return "Lista zarysów rzutu";
	}
}
