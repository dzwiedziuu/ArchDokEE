package com.aniedzwiedz.dokarchee.gui.form.fields;

import java.util.List;

import com.aniedzwiedz.dokarchee.data.model.utils.ModelEntityLabelUtils;
import com.aniedzwiedz.dokarchee.data.model.utils.ModelEntityLabelUtils.ItemCaptionPart;
import com.vaadin.data.Property;
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
		Property<T> prop = source.getItem(itemId).getItemProperty(columnId);
		if (itemCaptionParts == null)
			return prop.getValue().toString();
		return ModelEntityLabelUtils.getItemCaption(new BeanItem<>(prop.getValue()), itemCaptionParts);
	}
}
