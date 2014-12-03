package com.aniedzwiedz.dokarchee.gui.table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aniedzwiedz.dokarchee.common.utils.ReflectionUtils;
import com.vaadin.data.Property;

/*
 * klasa uzyta do przetrzymywania danych z tabeli
 */
public class TablePropertyHolder<T>
{
	private Iterable<T> initialSet = new HashSet<>();

	private Property<? extends Set<T>> currentProperty = null;

	public Property setProperty(Property property)
	{
		Iterable<T> set = (Iterable<T>) property.getValue();
		if (initialSet == null)
			initialSet = set;
		Set<T> currentSet = mergePropertyDataSource(initialSet, set);
		property.setValue(currentSet);
		currentProperty = property;
		return currentProperty;
	}

	public Property getProperty()
	{
		return currentProperty;
	}

	public Property removeItem(T item)
	{
		Set<T> c = (Set<T>) currentProperty.getValue();
		List<T> toRemove = new ArrayList<>();
		for (T object : c)
			if (ReflectionUtils.equals(item, object))
				toRemove.add(object);
		for (T o : toRemove)
			c.remove(o);
		return currentProperty;
	}

	/*
	 * dodaje item jesli byl unikalny, zwraca rezultat, czy udalo sie dodac nowy
	 * item
	 */
	public boolean addUnique(T item)
	{
		Set<T> c = (Set<T>) currentProperty.getValue();
		for (Object object : c)
			if (ReflectionUtils.equals(item, object))
				return false;
		c.add(item);
		return true;
	}

	/*
	 * ³¹czy zbiory w argumentach, bior¹c wszystkie elementy z pierwszego zbioru
	 * oraz te, ktorych nie bylo w pierwszym z drugiego i zwraca wynik
	 */
	private Set<T> mergePropertyDataSource(Iterable<T> initialSet, Iterable<T> currentSet)
	{
		Set<T> resultSet = new HashSet<>();
		outer: for (T currentObj : currentSet)
		{
			for (T initialObj : initialSet)
				if (ReflectionUtils.equals(initialObj, currentObj))
				{
					resultSet.add(initialObj);
					continue outer;
				}
			resultSet.add(currentObj);
		}
		return resultSet;
	}
}
