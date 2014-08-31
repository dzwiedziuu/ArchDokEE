package com.aniedzwiedz.dokarchee.gui.form;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils.ItemCaptionPart;
import com.aniedzwiedz.dokarchee.gui.form.error.ErrorUtils;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField.ForeignFieldEvent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField.ForeignFieldListener;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.CRUDTableListener;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable.TableEvent;
import com.aniedzwiedz.dokarchee.gui.view.PojoEvent;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DefaultForm<T> extends Panel
{
	public interface FormListener<T>
	{
		void saveButtonClicked(FormEvent<T> formEvent);

		void discardButtonClicked(FormEvent<T> formEvent);
	}

	public static class FormEvent<T1> extends PojoEvent<T1>
	{
	}

	public interface FormFieldListener extends ForeignFieldListener, CRUDTableListener
	{
	}

	private BeanFieldGroup<T> beanFieldGroup;
	private ExtendedFieldGroupFieldFactory fieldGroupFieldFactory;
	private VerticalLayout vertiralLayout;
	private HorizontalLayout buttonPanel;

	private FormActionListener formActionListener;
	private ActiveFieldListener fieldListener;

	private Button saveActionButton;
	private Button discardActionButton;

	private List<FormListener<T>> formListeners = new ArrayList<>();
	private List<FormFieldListener> formFieldListeners = new ArrayList<>();
	private T pojoObject;

	public DefaultForm()
	{
		formActionListener = new FormActionListener();
		fieldListener = new ActiveFieldListener();
		vertiralLayout = new VerticalLayout();
		buttonPanel = new HorizontalLayout();
		setContent(vertiralLayout);
		setSizeFull();
	}

	public void addFormListener(FormListener<T> formListener)
	{
		formListeners.add(formListener);
	}

	public void addFormFieldListener(FormFieldListener formFieldListener)
	{
		formFieldListeners.add(formFieldListener);
	}

	public void addSaveActionButton(Button button)
	{
		this.saveActionButton = button;
		button.addClickListener(formActionListener);
		buttonPanel.addComponent(button);
	}

	public void addDiscardActionButton(Button button)
	{
		this.discardActionButton = button;
		button.addClickListener(formActionListener);
		buttonPanel.addComponent(button);
	}

	public void addButton(Button button)
	{
		buttonPanel.addComponent(button);
	}

	public void setFieldGroupFieldFactory(ExtendedFieldGroupFieldFactory fieldGroupFieldFactory)
	{
		this.fieldGroupFieldFactory = fieldGroupFieldFactory;
	}

	/*
	 * reinitialize defaultForm if pojoObject in param is diffrent than stored
	 * in field 'pojoObject'
	 */
	public void trySetPojoObjectOrRefresh(T pojoObject)
	{
		if (this.pojoObject == pojoObject)
		{
			refreshForm();
			return;
		}
		vertiralLayout.removeAllComponents();
		foreignFields.clear();
		Class<T> classObj = (Class<T>) pojoObject.getClass();
		beanFieldGroup = new BeanFieldGroup<>(classObj);
		// BeanItem<T> beanItem = new BeanItem<>(pojoObject);
		beanFieldGroup.setItemDataSource(pojoObject);
		beanFieldGroup.setFieldFactory(fieldGroupFieldFactory);
		// setObject(pojoObject);
		beanFieldGroup.addCommitHandler(formActionListener);
		TreeMap<Integer, AnnotatedField> map = new TreeMap<>();
		for (java.lang.reflect.Field reflectField : classObj.getDeclaredFields())
			if (reflectField.isAnnotationPresent(EditField.class))
			{
				EditField editField = reflectField.getAnnotation(EditField.class);
				map.put(editField.order(), new AnnotatedField(reflectField, editField));
			}

		for (AnnotatedField af : map.values())
		{
			Class<? extends Field> cl = Field.class;
			Field<?> field = null;
			boolean manyToMany = af.getField().isAnnotationPresent(ManyToMany.class);
			boolean manyToOne = af.getField().isAnnotationPresent(OneToMany.class);
			if (manyToOne || manyToMany)
			{
				Class<?> genericType = (Class<?>) ((ParameterizedType) af.getField().getGenericType()).getActualTypeArguments()[0];
				field = fieldGroupFieldFactory.createTableField(genericType, manyToMany);
				field.setCaption(af.getEditField().label());
				beanFieldGroup.bind(field, af.getField().getName());
				// cl = CRUDTable.class;
			} else
			{
				if (af.getField().isAnnotationPresent(ManyToOne.class))
					cl = ForeignField.class;
				// BeanValidator added in buildAndBind method
				field = beanFieldGroup.buildAndBind(af.getEditField().label(), af.getField().getName(), cl);
			}
			if (field instanceof TextField)
			{
				((TextField) field).setNullRepresentation("");
				// for (Validator validator : field.getValidators())
				// System.out.println(validator);
				((TextField) field).setConversionError("Wprowadzana wartosc musi byc liczba");
			}
			if (field instanceof CRUDTable)
				((CRUDTable<?>) field).addCRUDTableListener(fieldListener);
			if (field instanceof ForeignField)
			{
				ForeignField foreignField = (ForeignField<?>) field;
				foreignField.addForeignFieldListener(fieldListener);
				fillForeignField(foreignField, field.getType());
				foreignFields.add(foreignField);
			}
			if (af.getField().isAnnotationPresent(Id.class))
			{
				try
				{
					af.getField().setAccessible(true);
					if (af.getField().get(pojoObject) != null)
						field.setEnabled(false);
				} catch (IllegalArgumentException | IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			vertiralLayout.addComponent(field);
		}
		vertiralLayout.addComponent(buttonPanel);
		this.pojoObject = pojoObject;
	}

	private <T1> void fillForeignField(ForeignField<T1> foreignField, Class<T1> dataType)
	{
		foreignField.setContainerDataSource(new BeanItemContainer<T1>(dataType));
		List<ItemCaptionPart> itemCaptionPars = EntityLabelUtils.getItemCaptionPartList(dataType);
		List<T1> data = new ArrayList<>();
		if (getDataProvider() != null)
			data = getDataProvider().getList(dataType);
		foreignField.setData(data, itemCaptionPars);
	}

	public interface DataProvider
	{
		<T> List<T> getList(Class<T> classObj);
	}

	private DataProvider dataProvider;

	private DataProvider getDataProvider()
	{
		return dataProvider;
	}

	public void setDataProvider(DataProvider dataProvider)
	{
		this.dataProvider = dataProvider;
	}

	private class FormActionListener implements Button.ClickListener, CommitHandler
	{
		@Override
		public void buttonClick(ClickEvent event)
		{
			if (event.getButton() == discardActionButton)
			{
				beanFieldGroup.discard();
				FormEvent<T> formEvent = new FormEvent<>();
				formEvent.setPojoObject(beanFieldGroup.getItemDataSource().getBean());
				for (FormListener<T> formListener : formListeners)
					formListener.discardButtonClicked(formEvent);
			} else if (event.getButton() == saveActionButton)
			{
				try
				{
					beanFieldGroup.commit();
				} catch (CommitException e)
				{
					// TODO
					ErrorUtils.showComponentErrors(beanFieldGroup.getFields());
				} catch (Error e) // thrown by DokArchExceptionResolver
				{
					Notification.show("ERROR", e.getMessage(), Type.ERROR_MESSAGE);
				}
			}
		}

		@Override
		public void preCommit(CommitEvent commitEvent) throws CommitException
		{
		}

		@Override
		public void postCommit(CommitEvent commitEvent) throws CommitException
		{
			FormEvent<T> formEvent = new FormEvent<>();
			formEvent.setPojoObject(beanFieldGroup.getItemDataSource().getBean());
			for (FormListener<T> formListener : formListeners)
				formListener.saveButtonClicked(formEvent);
		}
	}

	private class ActiveFieldListener implements ForeignFieldListener, CRUDTableListener
	{
		@Override
		public void dictionaryOpened(ForeignFieldEvent foreignFieldEvent)
		{
			for (FormFieldListener formFieldListener : formFieldListeners)
				formFieldListener.dictionaryOpened(foreignFieldEvent);
		}

		@Override
		public void addItem(TableEvent crudTableEvent)
		{
			for (FormFieldListener formFieldListener : formFieldListeners)
				formFieldListener.addItem(crudTableEvent);
		}

		@Override
		public void editItem(TableEvent crudTableEvent)
		{
			for (FormFieldListener formFieldListener : formFieldListeners)
				formFieldListener.editItem(crudTableEvent);
		}

		@Override
		public void removeItem(TableEvent crudTableEvent)
		{
			for (FormFieldListener formFieldListener : formFieldListeners)
				formFieldListener.removeItem(crudTableEvent);
		}

		@Override
		public void doubleClickedItem(TableEvent crudTableEvent)
		{
			for (FormFieldListener formFieldListener : formFieldListeners)
				formFieldListener.doubleClickedItem(crudTableEvent);
		}

		@Override
		public void selectedItem(TableEvent event)
		{
			for (FormFieldListener formFieldListener : formFieldListeners)
				formFieldListener.selectedItem(event);
		}
	}

	private List<ForeignField<?>> foreignFields = new ArrayList<>();

	public void refreshForm()
	{
		for (ForeignField foreignField : foreignFields)
		{
			Property p = foreignField.getPropertyDataSource();
			Object value = p.getValue();
			fillForeignField(foreignField, foreignField.getType());
			p.setValue(value);
			foreignField.setPropertyDataSource(p);
		}
	}
}
