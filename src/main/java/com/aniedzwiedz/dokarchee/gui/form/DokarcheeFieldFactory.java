package com.aniedzwiedz.dokarchee.gui.form;

import java.util.List;

import com.aniedzwiedz.dokarchee.data.model.utils.ModelEntityLabelUtils;
import com.aniedzwiedz.dokarchee.data.model.utils.ModelEntityLabelUtils.ItemCaptionPart;
import com.aniedzwiedz.dokarchee.data.service.GeneralService;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Field;

public class DokarcheeFieldFactory implements FieldGroupFieldFactory
{
	private static final long serialVersionUID = -2990070763186820309L;

	private FieldGroupFieldFactory defaultFieldGroupFieldFactory;
	private GeneralService generalService;

	public DokarcheeFieldFactory(FieldGroupFieldFactory defaultFieldGroupFieldFactory, GeneralService generalService)
	{
		this.defaultFieldGroupFieldFactory = defaultFieldGroupFieldFactory;
		this.generalService = generalService;
	}

	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType)
	{
		if (ForeignField.class.isAssignableFrom(fieldType))
			return fieldType.cast(createForeignField(dataType));
		return defaultFieldGroupFieldFactory.createField(dataType, fieldType);
	}

	private <T> ForeignField createForeignField(Class<T> dataType)
	{
		ForeignField foreignField = new ForeignField();
		foreignField.setContainerDataSource(new BeanItemContainer<T>(dataType));
		List<ItemCaptionPart> itemCaptionPars = ModelEntityLabelUtils.getItemCaptionPartList(dataType);
		List<?> objectList = generalService.getList(dataType);
		for (Object t : objectList)
		{
			Item item = foreignField.addItem(t);
			if (itemCaptionPars != null)
				foreignField.setItemCaption(t, ModelEntityLabelUtils.getItemCaption(item, itemCaptionPars));
		}
		return foreignField;
	}
}
