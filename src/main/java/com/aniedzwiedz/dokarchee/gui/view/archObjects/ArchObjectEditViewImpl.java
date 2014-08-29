package com.aniedzwiedz.dokarchee.gui.view.archObjects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.archObjects.ArchObjectEditPresenter.ArchObjectEditView;

@Component(ArchObjectEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ArchObjectEditViewImpl.VIEW_NAME)
public class ArchObjectEditViewImpl extends AbstractPojoEditView<ArchObject> implements ArchObjectEditView
{
	public static final String VIEW_NAME = "ARCH_OBJECT_EDIT";

	public ArchObjectEditViewImpl()
	{
		super();
		setSizeFull();
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
