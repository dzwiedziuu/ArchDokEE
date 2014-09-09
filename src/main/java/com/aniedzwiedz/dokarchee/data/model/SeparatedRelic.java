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
@Table(name = "separated_relics")
@ForeignFieldLabel(pattern = "relicNr")
public class SeparatedRelic
{
	@Id
	@GeneratedValue
	@Column(name = "separated_relic_id")
	private Long id;

	@Column(name = "separated_relic_nr")
	@ColumnHeader(value = "Numer zabytku wydzielonego", order = 1)
	@EditField(label = "Numer zabytku wydzielonego", order = 1)
	@NotNull(message = "Numer zabytku wydzielonego nie moze byc pusty")
	private Long relicNr;

	@Column(name = "separated_relic_depth")
	@ColumnHeader(value = "G³êbokoœæ", order = 2)
	@EditField(label = "G³êbokoœæ", order = 2)
	@NotNull(message = "G³êbokoœæ nie moze byc pusta")
	private Double explorationDepth;

	@Column(name = "separated_relic_exploration_date")
	@ColumnHeader(value = "Data eksploracji", order = 3)
	@EditField(label = "Data eksploracji", order = 3)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@Column(name = "details")
	@ColumnHeader(value = "Szczegó³y", order = 4)
	@EditField(label = "Szczegó³y", order = 4)
	@NotNull(message = "Pole szczegó³y nie moze byc puste")
	private String details;

	@JoinColumn(name = "relic_layer_id")
	@ColumnHeader(value = "Warstwa", order = 5)
	@EditField(label = "Warstwa", order = 5)
	@NotNull(message = "Warstwa nie moze byc pusta")
	@ManyToOne(optional = false)
	private RelicLayer relicLayer;

	@JoinColumn(name = "object_id")
	@ColumnHeader(value = "Powi¹zany obiekt", order = 6)
	@EditField(label = "Powi¹zany obiekt", order = 6)
	@NotNull(message = "Powi¹zany obiekt nie moze byc pusty")
	@ManyToOne(optional = false)
	private ArchObject archObject;

	@JoinColumn(name = "relic_group_id")
	@ColumnHeader(value = "Grupa zabytków", order = 7)
	@EditField(label = "Grupa zabytków", order = 7)
	@NotNull(message = "Grupa zabytków nie moze byc pusta")
	@ManyToOne(optional = false)
	private RelicGroup relicGroup;

	@JoinColumn(name = "ar_id")
	@ColumnHeader(value = "Powi¹zany ar", order = 8)
	@EditField(label = "Powi¹zany ar", order = 8)
	@NotNull(message = "Powi¹zany ar nie moze byc pusty")
	@ManyToOne(optional = false)
	private Are are;

	@JoinColumn(name = "chronology_id")
	@ColumnHeader(value = "Chronologia", order = 9)
	@EditField(label = "Chronologia", order = 9)
	@NotNull(message = "Chronologia nie moze byc pusty")
	@ManyToOne(optional = false)
	private Chronology chronology;

	@JoinColumn(name = "context_id")
	@ManyToOne(optional = false)
	private BusinessContext businessContext;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getRelicNr()
	{
		return relicNr;
	}

	public void setRelicNr(Long relicNr)
	{
		this.relicNr = relicNr;
	}

	public Double getExplorationDepth()
	{
		return explorationDepth;
	}

	public void setExplorationDepth(Double explorationDepth)
	{
		this.explorationDepth = explorationDepth;
	}

	public Date getExplorationDate()
	{
		return explorationDate;
	}

	public void setExplorationDate(Date explorationDate)
	{
		this.explorationDate = explorationDate;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	public ArchObject getArchObject()
	{
		return archObject;
	}

	public void setArchObject(ArchObject archObject)
	{
		this.archObject = archObject;
	}

	public RelicGroup getRelicGroup()
	{
		return relicGroup;
	}

	public void setRelicGroup(RelicGroup relicGroup)
	{
		this.relicGroup = relicGroup;
	}

	public Are getAre()
	{
		return are;
	}

	public void setAre(Are are)
	{
		this.are = are;
	}

	public BusinessContext getBusinessContext()
	{
		return businessContext;
	}

	public void setBusinessContext(BusinessContext businessContext)
	{
		this.businessContext = businessContext;
	}

	public RelicLayer getRelicLayer()
	{
		return relicLayer;
	}

	public void setRelicLayer(RelicLayer relicLayer)
	{
		this.relicLayer = relicLayer;
	}

	public Chronology getChronology()
	{
		return chronology;
	}

	public void setChronology(Chronology chronology)
	{
		this.chronology = chronology;
	}
}
