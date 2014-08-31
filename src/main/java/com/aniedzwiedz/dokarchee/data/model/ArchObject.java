package com.aniedzwiedz.dokarchee.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "objects")
@ForeignFieldLabel(pattern = "objectNumber")
public class ArchObject
{
	@Id
	@GeneratedValue
	@Column(name = "object_id")
	private Long id;

	@Column(name = "object_nr")
	@ColumnHeader(value = "Numer obiektu", order = 1)
	@EditField(label = "Numer obiektu", order = 1)
	@NotNull(message = "Numer obiektu nie moze byc pusty")
	private String objectNumber;

	@Column(name = "object_exploration_date")
	@ColumnHeader(value = "Data eksploracji", order = 2)
	@EditField(label = "Data eksploracji", order = 2)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@JoinColumn(name = "business_context_id")
	@ManyToOne(optional = false)
	private BusinessContext businessContext;

	public BusinessContext getBusinessContext()
	{
		return businessContext;
	}

	public void setBusinessContext(BusinessContext businessContext)
	{
		this.businessContext = businessContext;
	}

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
