package com.aniedzwiedz.dokarchee.gui.table;

import com.vaadin.data.Property;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;

public class BooleanColumnGenerator implements ColumnGenerator
{
	@Override
	public Object generateCell(CustomTable source, Object itemId, Object columnId)
	{
		Property<Boolean> prop = source.getItem(itemId).getItemProperty(columnId);
		if (prop.getValue() == true)
			return "Tak";
		if (prop.getValue() == false)
			return "Nie";
		return "CONVERTION ERROR";
	}
}
