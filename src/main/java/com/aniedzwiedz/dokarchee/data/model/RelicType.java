package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "relic_types")
@ForeignFieldLabel(pattern = "name")
public class RelicType
{
	@Id
	@GeneratedValue
	@Column(name = "relic_type_id")
	private Long id;

	@Column(name = "relic_type_name")
	@ColumnHeader(value = "Nazwa typu zabytku", order = 1)
	@EditField(label = "Nazwa typu zabytku", order = 1)
	@NotNull(message = "Nazwa typu zabytku nie moze byc pusta")
	private String name;

	@JoinColumn(name = "relic_group_id")
	@ColumnHeader(value = "Grupa zabytków", order = 2)
	@EditField(label = "Grupa zabytków", order = 2)
	@NotNull(message = "Grupa zabytków nie moze byc pusta")
	@ManyToOne(optional = false)
	private RelicGroup relicGroup;

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

	public RelicGroup getRelicGroup()
	{
		return relicGroup;
	}

	public void setRelicGroup(RelicGroup relicGroup)
	{
		this.relicGroup = relicGroup;
	}
}
