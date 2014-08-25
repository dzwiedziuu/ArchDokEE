package com.aniedzwiedz.dokarchee.gui.form;

import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.ui.Field;

public class DokarcheeFieldFactory implements FieldGroupFieldFactory
{
	private static final long serialVersionUID = -2990070763186820309L;

	private FieldGroupFieldFactory defaultFieldGroupFieldFactory;

	public DokarcheeFieldFactory(FieldGroupFieldFactory defaultFieldGroupFieldFactory)
	{
		this.defaultFieldGroupFieldFactory = defaultFieldGroupFieldFactory;
	}

	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType)
	{
		return defaultFieldGroupFieldFactory.createField(dataType, fieldType);
	}

}
