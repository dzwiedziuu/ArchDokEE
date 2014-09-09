package com.aniedzwiedz.dokarchee.logic.presenter.relics;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.data.model.RelicGroup;
import com.aniedzwiedz.dokarchee.data.model.RelicLayer;
import com.aniedzwiedz.dokarchee.data.model.SeparatedRelic;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.ares.ArListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies.ChronologyListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.ArchObjectListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.group.RelicGroupListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.layer.RelicLayerListPresenter;
import com.vaadin.server.VaadinSession;

@Component
@Scope("session")
public class SeparatedRelicEditPresenter extends PojoEditPresenter<SeparatedRelic>
{
	public interface SeparatedRelicEditView extends PojoEditView<SeparatedRelic>
	{
	}

	@Autowired
	public SeparatedRelicEditPresenter(SeparatedRelicEditView pojoEditView, AbstractServiceInterface<SeparatedRelic> pojoService)
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
	private AbstractServiceInterface<RelicLayer> relicLayerService;

	@Autowired
	private AbstractServiceInterface<ArchObject> archObjectService;

	@Autowired
	private AbstractServiceInterface<RelicGroup> relicGroupService;

	@Autowired
	private AbstractServiceInterface<Are> areService;

	@Autowired
	private AbstractServiceInterface<Chronology> chronologyService;

	private class RelicTypeEditDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			if (RelicLayer.class.isAssignableFrom(classObj))
				return (List<T>) relicLayerService.getAll(RelicLayer.class, null);
			if (ArchObject.class.isAssignableFrom(classObj))
			{
				BusinessContext bc = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
				return (List<T>) archObjectService.getAll(ArchObject.class, Restrictions.eq("businessContext", bc));
			}
			if (RelicGroup.class.isAssignableFrom(classObj))
				return (List<T>) relicGroupService.getAll(RelicGroup.class, null);
			if (Are.class.isAssignableFrom(classObj))
				return (List<T>) areService.getAll(Are.class, null);
			if (Chronology.class.isAssignableFrom(classObj))
				return (List<T>) chronologyService.getAll(Chronology.class, null);
			return null;
		}
	}

	@Autowired
	private RelicGroupListPresenter relicGroupListPresenter;

	@Autowired
	private RelicLayerListPresenter relicLayerListPresenter;

	@Autowired
	private ArchObjectListPresenter archObjectListPresenter;

	@Autowired
	private ChronologyListPresenter chronologyListPresenter;

	@Autowired
	private ArListPresenter arListPresenter;

	@Override
	protected PojoPresenter<?> getDictionaryPresenter(Class<?> classObj)
	{
		if (RelicLayer.class.isAssignableFrom(classObj))
			return relicLayerListPresenter;
		if (ArchObject.class.isAssignableFrom(classObj))
			return archObjectListPresenter;
		if (RelicGroup.class.isAssignableFrom(classObj))
			return relicGroupListPresenter;
		if (Are.class.isAssignableFrom(classObj))
			return arListPresenter;
		if (Chronology.class.isAssignableFrom(classObj))
			return chronologyListPresenter;
		return null;
	}
}
