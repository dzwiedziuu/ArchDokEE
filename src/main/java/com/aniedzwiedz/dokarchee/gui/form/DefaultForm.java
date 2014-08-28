package com.aniedzwiedz.dokarchee.gui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.ManyToOne;

import com.aniedzwiedz.dokarchee.gui.ComponentWithAction;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;
import com.aniedzwiedz.dokarchee.gui.form.error.ErrorUtils;
import com.aniedzwiedz.dokarchee.gui.form.fields.ActiveComponent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.pojo.PojoAction;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DefaultForm<T> extends Panel implements CommitHandler, ActionTaker, ActiveComponent
{
	private static final long serialVersionUID = -823980725960033248L;
	private BeanFieldGroup<T> beanFieldGroup;
	private FieldGroupFieldFactory fieldGroupFieldFactory;
	private VerticalLayout vertiralLayout;
	private HorizontalLayout buttonPanel;

	private ActionTaker parentActionTaker;
	private ActionListener actionListener;
	private FormActionListener formActionListener;

	private List<ComponentWithAction<Button>> buttons;
	private ComponentWithAction<Button> saveActionButton;
	private ComponentWithAction<Button> discardActionButton;

	public DefaultForm()
	{
		actionListener = new ActionListener();
		formActionListener = new FormActionListener();
		vertiralLayout = new VerticalLayout();
		buttonPanel = new HorizontalLayout();
		buttons = new ArrayList<>();
		setContent(vertiralLayout);
		setSizeFull();
	}

	public void addSaveActionButton(Button button, Action action)
	{
		this.saveActionButton = new ComponentWithAction<>(button, action);
		button.addClickListener(formActionListener);
		buttonPanel.addComponent(button);
	}

	public void addDiscardActionButton(Button button, Action action)
	{
		this.discardActionButton = new ComponentWithAction<>(button, action);
		button.addClickListener(formActionListener);
		buttonPanel.addComponent(button);
	}

	public void addButton(Button button, Action action)
	{
		ComponentWithAction<Button> componentWithAction = new ComponentWithAction<>(button, action);
		button.addClickListener(actionListener);
		buttons.add(componentWithAction);
		buttonPanel.addComponent(button);
	}

	public void setParentActionTaker(ActionTaker parentActionTaker)
	{
		this.parentActionTaker = parentActionTaker;
	}

	@Override
	public void takeAction(Action action)
	{
		if (action instanceof PojoAction)
		{
			T pojoObject = beanFieldGroup.getItemDataSource().getBean();
			((PojoAction<T>) action).setPojoObject(pojoObject);
		}
		parentActionTaker.takeAction(action);
	}

	public void setFieldGroupFieldFactory(FieldGroupFieldFactory fieldGroupFieldFactory)
	{
		this.fieldGroupFieldFactory = fieldGroupFieldFactory;
	}

	public void setObject(T pojoObject)
	{
		vertiralLayout.removeAllComponents();
		Class<T> classObj = (Class<T>) pojoObject.getClass();
		beanFieldGroup = new BeanFieldGroup<>(classObj);
		// BeanItem<T> beanItem = new BeanItem<>(pojoObject);
		beanFieldGroup.setItemDataSource(pojoObject);
		beanFieldGroup.setFieldFactory(fieldGroupFieldFactory);
		// setObject(pojoObject);
		beanFieldGroup.addCommitHandler(this);
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
			if (af.getField().isAnnotationPresent(ManyToOne.class))
				cl = ForeignField.class;
			// else if (af.getField().isAnnotationPresent(OneToMany.class))
			// cl = CRUDTable.class;
			// else if (af.getField().isAnnotationPresent(ManyToMany.class))
			// cl = CRUDTable.class;
			Field<?> f = beanFieldGroup.buildAndBind(af.getEditField().label(), af.getField().getName(), cl);
			if (f instanceof TextField)
			{
				TextField textField = (TextField) f;
				if (textField.getPropertyDataSource().getValue() == null)
					textField.setValue("");
			} else if (f instanceof ActiveComponent)
				((ActiveComponent) f).setParentActionTaker(this);
			f.addValidator(new BeanValidator(classObj, af.getField().getName()));
			vertiralLayout.addComponent(f);
		}
		if (buttons.size() > 0 || saveActionButton != null || discardActionButton != null)
			vertiralLayout.addComponent(buttonPanel);
	}

	@Override
	public void preCommit(CommitEvent commitEvent) throws CommitException
	{
	}

	@Override
	public void postCommit(CommitEvent commitEvent) throws CommitException
	{
		takeAction(saveActionButton.getAction());
	}

	public class FormActionListener implements Button.ClickListener
	{
		@Override
		public void buttonClick(ClickEvent event)
		{
			if (event.getButton() == discardActionButton.getComponent())
			{
				beanFieldGroup.discard();
				takeAction(discardActionButton.getAction());
			} else if (event.getButton() == saveActionButton.getComponent())
			{
				try
				{
					beanFieldGroup.commit();
				} catch (CommitException e)
				{
					// TODO
					ErrorUtils.showComponentErrors(beanFieldGroup.getFields());
				}
			}
		}
	}

	public class ActionListener implements Button.ClickListener
	{
		@Override
		public void buttonClick(ClickEvent event)
		{
			for (ComponentWithAction<Button> componentWithAction : buttons)
				if (event.getButton() == componentWithAction.getComponent())
					takeAction(componentWithAction.getAction());
		}
	}
}
