package com.aniedzwiedz.dokarchee.gui.form.fields;

/*
 * interfejs implementowany przez ForeignField i CRUDTable, pobierajacy wynik dzialania podrzednego okna, czyli pobiera wybrana wartosc (jezeli potomnym oknem byla lista) lub dodaje/edytuje wartosc jezeli podrzednym oknem bylo okno edycji (np. pozycji specyfikacji)
 */
public interface ActiveComponent
{
	void addNewValueToTable(Object value);
}
