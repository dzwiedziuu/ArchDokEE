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
@Table(name = "relic_specyfications")
@ForeignFieldLabel(pattern = "id")
public class MassRelicSpecification
{
	@Id
	@GeneratedValue
	@Column(name = "relic_specyfication_id")
	private Long id;

	@Column(name = "relic_specyfication_count")
	@ColumnHeader(value = "Liczba zabytków", order = 1)
	@EditField(label = "Liczba zabytków", order = 1)
	@NotNull(message = "Liczba zabytków nie moze byc pusta")
	private Long count;

	@JoinColumn(name = "mass_relics_mass_relic_id")
	@NotNull(message = "Zabytek masowy nie moze byc pusty")
	@ManyToOne(optional = false)
	private MassRelic massRelic;

	@JoinColumn(name = "relic_type_id")
	@ColumnHeader(value = "Rodzaj zabytku", order = 2)
	@EditField(label = "Rodzaj zabytku", order = 2)
	@NotNull(message = "Rodzaj zabytku nie moze byc pusta")
	@ManyToOne(optional = false)
	private RelicType relicType;

	@JoinColumn(name = "chronology_id")
	@ColumnHeader(value = "Chronologia", order = 3)
	@EditField(label = "Chronologia", order = 3)
	@NotNull(message = "Chronologia nie moze byc pusta")
	@ManyToOne(optional = false)
	private Chronology chronology;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getCount()
	{
		return count;
	}

	public void setCount(Long count)
	{
		this.count = count;
	}

	public MassRelic getMassRelic()
	{
		return massRelic;
	}

	public void setMassRelic(MassRelic massRelic)
	{
		this.massRelic = massRelic;
	}

	public RelicType getRelicType()
	{
		return relicType;
	}

	public void setRelicType(RelicType relicType)
	{
		this.relicType = relicType;
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
