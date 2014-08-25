package com.aniedzwiedz.dokarchee.gui.form;

import java.util.TreeMap;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;
import com.aniedzwiedz.dokarchee.gui.form.error.ErrorUtils;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.PojoAction;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DefaultForm<T> extends Panel implements CommitHandler, ClickListener
{
	private static final long serialVersionUID = -823980725960033248L;
	private FieldGroup fieldGroup;
	private VerticalLayout vertiralLayout;
	private Button saveButton;
	private Button discardButton;
	
	private Action saveAction;
	private Action discardAction;

	public DefaultForm(T pojoObject)
	{
		setSizeFull();
		vertiralLayout = new VerticalLayout();
		fieldGroup = new FieldGroup();
		BeanItem<T> beanItem = new BeanItem<>(pojoObject);
		fieldGroup.setItemDataSource(beanItem);
		fieldGroup.setFieldFactory(new DokarcheeFieldFactory(fieldGroup.getFieldFactory()));
		setObject(pojoObject);
		fieldGroup.addCommitHandler(this);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		saveButton = new Button("Zapisz", this);
		discardButton = new Button("Wróæ", this);
		buttonLayout.addComponent(saveButton);
		buttonLayout.addComponent(discardButton);
		vertiralLayout.addComponent(buttonLayout);
		setContent(vertiralLayout);
	}
	
	public void setSaveAction(Action action)
	{
		this.saveAction = action;
	}
	
	public void setDiscardAction(Action action)
	{
		this.discardAction = action;
	}

	public void setObject(T pojoObject)
	{
		Class<T> classObj = (Class<T>) pojoObject.getClass();
		TreeMap<Integer, AnnotatedField> map = new TreeMap<>();
		for (java.lang.reflect.Field reflectField : classObj.getDeclaredFields())
			if (reflectField.isAnnotationPresent(EditField.class))
			{
				EditField editField = reflectField.getAnnotation(EditField.class);
				map.put(editField.order(), new AnnotatedField(reflectField, editField));
			}

		for (AnnotatedField af : map.values())
		{
			Field<?> f = fieldGroup.buildAndBind(af.getEditField().label(), af.getField().getName());
			if (f instanceof TextField)
			{
				TextField textField = (TextField) f;
				if (textField.getPropertyDataSource().getValue() == null)
					textField.setValue("");
			}
			f.addValidator(new BeanValidator(classObj, af.getField().getName()));
			vertiralLayout.addComponent(f);
		}
	}

	@Override
	public void preCommit(CommitEvent commitEvent) throws CommitException
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void postCommit(CommitEvent commitEvent) throws CommitException
	{
		FieldGroup fieldGroup = commitEvent.getFieldBinder();
		BeanItem<T> bi = (BeanItem<T>) fieldGroup.getItemDataSource();
		T t = bi.getBean();
		if(saveAction instanceof PojoAction)
			((PojoAction<T>) saveAction).setPojoObject(t);
		saveAction.getPreAction().doPreAction(saveAction);
	}

	@Override
	public void buttonClick(ClickEvent event)
	{
		if (event.getButton() == discardButton)
		{
			fieldGroup.discard();
			discardAction.getPreAction().doPreAction(discardAction);
		}
		else if (event.getButton() == saveButton)
		{
			try
			{
				fieldGroup.commit();
			} catch (CommitException e)
			{
				ErrorUtils.showComponentErrors(fieldGroup.getFields());
			}
		}
	}
}
