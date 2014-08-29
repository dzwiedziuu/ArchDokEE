package com.aniedzwiedz.dokarchee.gui.table;

public class PropertyWithClass
{
	private String propertyName;
	private Class<?> propertyType;

	public PropertyWithClass(String propertyName, Class<?> propertyType)
	{
		this.propertyName = propertyName;
		this.propertyType = propertyType;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public Class<?> getPropertyType()
	{
		return propertyType;
	}
}
