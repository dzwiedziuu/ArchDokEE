package com.aniedzwiedz.dokarchee.gui.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.form.DefaultForm;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormEvent;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormFieldListener;
import com.aniedzwiedz.dokarchee.gui.form.DefaultForm.FormListener;
import com.aniedzwiedz.dokarchee.gui.form.EditFieldFactory;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField.ForeignFieldEvent;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoEditView<T> extends AbstractViewImpl implements AbstractEditView<T>
{
	private List<EditViewListener<T>> editViewListeners = new ArrayList<>();

	public void addEditViewListener(EditViewListener<T> editViewListener)
	{
		editViewListeners.add(editViewListener);
	}

	private EditFieldFactory fieldGroupFieldFactory;

	private VerticalLayout verticalLayout;
	private DefaultForm<T> defaultForm;

	private FormWithFieldListener formWithFieldListener = new FormWithFieldListener();

	public AbstractPojoEditView()
	{
		verticalLayout = new VerticalLayout();
		defaultForm = new DefaultForm<>();
		defaultForm.addFormListener(formWithFieldListener);
		defaultForm.addSaveActionButton(new Button("Zapisz"));
		defaultForm.addDiscardActionButton(new Button("Wroc"));
		verticalLayout.addComponent(defaultForm);
		setContent(verticalLayout);
	}

	@Autowired
	public void setFieldGroupFieldFactory(EditFieldFactory fieldGroupFieldFactory)
	{
		this.fieldGroupFieldFactory = fieldGroupFieldFactory;
		defaultForm.setFieldGroupFieldFactory(fieldGroupFieldFactory);
	}

	public EditFieldFactory getFieldGroupFieldFactory()
	{
		return fieldGroupFieldFactory;
	}

	public void setPojoObject(T pojoObj)
	{
		defaultForm.setObject(pojoObj);
	}

	private class FormWithFieldListener implements FormFieldListener, FormListener<T>
	{

		@Override
		public void dictionaryOpened(ForeignFieldEvent foreignFieldEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.dictionaryOpened(foreignFieldEvent);
		}

		@Override
		public void addItem(TableEvent crudTableEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.addItem(crudTableEvent);
		}

		@Override
		public void editItem(TableEvent crudTableEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.editItem(crudTableEvent);
		}

		@Override
		public void removeItem(TableEvent crudTableEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.removeItem(crudTableEvent);
		}

		@Override
		public void doubleClickedItem(TableEvent crudTableEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.doubleClickedItem(crudTableEvent);
		}

		@Override
		public void selectedItem(TableEvent event)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.selectedItem(event);
		}

		@Override
		public void saveButtonClicked(FormEvent<T> formEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.saveButtonClicked(formEvent);
		}

		@Override
		public void discardButtonClicked(FormEvent<T> formEvent)
		{
			for (EditViewListener<T> editViewListener : editViewListeners)
				editViewListener.discardButtonClicked(formEvent);
		}
	}
}
