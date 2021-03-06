package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "profile_layers")
@ForeignFieldLabel(pattern = "layerName")
public class ProfileLayer
{
	@Id
	@GeneratedValue
	@Column(name = "profile_layer_id")
	private Long id;

	@Column(name = "profile_layer_name")
	@ColumnHeader(value = "Nazwa warstwy profilu", order = 2)
	@EditField(label = "Nazwa warstwy profilu", order = 2)
	@NotNull(message = "Nazwa warstwy profilu nie moze byc pusta")
	private String layerName;

	@Column(name = "profile_layer_structure")
	@ColumnHeader(value = "Struktura warstwy", order = 3)
	@EditField(label = "Struktura warstwy", order = 3)
	@NotNull(message = "Struktura warstwy nie moze byc pusta")
	@Length(min = 1, max = 1, message = "Struktura warstwy musi mie� dok�adnie jeden znak")
	private String layerStructure;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getLayerName()
	{
		return layerName;
	}

	public void setLayerName(String laterName)
	{
		this.layerName = laterName;
	}

	public String getLayerStructure()
	{
		return layerStructure;
	}

	public void setLayerStructure(String layerStructure)
	{
		this.layerStructure = layerStructure;
	}
}
