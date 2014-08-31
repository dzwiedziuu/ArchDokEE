package com.aniedzwiedz.dokarchee.logic.presenter.figures.specifications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.FigureSpecification;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.ArchObjectListPresenter;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class FigureSpecificationEditPresenter extends PojoEditPresenter<FigureSpecification>
{
	public interface FigureSpecificationEditView extends PojoEditView<FigureSpecification>
	{
	}

	@Autowired
	public FigureSpecificationEditPresenter(FigureSpecificationEditView pojoEditView,
			AbstractServiceInterface<FigureSpecification> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected DataProvider getDataProvider()
	{
		return new FigureSpecificationEditDataProvider();
	}

	@Autowired
	private AbstractServiceInterface<ArchObject> archObjService;

	private class FigureSpecificationEditDataProvider implements DataProvider
	{

		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			if (ArchObject.class.equals(classObj))
				return (List<T>) archObjService.getAll((Class<ArchObject>) classObj,
						SessionUtils.getBusinessContextCriterion("businessContext"));
			else
				return null;
		}
	}

	@Autowired
	private ArchObjectListPresenter archObjectListPresenter;

	@Override
	protected PojoPresenter getDictionaryPresenter(Class<?> ffType)
	{
		if (ArchObject.class.equals(ffType))
			return archObjectListPresenter;
		return super.getDictionaryPresenter(ffType);
	}
}
