package com.aniedzwiedz.dokarchee.gui.view.relics;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.MassRelic;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.MassRelicListPresenter.MassRelicListView;

@Component(MassRelicListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(MassRelicListViewImpl.VIEW_NAME)
public class MassRelicListViewImpl extends AbstractPojoListView<MassRelic> implements MassRelicListView
{
	public static final String VIEW_NAME = "MassRelic_LIST";

	public MassRelicListViewImpl()
	{
		super(MassRelic.class);
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
		return "Lista zabytków masowych";
	}
}
