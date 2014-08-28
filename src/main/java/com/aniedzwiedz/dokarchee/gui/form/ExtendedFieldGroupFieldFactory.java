package com.aniedzwiedz.dokarchee.gui.form;

import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;

public interface ExtendedFieldGroupFieldFactory extends FieldGroupFieldFactory
{
	<T> CRUDTable<T> createTableField(Class<T> genericType, boolean manyToMany);
}
