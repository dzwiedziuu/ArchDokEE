package com.aniedzwiedz.dokarchee.gui.form.fields;

import com.aniedzwiedz.dokarchee.gui.view.ActionTaker;
import com.aniedzwiedz.dokarchee.logic.action.ShowEditView;
import com.aniedzwiedz.dokarchee.logic.action.ShowPrevView;
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

	public ForeignField(Class<T> classObj)
	{
		this.classObj = classObj;
		horizontalLayout = new HorizontalLayout();
		comboBox = new ComboBox();
		button = new Button("D");
		horizontalLayout.addComponent(comboBox);
		horizontalLayout.addComponent(button);
		button.addClickListener(new MuButtonClickListener());
	}

	private class MuButtonClickListener implements ClickListener
	{
		private Button button2;
		private Button button;

		@Override
		public void buttonClick(ClickEvent event)
		{
			if (event.getButton() == ForeignField.this.button || event.getButton() == button)
			{
				// Window window = new Window();
				// VerticalLayout verticalLayout = new VerticalLayout();
				// verticalLayout.addComponent(new Label("tst"));
				// button = new Button("button");
				// button.addClickListener(this);
				// button2 = new Button("close");
				// button2.addClickListener(this);
				// horizontalLayout.addComponent(button2);
				// verticalLayout.addComponent(button);
				// window.setContent(verticalLayout);
				// window.center();
				// window.setModal(true);
				// UI.getCurrent().addWindow(window);
				parentActionTaker.takeAction(new ShowEditView<>());
			} else
			{
				try
				{
					T t = classObj.newInstance();
					parentActionTaker.takeAction(new ShowPrevView());
				} catch (InstantiationException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}
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
		super.setPropertyDataSource(newDataSource);
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
}
