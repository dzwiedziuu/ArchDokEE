package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;

@Entity
@Table(name = "business_context")
public class BusinessContext
{
	@Id
	@GeneratedValue
	@Column(name = "idbusiness_context")
	private Long id;

	@Column(name = "name")
	@ColumnHeader(value = "Nazwa opracowania", order = 1)
	@EditField(label = "Nazwa opracowania", order = 1)
	@NotEmpty(message = "Nazwa opracowania nie moze byc pusta")
	private String name;

	@Column(name = "describtion")
	@ColumnHeader(value = "Opis", order = 2)
	@EditField(label = "Opis", order = 2)
	private String describtion;

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

	public String getDescribtion()
	{
		return describtion;
	}

	public void setDescribtion(String describtion)
	{
		this.describtion = describtion;
	}
}
