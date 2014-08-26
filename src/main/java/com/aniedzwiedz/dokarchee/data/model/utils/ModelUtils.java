package com.aniedzwiedz.dokarchee.data.model.utils;

import java.lang.reflect.Field;

public class ModelUtils
{
	public static Object getObjectId(Object object, String propertyId)
	{
		Object result = null;
		try
		{
			Field field = object.getClass().getDeclaredField(propertyId);
			field.setAccessible(true);
			result = field.get(object);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
