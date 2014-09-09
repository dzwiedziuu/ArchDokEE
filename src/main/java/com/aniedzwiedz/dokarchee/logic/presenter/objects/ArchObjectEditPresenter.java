package com.aniedzwiedz.dokarchee.logic.presenter.objects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.ArchObject;
import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.model.Chronology;
import com.aniedzwiedz.dokarchee.data.model.Culture;
import com.aniedzwiedz.dokarchee.data.model.Function;
import com.aniedzwiedz.dokarchee.data.model.ProfileLayer;
import com.aniedzwiedz.dokarchee.data.model.ProfileShape;
import com.aniedzwiedz.dokarchee.data.model.ProjectionLayer;
import com.aniedzwiedz.dokarchee.data.model.ProjectionShape;
import com.aniedzwiedz.dokarchee.data.model.Soil;
import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.ares.ArListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.chronologies.ChronologyListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.users.UserListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.cultures.CultureListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.functions.FunctionListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.profileLayers.ProfileLayerListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.profileShapes.ProfileShapeListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionLayers.ProjectionLayerListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.projectionShapes.ProjectionShapeListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.objects.soils.SoilListPresenter;

@Component
@Scope("session")
public class ArchObjectEditPresenter extends PojoEditPresenter<ArchObject>
{
	public interface ArchObjectEditView extends PojoEditView<ArchObject>
	{
	}

	@Autowired
	public ArchObjectEditPresenter(ArchObjectEditView pojoEditView, AbstractServiceInterface<ArchObject> pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Override
	protected DataProvider getDataProvider()
	{
		return new ArchObjectEditDataProvider();
	}

	@Autowired
	private AbstractServiceInterface<User> authorService;

	@Autowired
	private AbstractServiceInterface<ProfileShape> profileShapeService;

	@Autowired
	private AbstractServiceInterface<ProjectionShape> projectionShapeService;

	@Autowired
	private AbstractServiceInterface<Function> functionService;

	@Autowired
	private AbstractServiceInterface<Culture> cultureService;

	@Autowired
	private AbstractServiceInterface<Soil> soilService;

	@Autowired
	private AbstractServiceInterface<Chronology> chronologyService;

	private class ArchObjectEditDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> ffType)
		{
			if (User.class.isAssignableFrom(ffType))
				return (List<T>) authorService.getAll(User.class, null);
			else if (ProfileShape.class.isAssignableFrom(ffType))
				return (List<T>) profileShapeService.getAll(ProfileShape.class, null);
			else if (ProjectionShape.class.isAssignableFrom(ffType))
				return (List<T>) projectionShapeService.getAll(ProjectionShape.class, null);
			else if (Function.class.isAssignableFrom(ffType))
				return (List<T>) functionService.getAll(Function.class, null);
			else if (Culture.class.isAssignableFrom(ffType))
				return (List<T>) cultureService.getAll(Culture.class, null);
			else if (Soil.class.isAssignableFrom(ffType))
				return (List<T>) soilService.getAll(Soil.class, null);
			else if (Chronology.class.isAssignableFrom(ffType))
				return (List<T>) chronologyService.getAll(Chronology.class, null);
			return null;
		}
	}

	@Autowired
	private UserListPresenter userListPresenter;

	@Autowired
	private ProfileShapeListPresenter profileShapeListPresenter;

	@Autowired
	private ProjectionShapeListPresenter projectionShapeListPresenter;

	@Autowired
	private FunctionListPresenter functionListPresenter;

	@Autowired
	private CultureListPresenter cultureListPresenter;

	@Autowired
	private SoilListPresenter soilListPresenter;

	@Autowired
	private ChronologyListPresenter chronologyListPresenter;

	@Override
	protected PojoPresenter<?> getDictionaryPresenter(Class<?> ffType)
	{
		if (User.class.isAssignableFrom(ffType))
			return userListPresenter;
		else if (ProfileShape.class.isAssignableFrom(ffType))
			return profileShapeListPresenter;
		else if (ProjectionShape.class.isAssignableFrom(ffType))
			return projectionShapeListPresenter;
		else if (Function.class.isAssignableFrom(ffType))
			return functionListPresenter;
		else if (Culture.class.isAssignableFrom(ffType))
			return cultureListPresenter;
		else if (Soil.class.isAssignableFrom(ffType))
			return soilListPresenter;
		else if (Chronology.class.isAssignableFrom(ffType))
			return chronologyListPresenter;
		return super.getDictionaryPresenter(ffType);
	}

	private ArListPresenter arListPresenter;
	private ProfileLayerListPresenter profileLayerListPresenter;
	private ProjectionLayerListPresenter projectionLayerListPresenter;

	@Autowired
	public void setArListPresenter(ArListPresenter arListPresenter)
	{
		this.arListPresenter = arListPresenter;
		this.arListPresenter.setSelectable(true);
	}

	@Autowired
	public void setProfileLayerListPresenter(ProfileLayerListPresenter profileLayerListPresenter)
	{
		this.profileLayerListPresenter = profileLayerListPresenter;
		this.profileLayerListPresenter.setSelectable(true);
	}

	@Autowired
	public void setProjectionLayerListPresenter(ProjectionLayerListPresenter projectionLayerListPresenter)
	{
		this.projectionLayerListPresenter = projectionLayerListPresenter;
		this.projectionLayerListPresenter.setSelectable(true);
	}

	@Override
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		if (Are.class.isAssignableFrom(ffType))
			return arListPresenter;
		if (ProfileLayer.class.isAssignableFrom(ffType))
			return profileLayerListPresenter;
		if (ProjectionLayer.class.isAssignableFrom(ffType))
			return projectionLayerListPresenter;
		return null;
	}
}
