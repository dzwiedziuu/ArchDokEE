package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aniedzwiedz.dokarchee.gui.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;

@Entity
@Table(name = "PHOTO")
public class Photo
{
	@Id
	@Column(name = "IDUSERS")
	@ColumnHeader(value = "ID", order = 0)
	@GeneratedValue
	private Long id;

	@Column(name = "PHOTO_NUMBER")
	@ColumnHeader(value = "Numer zdjecia", order = 2)
	@EditField(label = "Numer zdjecia", order = 2)
	@NotNull(message = "Numer zdjecia nie moze byc pusty")
	@Size(min = 1, max = 5, message = "Numer zdjecia musi miec dlugosc w przedziale <1, 5>")
	private String photoNumber;

	public Photo()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPhotoNumber()
	{
		return photoNumber;
	}

	public void setPhotoNumber(String photoNumber)
	{
		this.photoNumber = photoNumber;
	}
}
