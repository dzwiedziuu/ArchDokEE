package com.aniedzwiedz.dokarchee.gui.view.misc.chronologies;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies.ChronologyEditPresenter.ChronologyEditView;

@Component(ChronologyEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(ChronologyEditViewImpl.VIEW_NAME)
public class ChronologyEditViewImpl extends AbstractPojoEditView<Chronology> implements ChronologyEditView
{
	public static final String VIEW_NAME = "Chronology_EDIT";

	public ChronologyEditViewImpl()
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
		return "Edycja chronologii";
	}
}
