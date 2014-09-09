package com.aniedzwiedz.dokarchee.logic.presenter.relics.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.RelicGroup;
import com.aniedzwiedz.dokarchee.data.model.RelicType;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.group.RelicGroupListPresenter;

@Component
@Scope("session")
public class RelicTypeEditPresenter extends PojoEditPresenter<RelicType>
{
	public interface RelicTypeEditView extends PojoEditView<RelicType>
	{
	}

	@Autowired
	public RelicTypeEditPresenter(RelicTypeEditView pojoEditView, AbstractServiceInterface<RelicType> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected DataProvider getDataProvider()
	{
		return new RelicTypeEditDataProvider();
	}

	@Autowired
	private AbstractServiceInterface<RelicGroup> relicGroupService;

	private class RelicTypeEditDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			if (RelicGroup.class.isAssignableFrom(classObj))
				return (List<T>) relicGroupService.getAll(RelicGroup.class, null);
			return null;
		}
	}

	@Autowired
	private RelicGroupListPresenter relicGroupListPresenter;

	@Override
	protected PojoPresenter<?> getDictionaryPresenter(Class<?> ffType)
	{
		if (RelicGroup.class.isAssignableFrom(ffType))
			return relicGroupListPresenter;
		return super.getDictionaryPresenter(ffType);
	}
}
