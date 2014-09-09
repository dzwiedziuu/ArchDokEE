package com.aniedzwiedz.dokarchee.gui.view.relics;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.MassRelic;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.MassRelicEditPresenter.MassRelicEditView;

@Component(MassRelicEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(MassRelicEditViewImpl.VIEW_NAME)
public class MassRelicEditViewImpl extends AbstractPojoEditView<MassRelic> implements MassRelicEditView
{
	public static final String VIEW_NAME = "MassRelic_EDIT";

	public MassRelicEditViewImpl()
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
		return "Edycja zabytku masowego";
	}
}
