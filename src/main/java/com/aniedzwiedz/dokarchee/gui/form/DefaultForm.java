package com.aniedzwiedz.dokarchee.gui.form;

import java.util.TreeMap;

import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.service.GeneralService;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;
import com.aniedzwiedz.dokarchee.gui.form.error.ErrorUtils;
import com.aniedzwiedz.dokarchee.gui.form.fields.ActiveComponent;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.action.SaveObject;
import com.aniedzwiedz.dokarchee.logic.action.ShowPrevView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
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

@Component
@Scope("prototype")
public class DefaultForm<T> extends Panel implements CommitHandler, ClickListener, ActionTaker, ActiveComponent
{
	private static final long serialVersionUID = -823980725960033248L;
	private BeanFieldGroup<T> beanFieldGroup;
	private VerticalLayout vertiralLayout;
	private Button saveButton;
	private Button discardButton;

	private HorizontalLayout buttonLayout;

	@Autowired
	private GeneralService generalService;
	private ActionTaker parentActionTaker;

	public DefaultForm()
	{
		setSizeFull();
		vertiralLayout = new VerticalLayout();

		buttonLayout = new HorizontalLayout();
		saveButton = new Button("Zapisz", this);
		discardButton = new Button("Wroc", this);
		buttonLayout.addComponent(saveButton);
		buttonLayout.addComponent(discardButton);
		setContent(vertiralLayout);
	}

	public void setParentActionTaker(ActionTaker parentActionTaker)
	{
		this.parentActionTaker = parentActionTaker;
	}

	public void setObject(T pojoObject)
	{
		Class<T> classObj = (Class<T>) pojoObject.getClass();
		vertiralLayout.removeAllComponents();
		beanFieldGroup = new BeanFieldGroup<>(classObj);
		// BeanItem<T> beanItem = new BeanItem<>(pojoObject);
		beanFieldGroup.setItemDataSource(pojoObject);
		beanFieldGroup.setFieldFactory(new DokarcheeFieldFactory(beanFieldGroup.getFieldFactory(), generalService));
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
			Field<?> f = beanFieldGroup.buildAndBind(af.getEditField().label(), af.getField().getName(), cl);
			if (f instanceof TextField)
			{
				TextField textField = (TextField) f;
				if (textField.getPropertyDataSource().getValue() == null)
					textField.setValue("");
			} else if (f instanceof ActiveComponent)
			{
				((ActiveComponent) f).setParentActionTaker(this);
			}
			f.addValidator(new BeanValidator(classObj, af.getField().getName()));
			vertiralLayout.addComponent(f);
		}
		vertiralLayout.addComponent(buttonLayout);
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
		SaveObject<T> saveObject = new SaveObject<>();
		saveObject.setPojoObject(t);
		takeAction(saveObject);
	}

	@Override
	public void buttonClick(ClickEvent event)
	{
		if (event.getButton() == discardButton)
		{
			beanFieldGroup.discard();
			takeAction(new ShowPrevView());
		} else if (event.getButton() == saveButton)
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

	@Override
	public void takeAction(Action action)
	{
		parentActionTaker.takeAction(action);
	}
}
