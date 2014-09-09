package com.aniedzwiedz.dokarchee.gui.view.relics.specification;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.MassRelicSpecification;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.specification.MassRelicSpecificationEditPresenter.MassRelicSpecificationEditView;

@Component(MassRelicSpecificationEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(MassRelicSpecificationEditViewImpl.VIEW_NAME)
public class MassRelicSpecificationEditViewImpl extends AbstractPojoEditView<MassRelicSpecification> implements
		MassRelicSpecificationEditView
{
	public static final String VIEW_NAME = "MassRelicSpecification_EDIT";

	public MassRelicSpecificationEditViewImpl()
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
		return "Edycja specyfikacji zabytku masowego";
	}
}
