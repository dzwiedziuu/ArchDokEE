package com.aniedzwiedz.dokarchee.gui.form;

import java.util.List;

import com.aniedzwiedz.dokarchee.data.model.utils.ModelUtils;
import com.aniedzwiedz.dokarchee.data.service.GeneralService;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
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

	private ForeignField createForeignField(Class<?> dataType)
	{
		ForeignField foreignField = new ForeignField();
		List<?> objectList = generalService.getList(dataType);
		for (Object t : objectList)
		{
			String idPropertyName = generalService.getIdentifierPropertyName(dataType);
			Object idValue = ModelUtils.getObjectId(t, idPropertyName);
			Item item = foreignField.addItem(t);
			// foreignField.addItem(t.toString());
			foreignField.setItemCaption(t, t.toString());
		}
		// foreignField.addValueChangeListener(this);
		foreignField.select(objectList.get(0));
		return foreignField;
	}
}
