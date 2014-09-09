package com.aniedzwiedz.dokarchee.data.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "mass_relics")
@ForeignFieldLabel(pattern = "relicNr")
public class MassRelic
{
	@Id
	@GeneratedValue
	@Column(name = "mass_relic_id")
	private Long id;

	@Column(name = "mass_relic_nr")
	@ColumnHeader(value = "Numer zabytku masowego", order = 1)
	@EditField(label = "Numer zabytku masowego", order = 1)
	@NotNull(message = "Numer zabytku masowego nie moze byc pusty")
	private Long relicNr;

	@Column(name = "mass_relic_depth")
	@ColumnHeader(value = "G³êbokoœæ", order = 2)
	@EditField(label = "G³êbokoœæ", order = 2)
	@NotNull(message = "G³êbokoœæ nie moze byc pusta")
	private Double explorationDepth;

	@Column(name = "mass_relic_exploration_date")
	@ColumnHeader(value = "Data eksploracji", order = 3)
	@EditField(label = "Data eksploracji", order = 3)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@JoinColumn(name = "object_id")
	@ColumnHeader(value = "Powi¹zany obiekt", order = 4)
	@EditField(label = "Powi¹zany obiekt", order = 4)
	@NotNull(message = "Powi¹zany obiekt nie moze byc pusty")
	@ManyToOne(optional = false)
	private ArchObject archObject;

	@JoinColumn(name = "ar_id")
	@ColumnHeader(value = "Powi¹zany ar", order = 5)
	@EditField(label = "Powi¹zany ar", order = 5)
	@NotNull(message = "Powi¹zany ar nie moze byc pusty")
	@ManyToOne(optional = false)
	private Are are;

	@JoinColumn(name = "relic_layer_id")
	@ColumnHeader(value = "Warstwa", order = 6)
	@EditField(label = "Warstwa", order = 6)
	@NotNull(message = "Warstwa nie moze byc pusta")
	@ManyToOne(optional = false)
	private RelicLayer relicLayer;

	@JoinColumn(name = "business_context_id")
	@ManyToOne(optional = false)
	private BusinessContext businessContext;

	@ColumnHeader(value = "Specyfikacja zabytku masowego", order = 7, genericType = MassRelicSpecification.class)
	@EditField(label = "Specyfikacja zabytku masowego:", order = 7)
	@OneToMany(mappedBy = "massRelic", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<MassRelicSpecification> massRelicSpecifications = new HashSet<MassRelicSpecification>();

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

	public ArchObject getArchObject()
	{
		return archObject;
	}

	public void setArchObject(ArchObject archObject)
	{
		this.archObject = archObject;
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

	public Set<MassRelicSpecification> getMassRelicSpecifications()
	{
		return massRelicSpecifications;
	}

	public void setMassRelicSpecifications(Set<MassRelicSpecification> massRelicSpecifications)
	{
		this.massRelicSpecifications = massRelicSpecifications;
	}
}
