package com.aniedzwiedz.dokarchee.gui.view.objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.ArchObjectListPresenter.ArchObjectListView;

@Component(ArchObjectListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ArchObjectListViewImpl.VIEW_NAME)
public class ArchObjectListViewImpl extends AbstractPojoListView<ArchObject> implements ArchObjectListView
{
	public static final String VIEW_NAME = "ARCH_OBJECT_LIST";

	public ArchObjectListViewImpl()
	{
		super(ArchObject.class);
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
