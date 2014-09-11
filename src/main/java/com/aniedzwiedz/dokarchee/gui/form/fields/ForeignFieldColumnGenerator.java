package com.aniedzwiedz.dokarchee.gui.form.fields;

import java.util.List;

import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils.ItemCaptionPart;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;

/*
 * generator uzywany przez CustomTable do wyswietlania wartosci w komorce dla pol slownikowych
 */
public class ForeignFieldColumnGenerator<T> implements ColumnGenerator
{
	private List<ItemCaptionPart> itemCaptionParts;

	public ForeignFieldColumnGenerator(Class<T> classObj)
	{
		itemCaptionParts = EntityLabelUtils.getItemCaptionPartList(classObj);
	}

	@Override
	public Object generateCell(CustomTable source, Object itemId, Object columnId)
	{
		T pojoObject = (T) source.getItem(itemId).getItemProperty(columnId).getValue();
		if (pojoObject == null)
			return "(no data)";
		if (itemCaptionParts == null)
			return pojoObject.toString();
		return EntityLabelUtils.getItemCaption(new BeanItem<>(pojoObject), itemCaptionParts);
	}
}
