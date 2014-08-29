package com.aniedzwiedz.dokarchee.gui.table;

import java.util.Iterator;
import java.util.List;

import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils.ItemCaptionPart;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;

public class TableFieldColumnGenerator<T> implements ColumnGenerator
{
	private List<ItemCaptionPart> itemCaptionParts;
	private Class<?> classObj;

	public TableFieldColumnGenerator(Class<T> classObj)
	{
		this.classObj = classObj;
		itemCaptionParts = EntityLabelUtils.getItemCaptionPartList(classObj);
	}

	@Override
	public Object generateCell(CustomTable source, Object itemId, Object columnId)
	{
		Property<? extends Iterable<T>> prop = source.getItem(itemId).getItemProperty(columnId);
		if (itemCaptionParts == null)
			return prop.getValue().toString();
		Iterable<T> set = prop.getValue();
		Iterator<T> iter = set.iterator();
		if (iter.hasNext())
		{
			Class<?> actualClass = iter.next().getClass();
			if (!actualClass.equals(classObj))
				throw new RuntimeException("Declared type in annotation ColumnHeader (" + classObj + ") is different than actual ("
						+ actualClass + ")");
		} else
			return "(no data)";
		StringBuffer sb = new StringBuffer();
		for (T t : set)
			sb.append(EntityLabelUtils.getItemCaption(new BeanItem<>(t), itemCaptionParts)).append(", ");
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}
}
