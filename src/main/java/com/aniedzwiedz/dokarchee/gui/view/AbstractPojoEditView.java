package com.aniedzwiedz.dokarchee.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.form.DefaultForm;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoEditView<T> extends AbstractViewImpl
{
	@Autowired
	private DefaultForm<T> defaultForm;

	private VerticalLayout verticalLayout;

	public AbstractPojoEditView()
	{
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
	}

	public void changePojoObj(T pojoObj)
	{
		verticalLayout.removeAllComponents();
		defaultForm.setObject(pojoObj);
		defaultForm.setParentActionTaker(this);
		verticalLayout.addComponent(defaultForm);
	}

	public void setPojoObject(T t)
	{
		changePojoObj(t);
	}
}
