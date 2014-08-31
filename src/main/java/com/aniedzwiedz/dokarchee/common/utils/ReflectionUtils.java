package com.aniedzwiedz.dokarchee.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aniedzwiedz.dokarchee.common.annotations.EditField;

public class ReflectionUtils
{
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

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
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * type must have at least one field to edit other than ID
	 */
	public static boolean isEditable(Class<?> genericType)
	{
		for (Field field : genericType.getDeclaredFields())
			if (field.isAnnotationPresent(EditField.class) && !field.isAnnotationPresent(Id.class))
				return true;
		return false;
	}

	/*
	 * if both ids are null, returns false works only with one ID field in type
	 * T
	 */
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
				if (id1 == null && id2 == null)
					return false;
				if (id1 == null ^ id2 == null)
					return false;
				if (!id1.equals(id2))
					return false;
			}
			return true;
		} catch (IllegalArgumentException | IllegalAccessException e)
		{
			logger.error("", e);
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
