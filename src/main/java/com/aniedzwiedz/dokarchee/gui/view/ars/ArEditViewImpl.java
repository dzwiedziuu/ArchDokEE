package com.aniedzwiedz.dokarchee.gui.view.ars;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Ar;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.ars.ArEditPresenter.ArEditView;

@Component(ArEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ArEditViewImpl.VIEW_NAME)
public class ArEditViewImpl extends AbstractPojoEditView<Ar> implements ArEditView
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
