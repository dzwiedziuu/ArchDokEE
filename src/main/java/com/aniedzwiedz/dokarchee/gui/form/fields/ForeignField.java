package com.aniedzwiedz.dokarchee.gui.form.fields;

import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;

public class ForeignField extends ComboBox
{
	@Override
	public void setPropertyDataSource(Property newDataSource)
	{
		super.setPropertyDataSource(newDataSource);
		select(newDataSource.getValue());
	}
}
