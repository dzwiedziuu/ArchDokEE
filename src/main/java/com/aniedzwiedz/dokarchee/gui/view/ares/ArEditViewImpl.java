package com.aniedzwiedz.dokarchee.gui.view.ares;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.ares.ArEditPresenter.ArEditView;

@Component(ArEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ArEditViewImpl.VIEW_NAME)
public class ArEditViewImpl extends AbstractPojoEditView<Are> implements ArEditView
{
	public static final String VIEW_NAME = "AR_EDIT";

	public ArEditViewImpl()
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
