package com.aniedzwiedz.dokarchee.gui.view.objects.functions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.Function;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoListView;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.functions.FunctionListPresenter.FunctionListView;

@Component(FunctionListViewImpl.VIEW_NAME)
@Scope("prototype")
@VaadinView(FunctionListViewImpl.VIEW_NAME)
public class FunctionListViewImpl extends AbstractPojoListView<Function> implements FunctionListView
{
	public static final String VIEW_NAME = "FUNCTION_LIST";

	public FunctionListViewImpl()
	{
		super(Function.class);
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
		return "Lista funkcji";
	}
}
