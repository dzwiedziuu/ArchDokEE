package com.aniedzwiedz.dokarchee.gui.view.objects.functions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Function;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.functions.FunctionEditPresenter.FunctionEditView;

@Component(FunctionEditViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FunctionEditViewImpl.VIEW_NAME)
public class FunctionEditViewImpl extends AbstractPojoEditView<Function> implements FunctionEditView
{
	public static final String VIEW_NAME = "FUNCTION_EDIT";

	public FunctionEditViewImpl()
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
		return "Edycja funkcji";
	}
}
