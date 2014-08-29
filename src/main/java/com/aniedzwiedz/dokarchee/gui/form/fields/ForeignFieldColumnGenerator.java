package com.aniedzwiedz.dokarchee.gui.form.fields;

import java.util.List;

import com.aniedzwiedz.dokarchee.common.utils.ModelEntityLabelUtils;
import com.aniedzwiedz.dokarchee.common.utils.ModelEntityLabelUtils.ItemCaptionPart;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;

public class ForeignFieldColumnGenerator<T> implements ColumnGenerator
{
	private List<ItemCaptionPart> itemCaptionParts;

	public ForeignFieldColumnGenerator(Class<T> classObj)
	{
		itemCaptionParts = ModelEntityLabelUtils.getItemCaptionPartList(classObj);
	}

	@Override
	public Object generateCell(CustomTable source, Object itemId, Object columnId)
	{
		T pojoObject = (T) source.getItem(itemId).getItemProperty(columnId).getValue();
		if (pojoObject == null)
			return "(no data)";
		if (itemCaptionParts == null)
			return pojoObject.toString();
		return ModelEntityLabelUtils.getItemCaption(new BeanItem<>(pojoObject), itemCaptionParts);
	}
}
