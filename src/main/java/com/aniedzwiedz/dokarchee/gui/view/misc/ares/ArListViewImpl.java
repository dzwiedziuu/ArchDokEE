package com.aniedzwiedz.dokarchee.gui.view.misc.ares;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.ares.ArListPresenter.ArListView;

@Component(ArListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ArListViewImpl.VIEW_NAME)
public class ArListViewImpl extends AbstractPojoListView<Are> implements ArListView
{
	public static final String VIEW_NAME = "AR_LIST";

	public ArListViewImpl()
	{
		super(Are.class);
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
		return "Lista arów";
	}
}
