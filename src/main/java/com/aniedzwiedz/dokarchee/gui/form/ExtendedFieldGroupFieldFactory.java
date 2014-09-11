package com.aniedzwiedz.dokarchee.gui.form;

import com.aniedzwiedz.dokarchee.gui.table.CRUDTable;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;

/*
 * uzywana do tworzenia pol w formularzu.
 * pole slownikowe jest tworzony metoda 
 * createField(Class<?> dataType, ForeignField.class), natomaist dla zbiorow jest dedykowana specjalna metoda createTableField
 */
public interface ExtendedFieldGroupFieldFactory extends FieldGroupFieldFactory
{
	<T> CRUDTable<T> createTableField(Class<T> genericType, boolean manyToMany);
}
