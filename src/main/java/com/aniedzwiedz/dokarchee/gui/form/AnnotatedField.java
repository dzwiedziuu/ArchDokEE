package com.aniedzwiedz.dokarchee.gui.form;

import java.lang.reflect.Field;

import com.aniedzwiedz.dokarchee.common.annotations.EditField;

/*
 * klasa pomocnicza przechowujaca informacje o polu
 */
public class AnnotatedField
{
	private Field field;
	private EditField editField;

	public AnnotatedField(Field field, EditField editField)
	{
		this.field = field;
		this.editField = editField;
	}

	public Field getField()
	{
		return field;
	}

	public EditField getEditField()
	{
		return editField;
	}

}
