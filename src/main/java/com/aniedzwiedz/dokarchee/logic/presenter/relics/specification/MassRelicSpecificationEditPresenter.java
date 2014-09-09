package com.aniedzwiedz.dokarchee.logic.presenter.relics.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.data.model.MassRelicSpecification;
import com.aniedzwiedz.dokarchee.data.model.RelicType;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies.ChronologyListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.type.RelicTypeListPresenter;

@Component
@Scope("session")
public class MassRelicSpecificationEditPresenter extends PojoEditPresenter<MassRelicSpecification>
{
	public interface MassRelicSpecificationEditView extends PojoEditView<MassRelicSpecification>
	{
	}

	@Autowired
	public MassRelicSpecificationEditPresenter(MassRelicSpecificationEditView pojoEditView,
			AbstractServiceInterface<MassRelicSpecification> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected DataProvider getDataProvider()
	{
		return new MassRelicSpecificationDataProvider();
	}

	@Autowired
	private AbstractServiceInterface<RelicType> relicTypeService;

	@Autowired
	private AbstractServiceInterface<Chronology> chronologyService;

	private class MassRelicSpecificationDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			if (RelicType.class.isAssignableFrom(classObj))
				return (List<T>) relicTypeService.getAll(RelicType.class, null);
			if (Chronology.class.isAssignableFrom(classObj))
				return (List<T>) chronologyService.getAll(Chronology.class, null);
			return null;
		}
	}

	@Autowired
	private RelicTypeListPresenter relicTypeListPresenter;

	@Autowired
	private ChronologyListPresenter chronologyListPresenter;

	@Override
	protected PojoPresenter<?> getDictionaryPresenter(Class<?> classObj)
	{
		if (RelicType.class.isAssignableFrom(classObj))
			return relicTypeListPresenter;
		if (Chronology.class.isAssignableFrom(classObj))
			return chronologyListPresenter;
		return null;
	}
}
