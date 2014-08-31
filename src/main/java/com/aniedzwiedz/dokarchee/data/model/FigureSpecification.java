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
@Table(name = "figure_specifications")
@ForeignFieldLabel(pattern = "id")
public class FigureSpecification
{
	@Id
	@GeneratedValue
	@Column(name = "figure_specification_id")
	private Long id;

	@Column(name = "figure_specification_profile")
	@ColumnHeader(value = "Profil", order = 3)
	@EditField(label = "Profil", order = 3)
	@NotNull
	private Boolean profile = false;

	@Column(name = "figure_specification_projection")
	@ColumnHeader(value = "Rzut", order = 2)
	@EditField(label = "Rzut", order = 2)
	@NotNull
	private Boolean projection = false;

	@JoinColumn(name = "figure_id")
	@NotNull(message = "Rysunek nie moze byc pusty")
	@ManyToOne(optional = false)
	private Figure figure;

	@JoinColumn(name = "object_id")
	@ColumnHeader(value = "Obiekt", order = 1)
	@EditField(label = "Obiekt", order = 1)
	@NotNull(message = "Obiekt nie moze byc pusty")
	@ManyToOne(optional = false)
	private ArchObject archObject;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Boolean getProfile()
	{
		return profile;
	}

	public void setProfile(Boolean profile)
	{
		this.profile = profile;
	}

	public Boolean getProjection()
	{
		return projection;
	}

	public void setProjection(Boolean projection)
	{
		this.projection = projection;
	}

	public Figure getFigure()
	{
		return figure;
	}

	public void setFigure(Figure figure)
	{
		this.figure = figure;
	}

	public ArchObject getArchObject()
	{
		return archObject;
	}

	public void setArchObject(ArchObject archObject)
	{
		this.archObject = archObject;
	}
}
