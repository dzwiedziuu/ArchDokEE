package com.aniedzwiedz.dokarchee.gui.form;

import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.common.utils.ReflectionUtils;
import com.aniedzwiedz.dokarchee.gui.form.fields.ForeignField;
import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;

@Component
public class EditFieldFactory implements ExtendedFieldGroupFieldFactory
{
	private static final long serialVersionUID = -2990070763186820309L;

	private FieldGroupFieldFactory defaultFieldGroupFieldFactory = new DefaultFieldGroupFieldFactory();

	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType)
	{
		if (ForeignField.class.isAssignableFrom(fieldType))
			return fieldType.cast(createForeignField(dataType));
		if (CRUDTable.class.isAssignableFrom(fieldType))
			return fieldType.cast(createCRUDTableField(dataType));
		return defaultFieldGroupFieldFactory.createField(dataType, fieldType);
	}

	private Object createCRUDTableField(Class<?> dataType)
	{
		return null;
	}

	private <T> ForeignField<T> createForeignField(Class<T> dataType)
	{
		ForeignField<T> foreignField = new ForeignField<>(dataType);
		return foreignField;
	}

	@Override
	public <T> CRUDTable<T> createTableField(Class<T> genericType, boolean manyToMany)
	{
		CRUDTable<T> crudTable = new CRUDTable<>(genericType);
		crudTable.setAddActionMenuItem(crudTable.addContextMenuItem("Dodaj"));
		crudTable.setAddActionButton(crudTable.addUpperButton(new Button("Dodaj")));
		if (!manyToMany && ReflectionUtils.isEditable(genericType))
		{
			crudTable.setEditActionMenuItem(crudTable.addContextMenuItem("Edytuj"));
			crudTable.setEditActionButton(crudTable.addUpperButton(new Button("Edytuj")));
		}
		crudTable.setRemoveActionMenuItem(crudTable.addContextMenuItem("Usun"));
		crudTable.setRemoveActionButton(crudTable.addUpperButton(new Button("Usun")));
		return crudTable;
	}
}
