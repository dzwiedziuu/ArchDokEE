package com.aniedzwiedz.dokarchee.gui.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.common.utils.ReflectionUtils;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils.ItemCaptionPart;
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
	public interface ForeignFieldListener
	{
		void dictionaryOpened(ForeignFieldEvent foreignFieldEvent);
	}

	public static class ForeignFieldEvent
	{
		private ForeignField<?> foreignField;

		public ForeignFieldEvent(ForeignField<?> foreignField)
		{
			this.foreignField = foreignField;
		}

		public ForeignField<?> getForeignField()
		{
			return foreignField;
		}
	}

	private List<ForeignFieldListener> foreignFieldListeners = new ArrayList<>();

	public void addForeignFieldListener(ForeignFieldListener foreignFieldListener)
	{
		foreignFieldListeners.add(foreignFieldListener);
	}

	private Class<T> classObj;

	private HorizontalLayout horizontalLayout;
	private ComboBox comboBox;
	private Button button;
	private ForeignFieldActionListener foreignFieldActionListener = new ForeignFieldActionListener();

	public ForeignField(Class<T> classObj)
	{
		this.classObj = classObj;
		horizontalLayout = new HorizontalLayout();
		comboBox = new ComboBox();
		comboBox.addValueChangeListener(foreignFieldActionListener);
		button = new Button("D");
		horizontalLayout.addComponent(comboBox);
		horizontalLayout.addComponent(button);
		button.addClickListener(foreignFieldActionListener);
	}

	@Override
	public void addValueChangeListener(com.vaadin.data.Property.ValueChangeListener listener)
	{
		comboBox.addValueChangeListener(listener);
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
			if (ReflectionUtils.equals(t, itemPojoObject))
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

	public void setData(List<T> list, List<ItemCaptionPart> itemCaptionPars)
	{
		for (Object t : list)
		{
			Item item = addItem(t);
			if (itemCaptionPars != null)
				setItemCaption(t, EntityLabelUtils.getItemCaption(item, itemCaptionPars));
		}
	}

	private class ForeignFieldActionListener implements ValueChangeListener, ClickListener
	{

		@Override
		public void buttonClick(ClickEvent event)
		{
			for (ForeignFieldListener foreignFieldListener : foreignFieldListeners)
				foreignFieldListener.dictionaryOpened(new ForeignFieldEvent(ForeignField.this));
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event)
		{
			setValue((T) event.getProperty().getValue());
		}
	}

	@Override
	public void addNewValueToTable(Object value)
	{
		Property property = getPropertyDataSource();
		property.setValue(value);
		setPropertyDataSource(property);
	}
}
