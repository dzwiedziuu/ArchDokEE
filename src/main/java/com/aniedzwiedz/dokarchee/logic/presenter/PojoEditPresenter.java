package com.aniedzwiedz.dokarchee.logic.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aniedzwiedz.dokarchee.data.service.PojoService;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.DataProvider;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormEvent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ActiveComponent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField.ForeignFieldEvent;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.aniedzwiedz.dokarchee.gui.view.AbstractEditView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractEditView.EditViewListener;
import com.aniedzwiedz.dokarchee.gui.view.AbstractPojoEditView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;

/*
 * klasa bazowa dla wszystkich prezenterów okna edycji
 */
public abstract class PojoEditPresenter<T> extends PojoPresenter<T> implements EditViewListener<T>, SelectListener
{
	public interface PojoEditView<T> extends AbstractEditView<T>
	{
		void setPojoObject(T t);
	}

	private T pojoObject;
	private PojoEditView<T> pojoEditView;
	private Map<Class<? extends AbstractView>, ActiveComponent> childPresentersFromFields = new HashMap<>();
	private boolean newObject;

	/*
	 * czy edytowany obiekt jest nowym obiektem czy edytowanym
	 */
	public void setNewObject(boolean newObject)
	{
		this.newObject = newObject;
	}

	/*
	 * przekazuje wartosc otrzymana z potomnego prezentera i przekazuje ja do
	 * pola ktory spowodowalo wyswietlenie nowego okna (non-Javadoc)
	 * 
	 * @see
	 * com.aniedzwiedz.dokarchee.logic.presenter.SelectListener#returnValue(
	 * com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter,
	 * java.lang.Object)
	 */
	public void returnValue(AbstractPresenter abstractPresenter, Object value)
	{
		if (value == null)
			return;
		ActiveComponent activeComponent = childPresentersFromFields.remove(abstractPresenter.getAbstractView().getClass());
		fillValueInOneToManyRel(value);
		activeComponent.addNewValueToTable(value);
	}

	/*
	 * metoda pozwala wzbogacic obiekt zwracany przez potomne okno edycji,
	 * konieczna do implementacji, jezeli w oknie edycji istnieje pole w relacji
	 * jeden-do-wielu, np. dla specyfikacji
	 */
	protected void fillValueInOneToManyRel(Object value)
	{
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

	protected T getPojoObject()
	{
		return pojoObject;
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
		if (pojoEditView instanceof AbstractPojoEditView)
			((AbstractPojoEditView) pojoEditView).setDataProvider(getDataProvider());
	}

	@Override
	public void dictionaryOpened(ForeignFieldEvent foreignFieldEvent)
	{
		Class<?> ffType = foreignFieldEvent.getForeignField().getType();
		PojoPresenter<?> pojoPresenter = getDictionaryPresenter(ffType);
		if (pojoPresenter == null)
			throw new RuntimeException("Couldn't find dictionary presenter for class " + ffType);
		pojoPresenter.setSelectable(true);
		childPresentersFromFields.put(pojoPresenter.getAbstractView().getClass(), foreignFieldEvent.getForeignField());
		goToNextView(pojoPresenter);
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
		if (isSelectable())
		{
			((SelectListener) getParentPresenter()).returnValue(this, formEvent.getPojoObject());
			pojoEditView.closeLastWindow();
			return;
		}
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
	public void refreshView(AbstractView abstractView)
	{
		pojoEditView.setPojoObject(pojoObject);
	}

	// TODO to spaghetti
	/*
	 * jezeli zamykane jest okno powiazane z tym prezenterem - wywolaj ta metode
	 * na prezenterze-rodzicu, jezeli nie to wykonaj wlasciwa akcje, czyli usun
	 * z pamieci to okno (non-Javadoc)
	 * 
	 * @see com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewListener#
	 * focusAfterClosedWindow
	 * (com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent)
	 */
	@Override
	public void focusAfterClosedWindow(ViewEvent viewEvent)
	{
		if (viewEvent.getClosedWindowView() == pojoEditView)
			getParentPresenter().focusAfterClosedWindow(viewEvent);
		else
			childPresentersFromFields.remove(viewEvent.getClosedWindowView().getClass());
	}

	/*
	 * method used to get abstractPresenters which presents dirctionary of type
	 * in argument
	 */
	protected PojoPresenter<?> getDictionaryPresenter(Class<?> ffType)
	{
		throw new RuntimeException("Abstract Presenter for type " + ffType + " dictionary not provided. Implement this method in subclass.");
	}

	/*
	 * method used to get abstractPresenter which presents editWindow after
	 * new/edit button is clicked (it may be listView (many to many) or
	 * editView)
	 */
	protected AbstractPresenter getActiveFieldPresenter(Class<?> ffType)
	{
		throw new RuntimeException("Abstract Presenter for type " + ffType + " not provided. Implement this method in subclass.");
	}

	protected DataProvider getDataProvider()
	{
		return dummyDataProvider;
	}

	private static DataProvider dummyDataProvider = new DataProvider()
	{
		@Override
		public <T> List<T> getList(Class<T> classObj)
		{
			throw new RuntimeException("Not implemented method DataProvider.getList() - used to fill dictionaries - class: " + getClass());
		}
	};
}
