package com.aniedzwiedz.dokarchee.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.aniedzwiedz.dokarchee.gui.form.DefaultForm;
import com.aniedzwiedz.dokarchee.gui.form.EditFieldFactory;
import com.aniedzwiedz.dokarchee.logic.action.ShowPrevView;
import com.aniedzwiedz.dokarchee.logic.action.pojo.SaveObject;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractPojoEditView<T> extends AbstractViewImpl
{
	private EditFieldFactory fieldGroupFieldFactory;

	private VerticalLayout verticalLayout;
	private DefaultForm<Object> defaultForm;

	public AbstractPojoEditView()
	{
		verticalLayout = new VerticalLayout();
		defaultForm = new DefaultForm<>();
		defaultForm.setParentActionTaker(this);
		defaultForm.addSaveActionButton(new Button("Zapisz"), new SaveObject<T>());
		defaultForm.addDiscardActionButton(new Button("Wroc"), new ShowPrevView());
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
}
