package com.aniedzwiedz.dokarchee.logic.presenter.objects.cultures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Culture;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;

@Component
@Scope("session")
public class CultureEditPresenter extends PojoEditPresenter<Culture>
{
	public interface CultureEditView extends PojoEditView<Culture>
	{
	}

	@Autowired
	public CultureEditPresenter(CultureEditView pojoEditView, AbstractServiceInterface<Culture> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}
}
