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
@Table(name = "relic_layers")
@ForeignFieldLabel(pattern = "name")
public class RelicLayer
{
	@Id
	@GeneratedValue
	@Column(name = "relic_layer_id")
	private Long id;

	@Column(name = "relic_layer_name")
	@ColumnHeader(value = "Nazwa warstwy", order = 1)
	@EditField(label = "Nazwa warstwy", order = 1)
	@NotNull(message = "Nazwa warstwy nie moze byc pusta")
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
