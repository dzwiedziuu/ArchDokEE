package com.aniedzwiedz.dokarchee.gui.form;

import java.util.TreeMap;

import com.aniedzwiedz.dokarchee.data.model.User;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;
import com.aniedzwiedz.dokarchee.gui.form.error.ErrorUtils;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DefaultForm<T> extends Panel
{
	private static final long serialVersionUID = -823980725960033248L;

	public DefaultForm(T pojoObject)
	{
		Class<T> classObj = (Class<T>) pojoObject.getClass();
		setSizeFull();
		VerticalLayout vl = new VerticalLayout();
		final FieldGroup fieldGroup = new FieldGroup();
		BeanItem<T> beanItem = new BeanItem<>(pojoObject);
		fieldGroup.setItemDataSource(beanItem);
		fieldGroup.setFieldFactory(new DokarcheeFieldFactory(fieldGroup.getFieldFactory()));

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
			vl.addComponent(f);
		}

		fieldGroup.addCommitHandler(new CommitHandler()
		{
			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException
			{
				FieldGroup fieldGroup = commitEvent.getFieldBinder();
				BeanItem<User> bi = (BeanItem<User>) fieldGroup.getItemDataSource();
				User user = bi.getBean();
				System.out.println(user.getLastName());
			}
		});

		vl.addComponent(new Button("ADD", new Button.ClickListener()
		{
			@Override
			public void buttonClick(ClickEvent event)
			{
				try
				{
					fieldGroup.commit();
				} catch (CommitException e)
				{
					ErrorUtils.showComponentErrors(fieldGroup.getFields());
				}
			}
		}));
		setContent(vl);
	}
}
