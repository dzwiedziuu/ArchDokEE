package com.aniedzwiedz.dokarchee.gui.table;

import java.util.Locale;

import org.tepi.filtertable.FilterDecorator;
import org.tepi.filtertable.numberfilter.NumberFilterPopupConfig;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.datefield.Resolution;

@SuppressWarnings("serial")
public class FilterDecoratorImpl implements FilterDecorator
{

	@Override
	public String getEnumFilterDisplayName(Object propertyId, Object value)
	{
		return value.toString();
	}

	@Override
	public Resource getEnumFilterIcon(Object propertyId, Object value)
	{
		return null;
	}

	@Override
	public String getBooleanFilterDisplayName(Object propertyId, boolean value)
	{
		if (value == true)
			return "Tak";
		return "Nie";
	}

	@Override
	public Resource getBooleanFilterIcon(Object propertyId, boolean value)
	{
		return null;
	}

	@Override
	public boolean isTextFilterImmediate(Object propertyId)
	{
		return false;
	}

	@Override
	public int getTextChangeTimeout(Object propertyId)
	{
		return 0;
	}

	@Override
	public String getFromCaption()
	{
		return "Od";
	}

	@Override
	public String getToCaption()
	{
		return "Do";
	}

	@Override
	public String getSetCaption()
	{
		return "Ustaw";
	}

	@Override
	public String getClearCaption()
	{
		return "Wyczysc";
	}

	@Override
	public Resolution getDateFieldResolution(Object propertyId)
	{
		return Resolution.DAY;
	}

	@Override
	public String getDateFormatPattern(Object propertyId)
	{
		return null;
	}

	@Override
	public Locale getLocale()
	{
		return null;
	}

	@Override
	public String getAllItemsVisibleString()
	{
		return null;
	}

	@Override
	public NumberFilterPopupConfig getNumberFilterPopupConfig()
	{
		return null;
	}

	@Override
	public boolean usePopupForNumericProperty(Object propertyId)
	{
		return false;
	}

}
