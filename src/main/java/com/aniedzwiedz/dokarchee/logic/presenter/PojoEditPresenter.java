package com.aniedzwiedz.dokarchee.logic.presenter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.aniedzwiedz.dokarchee.common.utils.ReflectionUtils;
import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormEvent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ActiveComponent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField.ForeignFieldEvent;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.aniedzwiedz.dokarchee.gui.view.AbstractEditView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractEditView.EditViewListener;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;

public abstract class PojoEditPresenter<T> extends PojoPresenter<T> implements EditViewListener<T>
{
	public interface PojoEditView<T> extends AbstractEditView<T>
	{
		void setPojoObject(T t);
	}

	private T pojoObject;
	private PojoEditView<T> pojoEditView;
	private Map<Class<? extends AbstractView>, ActiveComponent> childPresentersFromFields = new HashMap<>();
	private boolean newObject;

	public void setNewObject(boolean newObject)
	{
		this.newObject = newObject;
	}

	public void returnValue(AbstractPresenter abstractPresenter, Object value)
	{
		if (value == null)
			return;
		ActiveComponent activeComponent = childPresentersFromFields.remove(abstractPresenter.getAbstractView().getClass());
		activeComponent.addNewValueToTable(value);
	}

	@Override
	public AbstractView getAbstractView()
	{
		return pojoEditView;
	}

	@Override
	public void setView(AbstractView namedView)
	{
		setPojoEditView((PojoEditView<T>) namedView);
	}

	public void setPojoObject(T pojoObject)
	{
		this.pojoObject = pojoObject;
	}

	protected PojoEditView<T> getPojoEditView()
	{
		return pojoEditView;
	}

	protected void setPojoEditView(PojoEditView<T> namedView)
	{
		this.pojoEditView = namedView;
		pojoEditView.addEditViewListener(this);
		pojoEditView.addViewListener(this);
	}

	protected abstract AbstractPresenter getDictionaryPresenter(Class<?> ffType);

	protected abstract AbstractPresenter getActiveFieldPresenter(Class<?> ffType);

	@Override
	public void dictionaryOpened(ForeignFieldEvent foreignFieldEvent)
	{
		Class<?> ffType = foreignFieldEvent.getForeignField().getType();
		AbstractPresenter abstractPresenter = getDictionaryPresenter(ffType);
		if (abstractPresenter == null)
			throw new RuntimeException("Couldn't find dictionary presenter for class " + ffType);
		childPresentersFromFields.put(abstractPresenter.getAbstractView().getClass(), foreignFieldEvent.getForeignField());
		goToNextView(abstractPresenter);
	}

	@Override
	public void addItem(TableEvent crudTableEvent)
	{
		openEditWindowForEvent(crudTableEvent, true);
	}

	@Override
	public void editItem(TableEvent crudTableEvent)
	{
		openEditWindowForEvent(crudTableEvent, false);
	}

	private void openEditWindowForEvent(TableEvent crudTableEvent, boolean newObject)
	{
		Class<?> crudClass = crudTableEvent.getCrudTable().getContentType();
		AbstractPresenter abstractPresenter = getActiveFieldPresenter(crudClass);
		if (abstractPresenter == null)
			throw new RuntimeException("Couldn't find activeField presenter for class " + crudClass);
		if (abstractPresenter instanceof PojoEditPresenter)
		{
			((PojoEditPresenter) abstractPresenter).setPojoObject(crudTableEvent.getPojoObject());
			((PojoEditPresenter) abstractPresenter).setNewObject(newObject);
		}
		childPresentersFromFields.put(abstractPresenter.getAbstractView().getClass(), crudTableEvent.getCrudTable());
		goToNextView(abstractPresenter);
	}

	@Override
	public void removeItem(TableEvent crudTableEvent)
	{
		crudTableEvent.getCrudTable().removeItem(crudTableEvent.getPojoObject());
	}

	@Override
	public void doubleClickedItem(TableEvent crudTableEvent)
	{
		selectedItem(crudTableEvent);
	}

	@Override
	public void selectedItem(TableEvent event)
	{
		((PojoEditPresenter) getParentPresenter()).returnValue(this, event.getPojoObject());
		pojoEditView.closeLastWindow();
	}

	@Override
	public void saveButtonClicked(FormEvent<T> formEvent)
	{
		PojoService<T> pojoService = getPojoService();
		T pojoObject = formEvent.getPojoObject();
		if (newObject)
			pojoService.add(pojoObject);
		else
			pojoService.update(pojoObject);
		pojoEditView.closeLastWindow();
	}

	@Override
	public void discardButtonClicked(FormEvent<T> formEvent)
	{
		pojoEditView.closeLastWindow();
	}

	@Override
	public void initializeView(AbstractView abstractView)
	{
		pojoEditView.setPojoObject(pojoObject);
	}

	private boolean checkIfObjectIsNew(T pojoObject)
	{
		try
		{
			for (Field field : ReflectionUtils.getObjectIdFields(pojoObject.getClass()))
			{
				field.setAccessible(true);
				if (field.get(pojoObject) == null)
					return true;
			}
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// TODO to spaghetti
	@Override
	public void focusAfterClosedWindow(ViewEvent viewEvent)
	{
		if (viewEvent.getClosedWindowView() == pojoEditView)
			getParentPresenter().focusAfterClosedWindow(viewEvent);
		else
			childPresentersFromFields.remove(viewEvent.getClosedWindowView().getClass());
	}
}
