package com.aniedzwiedz.dokarchee.gui.form.fields;

import com.aniedzwiedz.dokarchee.data.model.utils.ModelUtils;
import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.pojo.ShowListObjectView;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;

public class ForeignField<T> extends CustomField<T> implements ActiveComponent
{
	private Class<T> classObj;

	private HorizontalLayout horizontalLayout;
	private ComboBox comboBox;
	private Button button;
	private ActionTaker parentActionTaker;
	private ForeignFieldListener foreignFieldListener = new ForeignFieldListener();

	public ForeignField(Class<T> classObj)
	{
		this.classObj = classObj;
		horizontalLayout = new HorizontalLayout();
		comboBox = new ComboBox();
		comboBox.addValueChangeListener(foreignFieldListener);
		button = new Button("D");
		horizontalLayout.addComponent(comboBox);
		horizontalLayout.addComponent(button);
		button.addClickListener(foreignFieldListener);
	}

	public Item addItem(Object itemId)
	{
		return comboBox.addItem(itemId);
	}

	public void setContainerDataSource(Container newDataSource)
	{
		comboBox.setContainerDataSource(newDataSource);
	}

	public void setItemCaption(Object itemId, String caption)
	{
		comboBox.setItemCaption(itemId, caption);
	}

	@Override
	public void setPropertyDataSource(Property newDataSource)
	{
		T t = (T) newDataSource.getValue();
		Container container = comboBox.getContainerDataSource();
		for (Object item : container.getItemIds())
		{
			T itemPojoObject = (T) item;
			if (ModelUtils.equals(t, itemPojoObject))
				newDataSource.setValue(itemPojoObject);
		}
		comboBox.setPropertyDataSource(newDataSource);
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Property getPropertyDataSource()
	{
		return comboBox.getPropertyDataSource();
	}

	@Override
	protected Component initContent()
	{
		return horizontalLayout;
	}

	@Override
	public Class<T> getType()
	{
		return classObj;
	}

	@Override
	public void setParentActionTaker(ActionTaker actionTaker)
	{
		this.parentActionTaker = actionTaker;
	}

	private class ForeignFieldListener implements ValueChangeListener, ClickListener
	{

		@Override
		public void buttonClick(ClickEvent event)
		{
			parentActionTaker.takeAction(new ShowListObjectView(classObj));
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event)
		{
			setValue((T) event.getProperty().getValue());
		}
	}
}
