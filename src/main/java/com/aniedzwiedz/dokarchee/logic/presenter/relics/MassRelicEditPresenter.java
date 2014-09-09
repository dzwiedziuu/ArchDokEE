package com.aniedzwiedz.dokarchee.logic.presenter.relics;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.data.model.MassRelic;
import com.aniedzwiedz.dokarchee.data.model.MassRelicSpecification;
import com.aniedzwiedz.dokarchee.data.model.RelicLayer;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.ares.ArListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.ArchObjectListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.layer.RelicLayerListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.relics.specification.MassRelicSpecificationEditPresenter;
import com.vaadin.server.VaadinSession;

@Component
@Scope("session")
public class MassRelicEditPresenter extends PojoEditPresenter<MassRelic>
{
	public interface MassRelicEditView extends PojoEditView<MassRelic>
	{
	}

	@Autowired
	public MassRelicEditPresenter(MassRelicEditView pojoEditView, AbstractServiceInterface<MassRelic> pojoService)
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
	private AbstractServiceInterface<ArchObject> archObjectService;
	@Autowired
	private AbstractServiceInterface<Are> areService;
	@Autowired
	private AbstractServiceInterface<RelicLayer> relicLayerService;

	private class RelicTypeEditDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			if (ArchObject.class.isAssignableFrom(classObj))
			{
				BusinessContext bc = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
				return (List<T>) archObjectService.getAll(ArchObject.class, Restrictions.eq("businessContext", bc));
			}
			if (Are.class.isAssignableFrom(classObj))
				return (List<T>) areService.getAll(Are.class, null);
			if (RelicLayer.class.isAssignableFrom(classObj))
				return (List<T>) relicLayerService.getAll(RelicLayer.class, null);
			return null;
		}
	}

	@Autowired
	private ArchObjectListPresenter archObjectListPresenter;
	@Autowired
	private ArListPresenter arListPresenter;
	@Autowired
	private RelicLayerListPresenter relicLayerListPresenter;

	@Override
	protected PojoPresenter<?> getDictionaryPresenter(Class<?> classObj)
	{
		if (RelicLayer.class.isAssignableFrom(classObj))
			return relicLayerListPresenter;
		if (ArchObject.class.isAssignableFrom(classObj))
			return archObjectListPresenter;
		if (Are.class.isAssignableFrom(classObj))
			return arListPresenter;
		return null;
	}

	private MassRelicSpecificationEditPresenter massRelicSpecificationEditPresenter;

	@Autowired
	public void setMassRelicSpecificationEditPresenter(MassRelicSpecificationEditPresenter massRelicSpecificationEditPresenter)
	{
		this.massRelicSpecificationEditPresenter = massRelicSpecificationEditPresenter;
		this.massRelicSpecificationEditPresenter.setSelectable(true);
	}

	@Override
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		if (MassRelicSpecification.class.isAssignableFrom(ffType))
			return massRelicSpecificationEditPresenter;
		return super.getActiveFieldPresenter(ffType);
	}

	@Override
	protected void fillValueInOneToManyRel(Object value)
	{
		if (!(value instanceof MassRelicSpecification))
			return;
		MassRelicSpecification mrs = (MassRelicSpecification) value;
		mrs.setMassRelic(getPojoObject());
	}
}
