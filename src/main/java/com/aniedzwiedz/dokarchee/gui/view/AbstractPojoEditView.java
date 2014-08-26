package com.aniedzwiedz.dokarchee.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.form.DefaultForm;
import com.aniedzwiedz.dokarchee.logic.action.SaveObject;
import com.aniedzwiedz.dokarchee.logic.action.ShowPrevView;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoEditView<T> extends AbstractView
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
		defaultForm.setSaveAction(new SaveObject<>(this));
		defaultForm.setDiscardAction(new ShowPrevView(this));
		verticalLayout.addComponent(defaultForm);
	}

	public void setPojoObject(T t)
	{
		changePojoObj(t);
	}
}
