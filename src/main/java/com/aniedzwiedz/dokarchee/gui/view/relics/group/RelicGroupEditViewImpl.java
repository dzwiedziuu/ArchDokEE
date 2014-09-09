package com.aniedzwiedz.dokarchee.gui.view.relics.group;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.RelicGroup;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.group.RelicGroupEditPresenter.RelicGroupEditView;

@Component(RelicGroupEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(RelicGroupEditViewImpl.VIEW_NAME)
public class RelicGroupEditViewImpl extends AbstractPojoEditView<RelicGroup> implements RelicGroupEditView
{
	public static final String VIEW_NAME = "RelicGroup_EDIT";

	public RelicGroupEditViewImpl()
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
		return "Edycja grupy zabytków";
	}
}
