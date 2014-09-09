package com.aniedzwiedz.dokarchee.gui.view.relics.group;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.RelicGroup;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.group.RelicGroupListPresenter.RelicGroupListView;

@Component(RelicGroupListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(RelicGroupListViewImpl.VIEW_NAME)
public class RelicGroupListViewImpl extends AbstractPojoListView<RelicGroup> implements RelicGroupListView
{
	public static final String VIEW_NAME = "RelicGroup_LIST";

	public RelicGroupListViewImpl()
	{
		super(RelicGroup.class);
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
		return "Lista grup zabytków";
	}
}
