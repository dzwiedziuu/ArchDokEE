package com.aniedzwiedz.dokarchee.gui.view.relics.type;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.RelicType;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.type.RelicTypeListPresenter.RelicTypeListView;

@Component(RelicTypeListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(RelicTypeListViewImpl.VIEW_NAME)
public class RelicTypeListViewImpl extends AbstractPojoListView<RelicType> implements RelicTypeListView
{
	public static final String VIEW_NAME = "RelicType_LIST";

	public RelicTypeListViewImpl()
	{
		super(RelicType.class);
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
		return "Lista typów zabytków";
	}
}
