package com.aniedzwiedz.dokarchee.gui.view.relics;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.SeparatedRelic;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.SeparatedRelicEditPresenter.SeparatedRelicEditView;

@Component(SeparatedRelicEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(SeparatedRelicEditViewImpl.VIEW_NAME)
public class SeparatedRelicEditViewImpl extends AbstractPojoEditView<SeparatedRelic> implements SeparatedRelicEditView
{
	public static final String VIEW_NAME = "SeparatedRelic_EDIT";

	public SeparatedRelicEditViewImpl()
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
		return "Edycja zabytku wydzielonego";
	}
}
