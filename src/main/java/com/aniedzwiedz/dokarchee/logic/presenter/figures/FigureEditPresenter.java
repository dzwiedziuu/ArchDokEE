package com.aniedzwiedz.dokarchee.logic.presenter.figures;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.Figure;
import com.aniedzwiedz.dokarchee.data.model.FigureSpecification;
import com.aniedzwiedz.dokarchee.data.model.FigureSubject;
import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.data.service.AbstractServiceInterface;
import com.aniedzwiedz.dokarchee.data.service.FigureService;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.PojoPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.specifications.FigureSpecificationEditPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.figures.subjects.FigureSubjectListPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.users.UserListPresenter;

@Component
@Scope("session")
public class FigureEditPresenter extends PojoEditPresenter<Figure>
{
	public interface FigureEditView extends PojoEditView<Figure>
	{
	}

	@Autowired
	public FigureEditPresenter(FigureEditView pojoEditView, FigureService pojoService)
	{
		setView(pojoEditView);
		setPojoService(pojoService);
	}

	@Autowired
	private AbstractServiceInterface<FigureSubject> figureSubjectService;

	@Autowired
	private AbstractServiceInterface<User> userService;

	@Override
	protected DataProvider getDataProvider()
	{
		return new FigureEditDataProvider();
	}

	private class FigureEditDataProvider implements DataProvider
	{
		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			if (User.class.equals(classObj))
				return (List<T>) userService.getAll(User.class, null);
			else if (FigureSubject.class.equals(classObj))
				return (List<T>) figureSubjectService.getAll(FigureSubject.class, null);
			else
				return null;
		}
	}

	private FigureSpecificationEditPresenter figureSpecificationEditPresenter;

	@Autowired
	public void setFigureSpecificationEditPresenter(FigureSpecificationEditPresenter figureSpecificationEditPresenter)
	{
		this.figureSpecificationEditPresenter = figureSpecificationEditPresenter;
		this.figureSpecificationEditPresenter.setPresentsInWindow(true);
		this.figureSpecificationEditPresenter.setSelectable(true);
	}

	@Override
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		if (FigureSpecification.class.equals(ffType))
			return figureSpecificationEditPresenter;
		else
			return super.getActiveFieldPresenter(ffType);
	}

	@Autowired
	private FigureSubjectListPresenter figureSubjectListPresenter;

	@Autowired
	private UserListPresenter userListPresenter;

	@Override
	protected PojoPresenter getDictionaryPresenter(Class<?> ffType)
	{
		if (User.class.equals(ffType))
			return userListPresenter;
		else if (FigureSubject.class.equals(ffType))
			return figureSubjectListPresenter;
		else
			return super.getDictionaryPresenter(ffType);
	}

	@Override
	protected void fillValueInOneToManyRel(Object value)
	{
		if (!(value instanceof FigureSpecification))
			return;
		FigureSpecification fs = (FigureSpecification) value;
		fs.setFigure(getPojoObject());
	}
}
