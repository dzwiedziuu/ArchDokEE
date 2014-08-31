package com.aniedzwiedz.dokarchee.gui.table;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tepi.filtertable.FilterGenerator;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils.ItemCaptionPart;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class FilterGeneratorImpl implements FilterGenerator
{
	private static final Logger logger = LoggerFactory.getLogger(FilterGeneratorImpl.class);

	private Class classObj;

	public FilterGeneratorImpl(Class classObj)
	{
		this.classObj = classObj;
	}

	@Override
	public Filter generateFilter(final Object propertyId, Object value)
	{
		if (!isForeignFieldOrSet(propertyId))
			return null;
		// TODO not sure if necessary
		try
		{
			java.lang.reflect.Field field = classObj.getDeclaredField((String) propertyId);

			final String userValue = (String) value;

			Class<?> contentType = field.getType();
			if (Set.class.isAssignableFrom(field.getType()))
				contentType = field.getAnnotation(ColumnHeader.class).genericType();
			final List<ItemCaptionPart> parts = EntityLabelUtils.getItemCaptionPartList(contentType);
			return new Filter()
			{
				@Override
				public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException
				{
					Object propValue = item.getItemProperty(propertyId).getValue();
					if (propValue == null && userValue != null && !userValue.isEmpty())
						return false;
					if (parts == null)
						return false;
					String toStringValue = EntityLabelUtils.getItemCaption(new BeanItem<>(propValue), parts);
					if (toStringValue.contains(userValue))
						return true;
					return false;
				}

				@Override
				public boolean appliesToProperty(Object propertyId)
				{
					return true;
				}
			};
		} catch (NoSuchFieldException | SecurityException e)
		{
			logger.error("", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Filter generateFilter(Object propertyId, Field<?> originatingField)
	{
		return null;
	}

	@Override
	public AbstractField<?> getCustomFilterComponent(Object propertyId)
	{
		if (isForeignFieldOrSet(propertyId))
		{
			TextField result = new TextField();
			java.lang.reflect.Field field = getField(propertyId);
			if (field.isAnnotationPresent(ManyToMany.class) || field.isAnnotationPresent(OneToMany.class))
				result.setEnabled(false);
			return result;
		}
		return null;
	}

	private java.lang.reflect.Field getField(Object propertyId)
	{
		try
		{
			return classObj.getDeclaredField((String) propertyId);
		} catch (NoSuchFieldException | SecurityException e)
		{
			logger.error("", e);
			e.printStackTrace();
			return null;
		}
	}

	private boolean isForeignFieldOrSet(Object propertyId)
	{
		java.lang.reflect.Field field = getField(propertyId);
		if (!field.isAnnotationPresent(ColumnHeader.class))
			return false;
		if (field.isAnnotationPresent(ManyToMany.class))
			return true;
		if (field.isAnnotationPresent(ManyToOne.class))
			return true;
		if (field.isAnnotationPresent(OneToMany.class))
			return true;
		return false;
	}

	@Override
	public void filterRemoved(Object propertyId)
	{
	}

	@Override
	public void filterAdded(Object propertyId, Class<? extends Filter> filterType, Object value)
	{
	}

	@Override
	public Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value)
	{
		return null;
	}

}
