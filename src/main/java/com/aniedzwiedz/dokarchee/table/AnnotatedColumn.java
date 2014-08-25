package com.aniedzwiedz.dokarchee.table;

import java.lang.reflect.Field;

import com.aniedzwiedz.dokarchee.annotations.ColumnHeader;

public class AnnotatedColumn
{
	private Field field;
	private ColumnHeader columnHeader;

	public AnnotatedColumn(Field field, ColumnHeader columnHeader)
	{
		super();
		this.field = field;
		this.columnHeader = columnHeader;
	}

	public Field getField()
	{
		return field;
	}

	public ColumnHeader getColumnHeader()
	{
		return columnHeader;
	}
}
