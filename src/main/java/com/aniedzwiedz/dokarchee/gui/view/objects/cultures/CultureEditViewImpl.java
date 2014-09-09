package com.aniedzwiedz.dokarchee.gui.view.objects.cultures;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Culture;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.cultures.CultureEditPresenter.CultureEditView;

@Component(CultureEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(CultureEditViewImpl.VIEW_NAME)
public class CultureEditViewImpl extends AbstractPojoEditView<Culture> implements CultureEditView
{
	public static final String VIEW_NAME = "CULTURE_EDIT";

	public CultureEditViewImpl()
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
		return "Edycja kultury";
	}
}
