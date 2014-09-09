package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "functions")
@ForeignFieldLabel(pattern = "name")
public class Function
{
	@Id
	@GeneratedValue
	@Column(name = "function_id")
	private Long id;

	@Column(name = "function_name")
	@ColumnHeader(value = "Nazwa funkcji", order = 1)
	@EditField(label = "Nazwa funkcji", order = 1)
	@NotNull(message = "Nazwa funkcji nie moze byc pusta")
	private String name;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
