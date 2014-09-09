package com.aniedzwiedz.dokarchee.gui.view.objects.cultures;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Culture;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.cultures.CultureListPresenter.CultureListView;

@Component(CultureListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(CultureListViewImpl.VIEW_NAME)
public class CultureListViewImpl extends AbstractPojoListView<Culture> implements CultureListView
{
	public static final String VIEW_NAME = "CULTURE_LIST";

	public CultureListViewImpl()
	{
		super(Culture.class);
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
		return "Lista kultur";
	}
}
