package com.aniedzwiedz.dokarchee.logic.presenter.objects.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Function;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class FunctionEditPresenter extends PojoEditPresenter<Function>
{
	public interface FunctionEditView extends PojoEditView<Function>
	{
	}

	@Autowired
	public FunctionEditPresenter(FunctionEditView pojoEditView, AbstractServiceInterface<Function> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
