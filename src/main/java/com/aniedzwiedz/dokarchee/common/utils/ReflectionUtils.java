package com.aniedzwiedz.dokarchee.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class ReflectionUtils
{
	public static Object getObjectPropertyValue(Object object, String propertyId)
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

	public static <T> boolean equals(T t1, T t2)
	{
		if (t1 == null && t2 == null)
			return true;
		if (t1 == null ^ t2 == null)
			return false;
		try
		{
			for (Field field : getObjectIdFields(t1.getClass()))
			{
				field.setAccessible(true);
				Object id1 = field.get(t1);
				Object id2 = field.get(t2);
				if (t1 == null && t2 == null)
					continue;
				if (t1 == null ^ t2 == null)
					return false;
				if (!id1.equals(id2))
					return false;
			}
			return true;
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static <T> List<Field> getObjectIdFields(Class<T> classObj)
	{
		List<Field> result = new ArrayList<>();
		for (Field field : classObj.getDeclaredFields())
			if (field.isAnnotationPresent(Id.class))
				result.add(field);
		return result;
	}
}
