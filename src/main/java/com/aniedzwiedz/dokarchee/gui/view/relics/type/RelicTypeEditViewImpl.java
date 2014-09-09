package com.aniedzwiedz.dokarchee.gui.view.relics.type;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.RelicType;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.type.RelicTypeEditPresenter.RelicTypeEditView;

@Component(RelicTypeEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(RelicTypeEditViewImpl.VIEW_NAME)
public class RelicTypeEditViewImpl extends AbstractPojoEditView<RelicType> implements RelicTypeEditView
{
	public static final String VIEW_NAME = "RelicType_EDIT";

	public RelicTypeEditViewImpl()
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
		return "Edycja typu zabytku";
	}
}
