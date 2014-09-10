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
@Table(name = "soils")
@ForeignFieldLabel(pattern = "name")
public class Soil
{
	@Id
	@GeneratedValue
	@Column(name = "soil_id")
	private Long id;

	@Column(name = "soil_name")
	@ColumnHeader(value = "Nazwa calca", order = 1)
	@EditField(label = "Nazwa calca", order = 1)
	@NotNull(message = "Nazwa calca nie moze byc pusta")
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