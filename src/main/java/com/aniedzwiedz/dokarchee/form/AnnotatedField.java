package com.aniedzwiedz.dokarchee.form;

import java.lang.reflect.Field;

import com.aniedzwiedz.dokarchee.annotations.EditField;

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
