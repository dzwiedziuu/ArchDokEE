package com.aniedzwiedz.dokarchee.gui.view.misc.chronologies;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies.ChronologyListPresenter.ChronologyListView;

@Component(ChronologyListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ChronologyListViewImpl.VIEW_NAME)
public class ChronologyListViewImpl extends AbstractPojoListView<Chronology> implements ChronologyListView
{
	public static final String VIEW_NAME = "Chronology_LIST";

	public ChronologyListViewImpl()
	{
		super(Chronology.class);
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
		return "Lista chronologii";
	}
}
