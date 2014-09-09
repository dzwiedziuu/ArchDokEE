package com.aniedzwiedz.dokarchee.gui.view.relics;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.SeparatedRelic;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.SeparatedRelicListPresenter.SeparatedRelicListView;

@Component(SeparatedRelicListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(SeparatedRelicListViewImpl.VIEW_NAME)
public class SeparatedRelicListViewImpl extends AbstractPojoListView<SeparatedRelic> implements SeparatedRelicListView
{
	public static final String VIEW_NAME = "SeparatedRelic_LIST";

	public SeparatedRelicListViewImpl()
	{
		super(SeparatedRelic.class);
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
		return "Lista zabytków wydzielonych";
	}
}
