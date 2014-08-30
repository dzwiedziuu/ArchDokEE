package com.aniedzwiedz.dokarchee.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "object")
@ForeignFieldLabel(pattern = "objectNumber")
public class ArchObject
{
	@Id
	@GeneratedValue
	@Column(name = "idobject")
	private Long id;

	@Column(name = "objectnumber")
	@ColumnHeader(value = "Numer obiektu", order = 1)
	@EditField(label = "Numer obiektu", order = 1)
	@NotNull(message = "Numer obiektu nie moze byc pusty")
	private String objectNumber;

	@Column(name = "explorationDate")
	@ColumnHeader(value = "Data eksploracji", order = 2)
	@EditField(label = "Data eksploracji", order = 2)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getObjectNumber()
	{
		return objectNumber;
	}

	public void setObjectNumber(String objectNumber)
	{
		this.objectNumber = objectNumber;
	}

	public Date getExplorationDate()
	{
		return explorationDate;
	}

	public void setExplorationDate(Date explorationDate)
	{
		this.explorationDate = explorationDate;
	}
}
