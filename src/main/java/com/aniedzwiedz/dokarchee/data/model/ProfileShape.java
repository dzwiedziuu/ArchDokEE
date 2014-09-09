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
@Table(name = "profile_shapes")
@ForeignFieldLabel(pattern = "shapeName")
public class ProfileShape
{
	@Id
	@GeneratedValue
	@Column(name = "profile_shape_id")
	private Long id;

	@Column(name = "profile_shape_name")
	@ColumnHeader(value = "Kszta³t profilu", order = 2)
	@EditField(label = "Kszta³t profilu", order = 2)
	@NotNull(message = "Kszta³t profilu nie moze byc pusty")
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
