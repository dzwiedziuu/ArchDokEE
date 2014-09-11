package com.aniedzwiedz.dokarchee.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;

/*
 * klasa wspomagajca dzialania w obrebie wyswietlania obiektów POJO w slownikach i tabelce
 */
public class EntityLabelUtils
{
	/*
	 * klasa zawierajaca informacje o tokenie skladniowym wzorca podanego w
	 * adnotacji ForeignFieldLabel
	 */
	public static class ItemCaptionPart
	{
		private String value;
		/*
		 * czy podany token odwoluje sie do wlasciwosci obiektu czy jest to
		 * staly tekst
		 */
		private boolean property;

		public ItemCaptionPart(String value, boolean property)
		{
			this.value = value;
			this.property = property;
		}

		public String getValue()
		{
			return value;
		}

		public boolean isProperty()
		{
			return property;
		}

	}

	/*
	 * zwraca tekst odpowiadajacy obiektowi na podstawie adnotacji
	 * ForeignFieldLabel
	 */
	public static <T> String getObjectLabel(T object)
	{
		List<ItemCaptionPart> itemCaptionParts = getItemCaptionPartList(object.getClass());
		return getItemCaption(new BeanItem<T>(object), itemCaptionParts);
	}

	/*
	 * zwraca liste tokenów odpowiadajacych obiektowi na podstawie adnotacji
	 * ForeignFieldLabel
	 */
	public static List<ItemCaptionPart> getItemCaptionPartList(Class<?> classObj)
	{
		if (!classObj.isAnnotationPresent(ForeignFieldLabel.class))
			return null;
		String pattern = classObj.getAnnotation(ForeignFieldLabel.class).pattern();
		return parsePattern(pattern, classObj);
	}

	/*
	 * zwraca liste tokenów odpowiadajacych obiektowi na podstawie adnotacji
	 * ForeignFieldLabel
	 */
	private static List<ItemCaptionPart> parsePattern(String pattern, Class<?> classObj)
	{
		List<ItemCaptionPart> result = new ArrayList<>();
		String[] parts = pattern.split("\\$");
		if (parts.length == 1)
			return Arrays.asList(new ItemCaptionPart(parts[0], true));
		boolean isproperty = false;
		if (parts.length % 2 != 1 ^ pattern.endsWith("$"))
			throw new RuntimeException("Parse pattern contains unclosed property for class " + classObj);
		for (int i = 0; i < parts.length; i++)
		{
			if (!parts[i].isEmpty())
				result.add(new ItemCaptionPart(parts[i], isproperty));
			else if (isproperty == true)
				throw new RuntimeException("Empty property name for class " + classObj);
			isproperty ^= true;
		}
		return result;
	}

	/*
	 * zwraca napis odpowiadajacy obiektowi na podstawie adnotacji obiektu (i
	 * jego wartosci) oraz listy tokenow stworzonych w metodzie parsePattern()
	 */
	public static String getItemCaption(Item item, List<ItemCaptionPart> captionParts)
	{
		StringBuffer sb = new StringBuffer();
		for (ItemCaptionPart itemCaptionPart : captionParts)
		{
			if (itemCaptionPart.isProperty())
				sb.append(item.getItemProperty(itemCaptionPart.getValue()).getValue());
			else
				sb.append(itemCaptionPart.getValue());
		}
		return sb.toString();
	}

}
