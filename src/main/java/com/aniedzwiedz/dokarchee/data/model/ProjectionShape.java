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
@Table(name = "projection_shapes")
@ForeignFieldLabel(pattern = "shapeName")
public class ProjectionShape
{
	@Id
	@GeneratedValue
	@Column(name = "projection_shape_id")
	private Long id;

	@Column(name = "projection_shape_name")
	@ColumnHeader(value = "Zarys rzutu", order = 2)
	@EditField(label = "Zarys rzutu", order = 2)
	@NotNull(message = "Zarys rzutu nie moze byc pusty")
	private String shapeName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getShapeName()
	{
		return shapeName;
	}

	public void setShapeName(String shapeName)
	{
		this.shapeName = shapeName;
	}
}
